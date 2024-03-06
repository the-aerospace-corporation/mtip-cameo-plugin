/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Import;

import java.awt.Rectangle;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.CheckForNull;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.ModelElements.CommonRelationshipsFactory;
import org.aero.mtip.ModelElements.ModelDiagram;
import org.aero.mtip.XML.Export.ExportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ImportXmlSysml {
    static Map<String,Entry<Element, Element>> linktoPair = new HashMap<String,Entry<Element, Element>>();
    static Map<ModelDiagram,Diagram> diagramMap = new HashMap<ModelDiagram,Diagram>();
    public static HashMap<String, XMLItem> completeXML = new HashMap<String, XMLItem>();
    private static HashMap<String, XMLItem> stereotypesXML = new HashMap<String, XMLItem>();
    private static HashMap<String, XMLItem> profileXML = new HashMap<String, XMLItem>();
    private static Project project = Application.getInstance().getProject();
    private static HashMap<String, String> parentMap = new HashMap<String, String>();
    private static HashMap<String, String> pluginCreatedIDs = new HashMap<String, String>();
    private static ImportMetrics importMetrics = new ImportMetrics();
    
    //Variables for Automatic Validation Creation
	public static final boolean CREATE_VALIDATION_ON_IMPORT = false;
	public static final boolean IMPORT_FILTER = false;
    public static Element MODEL_VALIDATION_PACKAGE = null;
    public static CommonElementsFactory cef = new CommonElementsFactory();
    public static CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
    
	private static Element CHECK_CLASSES = null;
	private static Element CHECK_RELATIONSHIPS = null;
    
    private static Element primaryLocation = null;
    
    public static void resetImportParameters() {
    	//Reset class variables - if a previous import has been informed, old data may persist.
    	linktoPair = new HashMap<String,Entry<Element, Element>>();
		diagramMap = new HashMap<ModelDiagram,Diagram>();
		completeXML = new HashMap<String, XMLItem>();
		stereotypesXML = new HashMap<String, XMLItem>();
		profileXML = new HashMap<String, XMLItem>();
		project = Application.getInstance().getProject();
		parentMap = new HashMap<String, String>();
		pluginCreatedIDs = new HashMap<String, String>();
		importMetrics = new ImportMetrics();
		
		MODEL_VALIDATION_PACKAGE = null;
		CHECK_CLASSES = null;
		CHECK_RELATIONSHIPS = null;
		ImportLog.reset();
    }
    
    public static void parseXML(Document doc, Element start) throws NullPointerException {
    	if(start == null) {
			primaryLocation = project.getPrimaryModel();
			CameoUtils.logGUI("\nMounting project on package," + primaryLocation.getHumanName() + " with id: " + primaryLocation.getID());
		} else {
			primaryLocation = start;
		}
		CameoUtils.logGUI("Beginning Import.....");
		
		// Read file and set up XML object
		Node packet = doc.getDocumentElement();
		NodeList dataNodes = packet.getChildNodes();

		// Parse XML and build model based on data
		buildModelMap(dataNodes);
		buildStereotypesTree();
    }
    
	public static void createModel() throws NullPointerException {
		project.getOptions().setAutoNumbering(false);
		
		buildModel(profileXML);
		buildModel(completeXML);
		
		ImportLog.save();

		createSession("Refresh");
		closeSession();
		project.getOptions().setAutoNumbering(true);
	}

	public static void buildModel(HashMap<String, XMLItem> parsedXML) {
		for (Entry<String, XMLItem> entry : parsedXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			
			if(modelElement == null) {
				ImportLog.log(String.format("Error parsing data for XML item with id %s. Element will not be imported", id));
			}
			
			if (parentMap.get(modelElement.getEAID()) != null) {
				continue;
			}
			
			buildEntity(parsedXML, modelElement);
		}	
	}
	
	@CheckForNull
	public static Element buildEntity(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if (modelElement == null) {
			ImportLog.log(String.format("XML data not found attempting to build entity."));
			return null;
		}
		
		String category = modelElement.getCategory();
		
		if(category.isEmpty()) {
			ImportLog.log(String.format("Element %s with id %s is uncategorized and will not be imported.", modelElement.getName(), modelElement.getEAID()));
			return null;
		}
		
		if (category.equals(SysmlConstants.ELEMENT)) {
			return buildElement(project, parsedXML, modelElement);
		}
		
		if (category.equals(SysmlConstants.RELATIONSHIP)) {
			return buildRelationship(project, parsedXML, modelElement);
		}
		
		if (modelElement.getCategory().equals(SysmlConstants.DIAGRAM)) {
			return buildDiagram(project, parsedXML, modelElement);
		}
		
		return null;
	}
	
	public static Element buildDiagram(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(!isDiagramSupported(modelElement)) {
			ImportLog.log(String.format("%s with id %s type is not supported. ", modelElement.getType(), modelElement.getEAID()));
		}
		
		Element owner = GetImportedOwner(modelElement, parsedXML);
		
		createSession(String.format("Create %s Element", modelElement.getType()));
		
		Diagram newDiagram = null;
		AbstractDiagram diagram = (AbstractDiagram) cef.createElement(modelElement.getType(), modelElement.getName(), modelElement.getEAID());
		newDiagram = (Diagram) diagram.createElement(project, owner, modelElement);
		project.getDiagram(newDiagram).open(); 
		
		importMetrics.countElement(diagram);
		TrackIds(newDiagram, modelElement);
		
		closeSession();
		
		// Opens and closes its own session while populating the diagram
		populateDiagram(diagram, newDiagram, modelElement, parsedXML);
		
//		ImportLog.log(String.format("Created diagram %s of type %s and id %s", modelElement.getName(), modelElement.getType(), modelElement.getEAID()));
		return newDiagram;
	}
	
	public static Element buildRelationship(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(!isRelationshipSupported(modelElement)) { 
			ImportLog.log(String.format("%s type not supported. Import id %s", modelElement.getType(), modelElement.getEAID()));
			return null;
		}
		
		if (modelElement.isCreated()) {
			return (Element) project.getElementByID(modelElement.getCameoID());
		}
		
		Element owner = GetImportedOwner(modelElement, parsedXML);
		Element client = GetImportedClient(modelElement, parsedXML);
		if(client == null) {
			ImportLog.log(String.format("Client null for relationship %s. Import id %s", modelElement.getName(), modelElement.getEAID()));
		}
		Element supplier = GetImportedSupplier(modelElement, parsedXML);
		if(supplier == null) {
			ImportLog.log(String.format("Supplier null for relationship %s. Import id %s", modelElement.getName(), modelElement.getEAID()));
		}
		
		createSession(String.format("Creating CommonRelationship of type %s.",modelElement.getType()));
		CommonRelationship relationship = crf.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
		closeSession();
		
		if (relationship == null) {
			ImportLog.log("Error. Element missing in common elements factory.");
			return null;
		}

		relationship.createDependentElements(project, parsedXML, modelElement);
		
		createSession(String.format("Creating Relationship of type %s.", modelElement.getType()));
		Element newElement = relationship.createElement(project, owner, client, supplier, modelElement);
		closeSession();
				
		if (newElement == null) {
			ImportLog.log(String.format("Relationship not created. Type: %s ID: %s with parent %s.", modelElement.getType(), modelElement.getEAID(), modelElement.getParent()));
			return null;
		}
		
		if(newElement.getOwner() == null) {
			newElement.dispose();
			ImportLog.log("Owner failed to be set including any default owners. Relationship not created.");
			return null;
		}
			
		TrackRelationshipIds(modelElement, newElement, supplier, client);
//		ImportLog.log(String.format("Created relationship of type: %s and id: %s with parent %s.", modelElement.getType(), modelElement.getEAID(), modelElement.getParent()));
		return newElement;
	}
	
	public static Element buildElement(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(!isElementSupported(modelElement)) { 
			ImportLog.log(String.format("%s type not supported. Import id %s", modelElement.getType(), modelElement.getEAID()));
			return null;
		}
		
		if (modelElement.isCreated()) {
			return (Element) project.getElementByID(modelElement.getCameoID());
		}
		
		Element owner = GetImportedOwner(modelElement, parsedXML);
		
		createSession(String.format("Initialize element %s Element", modelElement.getType()));
		CommonElement element = cef.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
		closeSession();
		
		element.createDependentElements(project, parsedXML, modelElement);
		
		// Create new session if tagged values or other dependent elements are found, built, and session is closed.
		createSession(String.format("Create %s with dependent Elements", modelElement.getType()));
		Element newElement = element.createElement(project, owner, modelElement);
		closeSession();
		
		if(newElement == null) {
			ImportLog.log(String.format("Element not created. Name: %s Id: %s",  modelElement.getName(), modelElement.getEAID()));
			return owner;
		}
		
		TrackIds(newElement, modelElement);
		addStereotypes(newElement, modelElement);
		element.addStereotypeTaggedValues(modelElement);					
		element.createReferencedElements(parsedXML, modelElement);
		
		if(newElement.getOwner() == null) {
			newElement.dispose();
			ImportLog.log("Owner failed to be set including any default owners. Element with id " + modelElement.getEAID() + " not created.");
			return null;
		}
		
//		ImportLog.log(String.format("Created element %s of type: %s.", modelElement.getName(), modelElement.getType()));
		return newElement;
	}
	
	//Get stereotypes from parsedXML. Find profiles for those stereotypes. Get stereotypes. Apply stereotypes
	public static void addStereotypes(Element newElement, XMLItem modelElement) {
		HashMap<String, String> stereotypes = modelElement.getStereotypes();
		
		for(String stereotype : stereotypes.keySet()) {
			addStereotype(newElement, stereotype, stereotypes.get(stereotype));
			addStereotypeFields(newElement, modelElement);
		}
	}
	
	public static void buildModelMap(NodeList dataNodes) {
		int totalElementNodeCount = 0;
		int successParsedCount = 0;
		List<XMLItem> properties = new ArrayList<XMLItem> ();
		
		//Loop through all of the <data> nodes
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				totalElementNodeCount++;
				NodeList fieldNodes = dataNode.getChildNodes();
				XMLItem modelElement = new XMLItem();
				//Loop through all of the fields in each dataNode
				for(int j = 0; j < fieldNodes.getLength(); j++) {
					Node fieldNode = fieldNodes.item(j);
					
					if(fieldNode.getNodeType() == Node.ELEMENT_NODE) {
						//get model element type (ex. Sysml.Package)
						if(fieldNode.getNodeName().contentEquals(XmlTagConstants.TYPE)) {
							String type = fieldNode.getTextContent();
							if(type.contains(".")) {
								String element = type.split("[.]")[1];
								modelElement.setType(element);
							} else {
								modelElement.setType("");
							}
						}
						//get ID associated with each model element (for now, EA GUID)
						if(fieldNode.getNodeName().equals("id")) {
							modelElement = getIDs(fieldNode, modelElement);
						}
						//Loop through attributes and pull out necessary information
						if(fieldNode.getNodeName().equals("attributes")) {
							modelElement = getAttributes(fieldNode, modelElement);
						}
						//Loop through relationships look for parent, if diagram look for elements on diagram
						if(fieldNode.getNodeName().contentEquals("relationships")) {
							modelElement = getRelationships(fieldNode, modelElement);
						}
						
						//Add model element attributes to parsedXML hashmap passed back to main function
						if ((modelElement.getEAID() != null)  && !(modelElement.getEAID().isEmpty())) {
							for (String id : modelElement.getIds()) {
								completeXML.put(id,  modelElement);
							}
							
							if(modelElement.getType().contentEquals("Stereotype")) {
								for (String id : modelElement.getIds()) {
									stereotypesXML.put(id,  modelElement);
								}
							}
							if(modelElement.getType().contentEquals(SysmlConstants.PROPERTY)) {
								if(modelElement.getParent() != null) {
									// Parent may not exists in modelElements yet. Add to list to check if should be included in stereotypesXML post modelElements building
									properties.add(modelElement);
								}
							}
						} 
					}
				} if ((modelElement.getEAID() != null)  && !(modelElement.getEAID().isEmpty())) {
					successParsedCount++;
				} else {
					String message = "No ID for " + modelElement.getName() + ". Element will not be parsed correctly.";
					ImportLog.log(message);
				}
			}
		}
		ImportLog.log("Successfully parsed " + successParsedCount + "/" + totalElementNodeCount + " data blocks from XML.");
		// 
		for(XMLItem modelElement : properties) {
			if(modelElement.getParent() != null) {
				if(completeXML.get(modelElement.getParent()) != null) {
					if(completeXML.get(modelElement.getParent()).getType().contentEquals(SysmlConstants.STEREOTYPE)) {
						stereotypesXML.put(modelElement.getEAID(), modelElement);
					}
				} else {
					CameoUtils.logGUI("No parent found for property with id " + modelElement.getEAID());
				}
			}
		}
	}
	
	public static void buildStereotypesTree() {
		for (Entry<String, XMLItem> entry : stereotypesXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			profileXML.put(id,  modelElement);
			String parentID = modelElement.getParent();
			addParentToProfileXML(parentID);
		}
	}
	
	public static void addParentToProfileXML(String parentID) {
		if(parentID != "") {
			profileXML.put(parentID, completeXML.get(parentID));
			XMLItem nextParent = completeXML.get(parentID);
			if(nextParent != null) {
				String nextParentID = nextParent.getParent();
				addParentToProfileXML(nextParentID);
			}
		}
	}
	
	public static void addStereotype(Element newElement, String stereotypeName, String profileName) {
		// TODO: Implement UML Standard Profile Class, getInstance(), getMetaclass
		Profile umlStandardProfile = StereotypesHelper.getProfile(project,  "UML Standard Profile");
		
		if(stereotypeName.contentEquals("metaclass")) {
			Stereotype stereotypeObj = StereotypesHelper.getStereotype(project, "Metaclass", umlStandardProfile);
			StereotypesHelper.addStereotype(newElement,  stereotypeObj);
			return;
		} 
			
		Profile profile = StereotypesHelper.getProfile(project,  profileName);
		
		if (profile == null) {
			return; 
		}
		
		Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
		
		if(stereotype == null) {
			return;
		}
		
		StereotypesHelper.addStereotype(newElement,  stereotype);
	}
	
	public static void addStereotypeFields(Element newElement, XMLItem xmlElement) {
		// Need more robust way of doing this including all fields of all stereotypes... eventually
		// Have list of properties for each stereotype, iterate through them and update if xmlElement has equivalent field
		if(newElement instanceof Constraint) {
			List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(newElement);
			com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile validationProfile = StereotypesHelper.getProfile(project,  "Validation Profile");
			Stereotype validationRule = StereotypesHelper.getStereotype(project, "validationRule", validationProfile);
			
			//Must check if constraint has validation rule stereotype that holds these attributes
			if(stereotypes.contains(validationRule)) {
				if(xmlElement.hasAttribute("severity")) {
					Enumeration severityKind = Finder.byQualifiedName().find(project, "UML Standard Profile::Validation Profile::SeverityKind");
					for(EnumerationLiteral el : severityKind.getOwnedLiteral()) {
						if(el.getName().contentEquals(xmlElement.getAttribute("severity"))) {
							StereotypesHelper.setStereotypePropertyValue(newElement, validationRule, "severity", el);
						}
					}
				}
				if(xmlElement.hasAttribute("errorMessage")) {
					StereotypesHelper.setStereotypePropertyValue(newElement, validationRule, "errorMessage", xmlElement.getAttribute("errorMessage"));
				}
			}
		}
	}
	
	public static XMLItem getRelationships(Node fieldNode, XMLItem modelElement) {
		NodeList idNodes = fieldNode.getChildNodes();
		for (int k = 0; k < idNodes.getLength(); k++) {
			Node relationshipNode = idNodes.item(k);
			if (relationshipNode.getNodeType() == Node.ELEMENT_NODE) {
				if (relationshipNode.getNodeName() == XmlTagConstants.ELEMENT) {
					NodeList elementNodes = relationshipNode.getChildNodes();
					for(int j = 0; j < elementNodes.getLength(); j++) {
						Node elementNode = elementNodes.item(j);
						if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
							getDiagramElementData(elementNode, modelElement);
						}
					}
				} else if(relationshipNode.getNodeName() == XmlTagConstants.DIAGRAM_CONNECTOR) {
					NodeList elementNodes = relationshipNode.getChildNodes();
					for(int j = 0; j < elementNodes.getLength(); j++) {
						Node elementNode = elementNodes.item(j);
						if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
							getDiagramRelationshipData(elementNode, modelElement);
						}
					}
				} else if (relationshipNode.getNodeName() == XmlTagConstants.HAS_PARENT) {
					modelElement.setParent(getIdValueFromNode(relationshipNode));
				} else {
					String id = getIdValueFromNode(relationshipNode);
					if(!id.isEmpty()){
						modelElement.addAttribute(relationshipNode.getNodeName(), getIdValueFromNode(relationshipNode));
					}					
				}
			}
		}
		return modelElement;
	}
	
	public static String getIdValueFromNode(Node relationship) {
		NodeList idNodes = relationship.getChildNodes();
		for (int k = 0; k < idNodes.getLength(); k++) {
			Node idNode = idNodes.item(k);
			if (idNode.getNodeType() == Node.ELEMENT_NODE) {
				if (idNode.getNodeName() == XmlTagConstants.ID_TAG) {
					return idNode.getTextContent();
				}
			}
		}
		ImportLog.log("No ID found in field " + relationship.getNodeName() + ".");
		return "";
	}
	
	public static void getDiagramElementData(Node elementNode, XMLItem modelElement) {
		NodeList elementAttributesNodes = elementNode.getChildNodes();
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		int bottom = 0;
		int right = 0;
		
		String id = "";
		String type = "";
		String parentID = "";
		
		for (int i = 0; i < elementAttributesNodes.getLength(); i++) {
			Node elementAttributeNode = elementAttributesNodes.item(i);
			if (elementAttributeNode.getNodeType() == Node.ELEMENT_NODE) {
				if(elementAttributeNode.getNodeName() == XmlTagConstants.RELATIONSHIP_METADATA) {
					NodeList positionNodes = elementAttributeNode.getChildNodes();
					for (int j = 0; j < positionNodes.getLength(); j++) {
						Node positionNode = positionNodes.item(j);
						if(positionNode.getNodeType() == Node.ELEMENT_NODE) {
							if (positionNode.getNodeName() == XmlTagConstants.TOP) {
								y = Integer.parseInt(positionNode.getTextContent());
							}
							if (positionNode.getNodeName() == XmlTagConstants.BOTTOM) {
								bottom = Integer.parseInt(positionNode.getTextContent());
							}
							if (positionNode.getNodeName() == XmlTagConstants.LEFT) {
								x = Integer.parseInt(positionNode.getTextContent());
							}
							if (positionNode.getNodeName() == XmlTagConstants.RIGHT) {
								right = Integer.parseInt(positionNode.getTextContent());
							}
							if(positionNode.getNodeName() == XmlTagConstants.DIAGRAM_PARENT) { 
								NodeList parentNodes = positionNode.getChildNodes();
								for (int k = 0; k < parentNodes.getLength(); k++) {
									Node parentNode = parentNodes.item(k);
									if(parentNode.getNodeType() == Node.ELEMENT_NODE) {
										if (parentNode.getNodeName() == XmlTagConstants.ID) {
											parentID = parentNode.getTextContent();
										}
									}
								}
							}
						}
					}
				}
				if (elementAttributeNode.getNodeName() == XmlTagConstants.ID) {
					id = elementAttributeNode.getTextContent();
				}
				if (elementAttributeNode.getNodeName() == XmlTagConstants.TYPE) {
					type = elementAttributeNode.getTextContent();
				}
			}
		}
		if(x != 0 && y != 0 && bottom != 0 && right != 0 && !id.isEmpty() && !type.isEmpty()) {
			 modelElement.addChildElement(id);
			 modelElement.addChildElementType(id, type);
			 width = right - x;
			 height = bottom - y;
			 modelElement.addLocation(id, new Rectangle(x, -y, width, -height));
			 if(!parentID.isEmpty()) {
				 modelElement.addDiagramParent(id, parentID);
			 }
			 
		} else {
			modelElement.addChildElement(id);
			modelElement.addChildElementType(id, type);
			modelElement.addLocation(id, new Rectangle(-999, -999, -999, -999));
			CameoUtils.logGUI("Diagram element did not contain sufficient data for placement. Element will be automatically placed on diagram.");
		}
	}
	
	public static void getDiagramRelationshipData(Node relationshipNode, XMLItem modelElement) {
		NodeList elementAttributesNodes = relationshipNode.getChildNodes();		
		String id = "";
		String type = "";
		
		for (int i = 0; i < elementAttributesNodes.getLength(); i++) {
			Node elementAttributeNode = elementAttributesNodes.item(i);
			if (elementAttributeNode.getNodeType() == Node.ELEMENT_NODE) {
				if (elementAttributeNode.getNodeName() == XmlTagConstants.ID) {
					id = elementAttributeNode.getTextContent();
				}
				if (elementAttributeNode.getNodeName() == XmlTagConstants.TYPE) {
					type = elementAttributeNode.getTextContent();
				}
			}
		}
		
		if(!id.isEmpty() && !type.isEmpty()) {
			 modelElement.addChildRelationship(id);
		}
	}
	
	public static Node getSubAttribute(Node attributeNode) {
		NodeList childNodes = attributeNode.getChildNodes();
		for(int k = 0; k < childNodes.getLength(); k++) {
			Node childNode = childNodes.item(k);
			if(childNode.getNodeName().contentEquals(XmlTagConstants.ATTRIBUTE)) {
				return childNode;
			}
		}
		return null;
	}
	
	public static List<Node> getListSubAttributes(Node attributeNode) {
		List<Node> nodes = new ArrayList<Node> ();
		NodeList childNodes = attributeNode.getChildNodes();
		for(int k = 0; k < childNodes.getLength(); k++) {
			Node childNode = childNodes.item(k);
			if(childNode.getNodeName().contentEquals(XmlTagConstants.ATTRIBUTE)) {
				nodes.add(childNode);
			}
		}
		if(!nodes.isEmpty()) {
			return nodes;
		}
		return null;
	}
	
	public static Node getDirectChildByKey(Node parent, String key) {
		NodeList childNodes = parent.getChildNodes();
		for(int k = 0; k < childNodes.getLength(); k++) {
			Node childNode = childNodes.item(k);
			if(childNode.getNodeType() == Node.ELEMENT_NODE) {
				if(((org.w3c.dom.Element)childNode).getAttribute(XmlTagConstants.ATTRIBUTE_KEY).equals(key)) {
					return childNode;
				}
			}
		}
		return null;
	}
	
	public static List<Node> getDirectChildrenByKey(Node parent, String key) {
		List<Node> nodes = new ArrayList<Node> ();
		NodeList childNodes = parent.getChildNodes();
		for(int k = 0; k < childNodes.getLength(); k++) {
			Node childNode = childNodes.item(k);
			if(childNode.getNodeType() == Node.ELEMENT_NODE) {
				if(((org.w3c.dom.Element)childNode).getAttribute(XmlTagConstants.ATTRIBUTE_KEY).equals(key)) {
					nodes.add(childNode);
				}
			}
		}
		return null;
	}
	
	public static XMLItem getAttributes(Node fieldNode, XMLItem modelElement) {	
		NodeList attributeNodes = fieldNode.getChildNodes();
		for(int k = 0; k < attributeNodes.getLength(); k++) {
			Node attribute = attributeNodes.item(k);
			if(attribute.getNodeType() == Node.ELEMENT_NODE) {
				String attributeKey = ((org.w3c.dom.Element)attribute).getAttribute(XmlTagConstants.ATTRIBUTE_KEY);
				String attributeType = ((org.w3c.dom.Element)attribute).getAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE);
				if(attributeKey.contentEquals(XmlTagConstants.STEREOTYPE_TAG)) {
					try {
						String stereotypeName = getDirectChildByKey(attribute, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME).getTextContent();
//						String stereotypeID = getDirectChildByKey(attribute, XmlTagConstants.ATTRIBUTE, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_ID).getTextContent();
						String profileName = getDirectChildByKey(attribute, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME).getTextContent();
//						String profileID = getDirectChildByKey(attribute, XmlTagConstants.ATTRIBUTE, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_ID).getTextContent();
						modelElement.addStereotype(stereotypeName, profileName);
					} catch (NullPointerException npe) {
						ImportLog.log("Error parsing stereotype name or profile name from HUDS XML for element: " + modelElement.getEAID());
					}
				} else if (attributeKey.contentEquals(XmlTagConstants.STEREOTYPE_TAGGED_VALUE)) {
					try {
						TaggedValue tv = new TaggedValue(attribute);
						modelElement.addStereotypeTaggedValue(tv);
					} catch (NullPointerException npe) {
						ImportLog.log("Error parsing stereotype tagged value from HUDS XML for element: " + modelElement.getEAID());
					}
					
				} else if (attributeType.contentEquals(XmlTagConstants.ATTRIBUTE_TYPE_LIST)) {
					try {
						List<Node> listAttributes = getListSubAttributes(attribute);
						for(Node listAttribute : listAttributes) {
							modelElement.addListAttribute(attributeKey, listAttribute.getTextContent());
						}
						
					} catch (NullPointerException npe) {
						ImportLog.log(String.format("Error parsing attribute for element with id: %s", modelElement.getEAID()));
					}
				} else if (Arrays.asList(XMLItem.LIST_ATTRIBUTES).contains(attributeKey)) {
					try {
						modelElement.addListAttribute(attributeKey, getSubAttribute(attribute).getTextContent());
					} catch (NullPointerException npe) {
						ImportLog.log(String.format("Error parsing attribute for element with id: %s", modelElement.getEAID()));
					}
				} else {
					try {
						Node subAttribute = getSubAttribute(attribute);
						modelElement.addAttribute(attributeKey, subAttribute.getTextContent());
					} catch (NullPointerException npe) {
						ImportLog.log("Error parsing attribute for element with id: " + modelElement.getEAID());
					}
				}
				
			}
		}
		return modelElement;
	}
	
	public static XMLItem getIDs(Node fieldNode, XMLItem modelElement) {	
		NodeList idNodes = fieldNode.getChildNodes();
		for(int k = 0; k < idNodes.getLength(); k++) {
			Node idNode = idNodes.item(k);
			if(idNode.getNodeType() == Node.ELEMENT_NODE) {
				// Add all Ids to object
				modelElement.addId(idNode.getTextContent());
				modelElement.addIdWithType(idNode.getNodeName(), idNode.getTextContent());
				
				if(idNode.getNodeName().contentEquals(XmlTagConstants.HUDDLE_ID)) {
					modelElement.setEAID(idNode.getTextContent());
				}
			}
		}
		
		if (modelElement.getEAID().trim().isEmpty() && !modelElement.getIds().isEmpty()) {
			modelElement.setEAID(modelElement.getIds().get(0));
		}
		return modelElement;
	}
	//Recursive function to parse through XML Nodes
	public static void buildModelRecursive(Node node) {
	    // do something with the current node instead of System.out
	    System.out.println(node.getNodeName());

	    NodeList nodeList = node.getChildNodes();
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node currentNode = nodeList.item(i);
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	            //calls this method for all the children which is Element
	            buildModelRecursive(currentNode);
	        }
	    }
	}
	
	public static void outputAll(Map<String, XMLItem> parsedXML) {
		for(Entry<String, XMLItem> entry : parsedXML.entrySet()) {
			try {
				CameoUtils.logGUI("Item with Key: " + entry.getKey());
				XMLItem modelElement = entry.getValue();
				CameoUtils.logGUI("\t and of type: " + modelElement.getType());
				CameoUtils.logGUI("\t and with id: " + modelElement.getEAID());
				CameoUtils.logGUI("\t and with parent: " + modelElement.getParent());
				CameoUtils.logGUI("\t and with name: " + modelElement.getAttribute("name"));
			} catch(NullPointerException npe) {
				CameoUtils.logGUI("Null value found for item with key: " + entry.getKey());
			}
		}
	}
	
	public static void setProject() {
		Application.getInstance().getProject();
	}
	
	public static Project getProject() {
		return ImportXmlSysml.project;
	}
	
	public static Element getParent(String id) {
		return (Element) ImportXmlSysml.project.getElementByID(ImportXmlSysml.parentMap.get(id));
	}
	
	public static Element getParent(Element element) {
		return (Element) ImportXmlSysml.project.getElementByID(ImportXmlSysml.parentMap.get(element.getID()));
	}
	
	@CheckForNull
	public static String idConversion(String id) {
		if(ImportXmlSysml.completeXML.get(id) != null) {
			return ImportXmlSysml.completeXML.get(id).getCameoID();
		} 
		
		return null;
	}
	
	public static Element getImportedElement(String importId) {
		String createdId = idConversion(importId);
		
		if (createdId == null) {
			return null;
		}
		
		return (Element) project.getElementByID(createdId);
	}
	
	public static Element GetImportedOwner(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		String ownerID = modelElement.getParent();
		XMLItem ownerElement = parsedXML.get(ownerID);
		
		if(modelElement.getParent().trim().isEmpty() || ownerElement == null) {
			ImportLog.log(String.format("Xml object not found for owner of %s with id %s.", modelElement.getName(), modelElement.getEAID()));
			return primaryLocation; // Set owned equal to Primary model if no hasParent attribute in XML -> parent field in XMLItem == ""
		} 
		
		String ownerCreatedID = idConversion(ownerID);
		
		if (ownerCreatedID == null || ownerCreatedID.trim().isEmpty()) {
			return buildEntity(parsedXML, ownerElement);
		}
		
		return (Element) project.getElementByID(ownerCreatedID);
		
	}
	
	@CheckForNull
	public static Element GetImportedClient(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		if (modelElement.getClient() == null || modelElement.getClient().trim().isEmpty()) {
			ImportLog.log(String.format("No client id in xml for %s with id %s.", modelElement.getName(), modelElement.getEAID()));
			return null;
		}
		
		if (parentMap.containsKey(modelElement.getClient())) {
			return  (Element) project.getElementByID(parentMap.get(modelElement.getClient()));
		}
		
		String clientImportId = modelElement.getClient();
		XMLItem clientElement = parsedXML.get(clientImportId);
		
		if(clientElement != null) {
			return buildElement(project, parsedXML, clientElement);
		} 
		
		ImportLog.log(String.format("Client Element with id : %s not exported with XML. "
				+ "Checking for elements from cameo profiles and model libraries", clientImportId));
		
		if(clientImportId.startsWith("_9_")) {
			Element client = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
			if(client != null) {
				return client;
			}
		}
		
		if(clientImportId.startsWith("_")) {
			return (Element)project.getElementByID(clientImportId);
		}		
		
		return null;
	}
	
	public static Element GetImportedSupplier(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		if (modelElement.getSupplier() == null || modelElement.getSupplier().trim().isEmpty()) {
			ImportLog.log(String.format("No supplier id in xml for %s with id %s.", modelElement.getName(), modelElement.getEAID()));
			return null;
		}
		
		if (parentMap.containsKey(modelElement.getSupplier())) {
			return  (Element) project.getElementByID(parentMap.get(modelElement.getSupplier()));
		}
		
		String supplierImportId = modelElement.getSupplier();
		XMLItem supplierElement = parsedXML.get(supplierImportId);
		
		if(supplierElement != null) {
			return buildElement(project, parsedXML, supplierElement);
		}
		
		ImportLog.log(String.format("Supplier element with id : %s not exported with XML. "
				+ "Checking for elements from cameo profiles and model libraries", supplierImportId));
		
		if(supplierImportId.startsWith("_9_")) {
			Element supplier = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
			if(supplier != null) {
				return supplier;
			}
		}
		
		if(supplierImportId.startsWith("_")) {
			return (Element) project.getElementByID(supplierImportId);
		}
		
		return null;
	}
	
	public static boolean isElementSupported(XMLItem modelElement) {
		return SysmlConstants.SYSML_ELEMENTS.contains(modelElement.getType());
	}
	
	public static boolean isRelationshipSupported(XMLItem modelElement) {
		return SysmlConstants.SYSML_RELATIONSHIPS.contains(modelElement.getType());
	}
	
	public static boolean isDiagramSupported(XMLItem modelElement) {
		return SysmlConstants.SYSML_DIAGRAMS.contains(modelElement.getType());
	}
	
	public static void TrackIds(Element newElement, XMLItem modelElement) {
		modelElement.setCameoID(newElement.getID());
		pluginCreatedIDs.put(newElement.getID(), "");
		for (String curId : modelElement.getIds()) {
			parentMap.put(curId, newElement.getID());
		}
	}
	
	public static void TrackRelationshipIds(XMLItem modelElement, Element newElement, Element supplier, Element client) {
		String GUID = newElement.getID();
		for (String curId : modelElement.getIds()) {
			parentMap.put(curId, GUID);
		}
		
		modelElement.setCameoID(GUID);
		Map.Entry<Element, Element> entry = new AbstractMap.SimpleEntry<Element, Element>(supplier, client);
		linktoPair.put(modelElement.getEAID(), entry);
	}
	
	public static HashMap<Element, Rectangle> GetImportedElementsOnDiagram(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		HashMap<Element, Rectangle> elementsOnDiagram = new HashMap<Element, Rectangle> ();
		
		for(String importID : modelElement.getChildElements(parsedXML)) {
			String cameoID = parentMap.get(importID);
			
			if(cameoID == null || cameoID.trim().isEmpty()) {
				ImportLog.log(String.format("Element with import id %s failed to be created before populating diagram.", importID));
				continue;
			}
			
			Element elementOnDiagram = (Element)project.getElementByID(cameoID);
			
			if(elementOnDiagram == null) {
				ImportLog.log(String.format("Failed to find created element with import id %s", importID));
				continue;
			}
			
			// Move to Abstract Diagram Function
//			if (disallowedElements.contains(ExportXmlSysml.getElementType(elementOnDiagram))) {
//				continue;
//			}
			
			elementsOnDiagram.put(elementOnDiagram, modelElement.getLocation(importID));
		}
		
		return elementsOnDiagram;
	}
	
	public static List<Element> GetImportedRelationshipsOnDiagram(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		List<Element> relationshipsOnDiagram = new ArrayList<Element> ();
				
		for(String importRelationshipID : modelElement.getChildRelationships(parsedXML)) {
			String cameoID = parentMap.get(importRelationshipID);
			
			if(cameoID == null || cameoID.trim().isEmpty()) {
				ImportLog.log(String.format("Element with import id %s failed to be created before populating diagram.", importRelationshipID));
				continue;
			}
			
			Element elementOnDiagram = (Element)project.getElementByID(cameoID);
			
			if(elementOnDiagram == null) {
				ImportLog.log(String.format("Failed to find created element with import id %s", importRelationshipID));
				continue;
			}
			
			relationshipsOnDiagram.add((Element)project.getElementByID(cameoID));
		}
		return relationshipsOnDiagram;
	}
	
	public static void populateDiagram(AbstractDiagram diagram, Diagram newDiagram, XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		diagram.createDependentElements(project, parsedXML, modelElement);
		
		HashMap<Element, Rectangle> elementsOnDiagram = GetImportedElementsOnDiagram(modelElement, parsedXML);
		List<Element> relationshipsOnDiagram = GetImportedRelationshipsOnDiagram(modelElement, parsedXML);
			
		createSession(String.format("Adding elements to diagram with type %s.", modelElement.getType()));
				
		boolean noPosition = diagram.addElements(project, newDiagram, elementsOnDiagram, modelElement);
		if(noPosition) {
			Application.getInstance().getProject().getDiagram(newDiagram).layout(true, new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());
		}
		
		try {
			diagram.addRelationships(project, newDiagram, relationshipsOnDiagram);
		} catch (ReadOnlyElementException roee) {
			ImportLog.log("Read only element exception encountered populating diagram.");
		}
		
		project.getDiagram(newDiagram).close(); 
		closeSession();
		
	    diagramMap.put((ModelDiagram) diagram, newDiagram);
	}
	
	public static void createSession(String sessionName) {
		if (SessionManager.getInstance().isSessionCreated(project)) {
			return;
		}
		
		SessionManager.getInstance().createSession(project, sessionName);
	}
	
	public static void closeSession() {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			return;
		}
		
		SessionManager.getInstance().closeSession(project);
	}
}
