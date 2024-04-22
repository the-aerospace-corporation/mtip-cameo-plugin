/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Import;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.CheckForNull;
import javax.xml.parsers.ParserConfigurationException;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.ModelElements.CommonRelationshipsFactory;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.FileSelect;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.apache.commons.collections.MapUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Importer {
	static Importer instance;
	
	Map<String, String> importIdToCameoId = new HashMap<String, String> ();
	Map<String, String> parentMap = new HashMap<String, String>();
	Set<String> unsupportedElementIds = new HashSet<String> ();
	
    HashMap<String, XMLItem> completeXML = new HashMap<String, XMLItem>();
    HashMap<String, XMLItem> stereotypesXML = new HashMap<String, XMLItem>();
    HashMap<String, XMLItem> profileXML = new HashMap<String, XMLItem>();
    
    Project project;
    Element primaryLocation;
    CommonElementsFactory cef;
    CommonRelationshipsFactory crf;
    
    public static Importer createNewImporter() {
    	instance = new Importer();
    	return instance;
    }
    
    public static void destroy() {
    	instance = null;
    }
    
    public static Importer getInstance() {
    	return instance;
    }
    
	public static void importFromFiles(File[] files, Element parentPackage) throws ParserConfigurationException, SAXException, IOException {
		Importer importer = createNewImporter();
		
		for(File file : files) {
			Document doc = FileSelect.createDocument(file);
			doc.getDocumentElement().normalize();
			
			importer.parseXML(doc, parentPackage);
		}

		importer.createModel();
		
		Logger.logSummary(importer);
	}
    
    public Importer() {
    	project = Application.getInstance().getProject();
        cef = new CommonElementsFactory();
        crf = new CommonRelationshipsFactory();
    	
    	Logger.createNewImportLogger();
    }
    
    public void parseXML(Document doc, Element start) throws NullPointerException {
    	primaryLocation = start;
    	
    	if (primaryLocation == null) {
    		primaryLocation = project.getPrimaryModel();
    	}
		
		// Read file and set up XML object
		Node packet = doc.getDocumentElement();
		NodeList dataNodes = packet.getChildNodes();

		// Parse XML and build model based on data
		buildModelMap(dataNodes);
		buildStereotypesTree();
    }
    
	public void createModel() throws NullPointerException {
		project.getOptions().setAutoNumbering(false);
		
		buildModel(profileXML);
		buildModel(completeXML);

		CameoUtils.createSession(project, "Refresh");
		CameoUtils.closeSession(project);
		
		project.getOptions().setAutoNumbering(true);
	}

	public void buildModel(HashMap<String, XMLItem> parsedXML) {
		for (Entry<String, XMLItem> entry : parsedXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			
			if(modelElement == null) {
				Logger.log(String.format("Error parsing data for XML item with id %s. Element will not be imported", id));
			}
			
			if (isImported(modelElement)) {
				continue;
			}
			
			buildEntity(parsedXML, modelElement);
		}	
	}
	
	@CheckForNull
	public Element buildEntity(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if (modelElement == null) {
			Logger.log(String.format("XML data not found attempting to build entity."));
			return null;
		}
		
		String category = modelElement.getCategory();
		
		if(category.isEmpty()) {
			Logger.log(String.format("Element %s with id %s is uncategorized and will not be imported.", modelElement.getName(), modelElement.getImportId()));
			return null;
		}
		
		if (category.equals(SysmlConstants.ELEMENT)) {
			return buildElement(parsedXML, modelElement);
		}
		
		if (category.equals(SysmlConstants.RELATIONSHIP)) {
			return buildRelationship(parsedXML, modelElement);
		}
		
		if (modelElement.getCategory().equals(SysmlConstants.DIAGRAM)) {
			return buildDiagram(parsedXML, modelElement);
		}
		
		return null;
	}
	
	public Element buildDiagram(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {		
		Element owner = getOwnerElement(modelElement, parsedXML);
		
		if (isImported(modelElement)) {
			return getImportedElement(modelElement);
		}
		
		if (!isDiagramSupported(modelElement)) {
			Logger.log(String.format("%s with id %s type is not supported. ", modelElement.getType(), modelElement.getImportId()));
			return null;
		}
		
		CameoUtils.createSession(project, String.format("Create %s Element", modelElement.getType()));
	 
		AbstractDiagram diagram = (AbstractDiagram) cef.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getImportId());
		Diagram newDiagram = (Diagram) diagram.createElement(project, owner, modelElement);
		
		project.getDiagram(newDiagram).open(); 
		trackIds(newDiagram, modelElement);
		
		CameoUtils.closeSession(project);
		
		// Opens and closes its own session while populating the diagram
		populateDiagram(diagram, newDiagram, modelElement, parsedXML);
		
//		Logger.log(String.format("Created diagram %s of type %s and id %s", modelElement.getName(), modelElement.getType(), modelElement.getEAID()));
		return newDiagram;
	}
	
	public Element buildRelationship(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {		
		Element owner = getOwnerElement(modelElement, parsedXML);
		
		if (isImported(modelElement)) {
			return getImportedElement(modelElement);
		}
		
		if(!isRelationshipSupported(modelElement)) { 
			Logger.log(String.format("%s type not supported. Import id %s", modelElement.getType(), modelElement.getImportId()));
			return null;
		}
		
		Element client = getImportedClient(modelElement, parsedXML);
		if(client == null) {
			Logger.log(String.format("Client null for relationship %s. Import id %s", modelElement.getName(), modelElement.getImportId()));
		}
		Element supplier = GetImportedSupplier(modelElement, parsedXML);
		if(supplier == null) {
			Logger.log(String.format("Supplier null for relationship %s. Import id %s", modelElement.getName(), modelElement.getImportId()));
		}
		
		CameoUtils.createSession(project, String.format("Creating CommonRelationship of type %s.",modelElement.getType()));
		CommonRelationship relationship = crf.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getImportId());
		CameoUtils.closeSession(project);
		
		if (relationship == null) {
			Logger.log("Error. Element missing in common elements factory.");
			return null;
		}

		relationship.createDependentElements(parsedXML, modelElement);
		
		CameoUtils.createSession(project, String.format("Creating Relationship of type %s.", modelElement.getType()));
		Element newElement = relationship.createElement(project, owner, client, supplier, modelElement);
		CameoUtils.closeSession(project);
				
		if (newElement == null) {
			Logger.log(String.format("Relationship not created. Type: %s ID: %s with parent %s.", modelElement.getType(), modelElement.getImportId(), modelElement.getParent()));
			return null;
		}
		
		if(newElement.getOwner() == null) {
			newElement.dispose();
			Logger.log("Owner failed to be set including any default owners. Relationship not created.");
			return null;
		}
			
		trackIds(newElement, modelElement);
		relationship.createReferencedElements(parsedXML, modelElement);
		
		return newElement;
	}
	
	public Element buildElement(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {		
		Element owner = getOwnerElement(modelElement, parsedXML);
		
		// Element may be created in the owner's buildElement() call if it is a referenced element.
		if (isImported(modelElement)) {
			return getImportedElement(modelElement);
		}
		
		if(!isElementSupported(modelElement)) { 
			Logger.log(String.format("%s type not supported. Import id %s", modelElement.getType(), modelElement.getImportId()));
			return null;
		}
		
		CameoUtils.createSession(project, String.format("Initialize element %s Element", modelElement.getType()));
		CommonElement element = cef.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getImportId());
		CameoUtils.closeSession(project);
		
		element.createDependentElements(parsedXML, modelElement);
		
		// Create new session if tagged values or other dependent elements are found, built, and session is closed.
		CameoUtils.createSession(project, String.format("Create %s with dependent Elements", modelElement.getType()));
		Element newElement = element.createElement(project, owner, modelElement);
		CameoUtils.closeSession(project);
		
		if(newElement == null) {
			Logger.log(String.format("Element not created. Name: %s Id: %s",  modelElement.getName(), modelElement.getImportId()));
			return owner;
		}
		
		trackIds(newElement, modelElement);
		addStereotypes(newElement, modelElement);
		element.addStereotypeTaggedValues(modelElement);					
		element.createReferencedElements(parsedXML, modelElement);
		
		if(newElement.getOwner() == null) {
			newElement.dispose();
			Logger.log("Owner failed to be set including any default owners. Element with id " + modelElement.getImportId() + " not created.");
			return null;
		}
		
//		Logger.log(String.format("Created element %s of type: %s.", modelElement.getName(), modelElement.getType()));
		return newElement;
	}
	
	//Get stereotypes from parssedXML. Find profiles for those stereotypes. Get stereotypes. Apply stereotypes
	public void addStereotypes(Element newElement, XMLItem modelElement) {
		HashMap<String, String> stereotypes = modelElement.getStereotypes();
		if(!MapUtils.isEmpty(stereotypes)) {
			for(String stereotype : stereotypes.keySet()) {
				addStereotype(newElement, stereotype, stereotypes.get(stereotype));
				addStereotypeFields(newElement, modelElement);
			}
		}
	}
	
	public void buildModelMap(NodeList dataNodes) {
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
						if ((modelElement.getImportId() != null)  && !(modelElement.getImportId().isEmpty())) {
							for (String id : modelElement.getIds()) {
								completeXML.put(id, modelElement);
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
				} if ((modelElement.getImportId() != null)  && !(modelElement.getImportId().isEmpty())) {
					successParsedCount++;
				} else {
					String message = "No ID for " + modelElement.getName() + ". Element will not be parsed correctly.";
					Logger.log(message);
				}
			}
		}
		Logger.log("Successfully parsed " + successParsedCount + "/" + totalElementNodeCount + " data blocks from XML.");
		// 
		for(XMLItem modelElement : properties) {
			if(modelElement.getParent() != null) {
				if(completeXML.get(modelElement.getParent()) != null) {
					if(completeXML.get(modelElement.getParent()).getType().contentEquals(SysmlConstants.STEREOTYPE)) {
						stereotypesXML.put(modelElement.getImportId(), modelElement);
					}
				} else {
					CameoUtils.logGui("No parent found for property with id " + modelElement.getImportId());
				}
			}
		}
	}
	
	public void buildStereotypesTree() {
		for (Entry<String, XMLItem> entry : stereotypesXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			profileXML.put(id,  modelElement);
			String parentID = modelElement.getParent();
			addParentToProfileXML(parentID);
		}
	}
	
	public void addParentToProfileXML(String parentID) {
		if(parentID != "") {
			profileXML.put(parentID, completeXML.get(parentID));
			XMLItem nextParent = completeXML.get(parentID);
			if(nextParent != null) {
				String nextParentID = nextParent.getParent();
				addParentToProfileXML(nextParentID);
			}
		}
	}
	
	public void addStereotype(Element newElement, String stereotypeName, String profileName) {
		//Need to implement mapping of all SysML base stereotypes and which internal library they come from (SysML, MD Customization for SysML, UML Standard Profile, etc.)
		Profile umlStandardProfile = StereotypesHelper.getProfile(project,  "UML Standard Profile");
		if(stereotypeName.contentEquals("metaclass")) {
			Stereotype stereotypeObj = StereotypesHelper.getStereotype(project, "Metaclass", umlStandardProfile);
			StereotypesHelper.addStereotype(newElement,  stereotypeObj);
		} else {
			CameoUtils.logGui("Looking for profile name " + profileName);
			Profile profile = StereotypesHelper.getProfile(project,  profileName);
			if(profile != null) {
				CameoUtils.logGui("Looking for stereotype name " + stereotypeName);
				Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
				if(stereotype != null) {
					StereotypesHelper.addStereotype(newElement,  stereotype);
				}
			}
		}
	}
	
	public void addStereotypeFields(Element newElement, XMLItem xmlElement) {
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
		Logger.log("No ID found in field " + relationship.getNodeName() + ".");
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
			CameoUtils.logGui("Diagram element did not contain sufficient data for placement. Element will be automatically placed on diagram.");
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
						Logger.log("Error parsing stereotype name or profile name from HUDS XML for element: " + modelElement.getImportId());
					}
				} else if (attributeKey.contentEquals(XmlTagConstants.STEREOTYPE_TAGGED_VALUE)) {
					try {
						TaggedValue tv = new TaggedValue(attribute);
						modelElement.addStereotypeTaggedValue(tv);
					} catch (NullPointerException npe) {
						Logger.log("Error parsing stereotype tagged value from HUDS XML for element: " + modelElement.getImportId());
					}
					
				} else if (attributeType.contentEquals(XmlTagConstants.ATTRIBUTE_TYPE_LIST)) {
					try {
						List<Node> listAttributes = getListSubAttributes(attribute);
						for(Node listAttribute : listAttributes) {
							modelElement.addListAttribute(attributeKey, listAttribute.getTextContent());
						}
						
					} catch (NullPointerException npe) {
						Logger.log(String.format("Error parsing attribute for element with id: %s", modelElement.getImportId()));
					}
				} else if (Arrays.asList(XMLItem.LIST_ATTRIBUTES).contains(attributeKey)) {
					try {
						modelElement.addListAttribute(attributeKey, getSubAttribute(attribute).getTextContent());
					} catch (NullPointerException npe) {
						Logger.log(String.format("Error parsing attribute for element with id: %s", modelElement.getImportId()));
					}
				} else {
					try {
						Node subAttribute = getSubAttribute(attribute);
						modelElement.addAttribute(attributeKey, subAttribute.getTextContent());
					} catch (NullPointerException npe) {
						Logger.log("Error parsing attribute for element with id: " + modelElement.getImportId());
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
					modelElement.setImportId(idNode.getTextContent());
				}
			}
		}
		
		if (modelElement.getImportId().trim().isEmpty() && !modelElement.getIds().isEmpty()) {
			modelElement.setImportId(modelElement.getIds().get(0));
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
				CameoUtils.logGui("Item with Key: " + entry.getKey());
				XMLItem modelElement = entry.getValue();
				CameoUtils.logGui("\t and of type: " + modelElement.getType());
				CameoUtils.logGui("\t and with id: " + modelElement.getImportId());
				CameoUtils.logGui("\t and with parent: " + modelElement.getParent());
				CameoUtils.logGui("\t and with name: " + modelElement.getAttribute("name"));
			} catch(NullPointerException npe) {
				CameoUtils.logGui("Null value found for item with key: " + entry.getKey());
			}
		}
	}
	
	public static Project getProject() {
		return getInstance().project;
	}
	
	@CheckForNull
	public static String idConversion(String id) {
		return getInstance().importIdToCameoId.get(id);
	}
	
	public Element getOwnerElement(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		String ownerID = modelElement.getParent();
		XMLItem ownerElement = parsedXML.get(ownerID);
		
		if(modelElement.getParent().trim().isEmpty() || ownerElement == null) {
			if (modelElement.getType() != SysmlConstants.MODEL) {
				Logger.log(String.format("Xml object not found for owner of %s with id %s.", modelElement.getName(), modelElement.getImportId()));
			}
			
			return primaryLocation; // Set owned equal to Primary model if no hasParent attribute in XML -> parent field in XMLItem == ""
		} 
		
		String ownerCreatedID = idConversion(ownerID);
		
		if (ownerCreatedID == null || ownerCreatedID.trim().isEmpty()) {
			return buildEntity(parsedXML, ownerElement);
		}
		
		return (Element) project.getElementByID(ownerCreatedID);
		
	}
	
	@CheckForNull
	public Element getImportedClient(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		if (modelElement.getClient() == null || modelElement.getClient().trim().isEmpty()) {
			Logger.log(String.format("No client id in xml for %s with id %s.", modelElement.getName(), modelElement.getImportId()));
			return null;
		}
		
		if (isImported(modelElement.getClient())) {
			return (Element) getImportedElement(modelElement.getClient());
		}
		
		String clientImportId = modelElement.getClient();
		XMLItem clientElement = parsedXML.get(clientImportId);
		
		if(clientElement != null) {
			return buildElement(parsedXML, clientElement);
		} 
		
		Logger.log(String.format("Client Element with id : %s not exported with XML. "
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
	
	public Element GetImportedSupplier(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		if (modelElement.getSupplier() == null || modelElement.getSupplier().trim().isEmpty()) {
			Logger.log(String.format("No supplier id in xml for %s with id %s.", modelElement.getName(), modelElement.getImportId()));
			return null;
		}
		
		if (parentMap.containsKey(modelElement.getSupplier())) {
			return  (Element) project.getElementByID(parentMap.get(modelElement.getSupplier()));
		}
		
		String supplierImportId = modelElement.getSupplier();
		XMLItem supplierElement = parsedXML.get(supplierImportId);
		
		if(supplierElement != null) {
			return buildElement(parsedXML, supplierElement);
		}
		
		Logger.log(String.format("Supplier element with id : %s not exported with XML. "
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
	
	public void trackIds(Element newElement, XMLItem modelElement) {
		importIdToCameoId.put(modelElement.getImportId(), newElement.getID());
	}
	
	public HashMap<Element, Rectangle> getImportedElementsOnDiagram(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		HashMap<Element, Rectangle> elementsOnDiagram = new HashMap<Element, Rectangle> ();
		
		for(String importId : modelElement.getChildElements(parsedXML)) {
			if(!isImported(importId)) {
				Logger.log(String.format("Element with import id %s failed to be created before populating diagram.", importId));
				continue;
			}
			
			Element elementOnDiagram = getImportedElement(importId);
			
			if(elementOnDiagram == null) {
				Logger.log(String.format("Failed to find created element with import id %s", importId));
				continue;
			}
			
			elementsOnDiagram.put(elementOnDiagram, modelElement.getLocation(importId));
		}
		
		return elementsOnDiagram;
	}
	
	public List<Element> getImportedRelationshipsOnDiagram(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		List<Element> relationshipsOnDiagram = new ArrayList<Element> ();
				
		for(String importId : modelElement.getChildRelationships(parsedXML)) {
			if(!isImported(importId)) {
				Logger.log(String.format("Element with import id %s failed to be created before populating diagram.", importId));
				continue;
			}
			
			Element relationshipOnDiagram = getImportedElement(importId);
			
			if(relationshipOnDiagram == null) {
				Logger.log(String.format("Failed to find created element with import id %s", importId));
				continue;
			}
			
			relationshipsOnDiagram.add(relationshipOnDiagram);
		}
		return relationshipsOnDiagram;
	}
	
	public void populateDiagram(AbstractDiagram diagram, Diagram newDiagram, XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		diagram.createDependentElements(parsedXML, modelElement);
		
		HashMap<Element, Rectangle> elementsOnDiagram = getImportedElementsOnDiagram(modelElement, parsedXML);
		List<Element> relationshipsOnDiagram = getImportedRelationshipsOnDiagram(modelElement, parsedXML);
			
		CameoUtils.createSession(project, String.format("Adding elements to diagram with type %s.", modelElement.getType()));
				
		boolean noPosition = diagram.addElements(project, newDiagram, elementsOnDiagram, modelElement);
		
		if(noPosition) {
			Application.getInstance().getProject().getDiagram(newDiagram).layout(true, new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());
		}
		
		try {
			diagram.addRelationships(project, newDiagram, relationshipsOnDiagram);
		} catch (ReadOnlyElementException roee) {
			Logger.log("Read only element exception encountered populating diagram.");
		}
		
		project.getDiagram(newDiagram).close(); 
		CameoUtils.closeSession(project);
	}
	
	public Set<String> getImportedElementIds() {
		return new HashSet<String>(importIdToCameoId.values());
	}
	
	public Set<String> getUnsupportedElementIds() {
		return unsupportedElementIds;
	}
	
	Element getImportedElement(XMLItem modelElement) {
		return (Element) project.getElementByID(importIdToCameoId.get(modelElement.getImportId()));
	}
	
	public Element getImportedElement(String importId) {
		return (Element) project.getElementByID(importIdToCameoId.get(importId));
	}
	
	boolean isImported(XMLItem modelElement) {
		if (!importIdToCameoId.keySet().contains(modelElement.getImportId())) {
			return false;
		}
		
		return true;
	}
	
	boolean isImported(String id) {
		if (!importIdToCameoId.keySet().contains(id)) {
			return false;
		}
		
		return true;
	}
}
