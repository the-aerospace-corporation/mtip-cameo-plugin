package org.aero.huddle.XML.Import;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.ModelElements.CommonElementsFactory;
import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.ModelElements.CommonRelationshipsFactory;
import org.aero.huddle.ModelElements.ModelDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.apache.commons.collections.MapUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

public class ImportXmlSysml {
    static Map<String,Entry<Element, Element>> linktoPair = new HashMap<String,Entry<Element, Element>>();
    static Map<ModelDiagram,Diagram> diagramMap = new HashMap<ModelDiagram,Diagram>();
    private static HashMap<String, XMLItem> completeXML = new HashMap<String, XMLItem>();
    private static HashMap<String, XMLItem> stereotypesXML = new HashMap<String, XMLItem>();
    private static HashMap<String, XMLItem> profileXML = new HashMap<String, XMLItem>();
    private static Project project = Application.getInstance().getProject();
    private static HashMap<String, String> parentMap = new HashMap<String, String>();
    
    //Variables for Automatic Validation Creation
	public static final boolean CREATE_VALIDATION_ON_IMPORT = true;
    private static Element MODEL_VALIDATION_PACKAGE = null;
	private static Element CHECK_CLASSES = null;
	private static Element CHECK_RELATIONSHIPS = null;
//	possible way to get active project instead of random project
//    ProjectsManager pm = Application.getInstance().getProjectsManager();
//    Project p = pm.getActiveProject();   
    
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
		MODEL_VALIDATION_PACKAGE = null;
		CHECK_CLASSES = null;
		CHECK_RELATIONSHIPS = null;
		ImportLog.reset();
    }
    
    public static void parseXML(Document doc, Element start) throws NullPointerException{
    	if(start == null) {
			primaryLocation = project.getPrimaryModel();
		} else {
			primaryLocation = start;
		}
		CameoUtils.logGUI("Mounting project on package," + primaryLocation.getHumanName() + " with id: " + primaryLocation.getLocalID());
		
		// Read file and set up XML object
		Node packet = doc.getDocumentElement();
		NodeList dataNodes = packet.getChildNodes();

		// Parse XML and build model based on data
		buildModelMap(dataNodes);
		buildStereotypesTree();
    }
    
	public static void createModel() throws NullPointerException {
		buildModel(profileXML);
		if(CREATE_VALIDATION_ON_IMPORT) {
			if(!profileXML.isEmpty() && !stereotypesXML.isEmpty()) {
				buildValidationSuite();
			}
		}
		buildModel(completeXML);
		ImportLog.save();
	}

	public static void buildModel(HashMap<String, XMLItem> parsedXML) {
		for (Entry<String, XMLItem> entry : parsedXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			
			CameoUtils.logGUI(modelElement.getEAID());
			if (modelElement.getCategory().equals(SysmlConstants.ELEMENT)) {
				if (parentMap.get(id) == null) {
					buildElement(project, parsedXML, modelElement, id);
				}
			}
			if (modelElement.getCategory().equals(SysmlConstants.RELATIONSHIP)) {
				if (parentMap.get(id) == null) {
					buildRelationship(project, parsedXML, modelElement, id);
				}
			}
		}

		for (Entry<String, XMLItem> entry : parsedXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			
//			CameoUtils.logGUI("Building node: keyid: " +id);
//			CameoUtils.logGUI("Building node: name: " + modelElement.getName());
//			CameoUtils.logGUI("Building node: cameoID: " + modelElement.getCameoID());
//			CameoUtils.logGUI("Building node: eaID: " + modelElement.getEAID());
//			CameoUtils.logGUI("Building node: category: " + modelElement.getCategory());
//			CameoUtils.logGUI("Building node: type: " + modelElement.getType());
	
			if (modelElement.getCategory().equals(SysmlConstants.DIAGRAM)) {
//				CameoUtils.logGUI("Building Diagram from next XMLItem");
				if (parentMap.get(id) == null) {
//					CameoUtils.logGUI("id was not null");
					buildDiagram(project, parsedXML, modelElement, id);
				}
			}
		}			
	}

	public static Element tryToGetElement(Project project, Map<String, XMLItem> parsedXML, String eaID) {
		String cameoUnique = parentMap.get(eaID);

		if (cameoUnique == null) {
			ImportLog.log("tryToGetElement: Cannot get Element of ID: " + eaID);
			return null;

		} else {
			Element element = (Element) project.getElementByID(cameoUnique);
			return element;
		}
	}
	
	public static Element getOrBuildElement(Project project, Map<String, XMLItem> parsedXML, String eaID) {
		String cameoUnique = parentMap.get(eaID);

		if (cameoUnique == null) {
			XMLItem newItem = parsedXML.get(eaID);
			return buildElement(project, parsedXML, newItem, eaID);

		} else {
			Element element = (Element) project.getElementByID(cameoUnique);
			return element;
		}
	}
	
	public static Element buildDiagram(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement, String id) {
		
		CommonElementsFactory cef = new CommonElementsFactory();
		Element owner = null;
		String cameoParentID = "";
		if(modelElement.getParent().equals("")) {
			owner = primaryLocation;
		} else {
			cameoParentID = parentMap.get(modelElement.getParent());
			if(cameoParentID != null) {
				owner = (Element) project.getElementByID(cameoParentID);
			}
			else {
				String ownerID = modelElement.getParent();
				XMLItem ownerElement = parsedXML.get(ownerID);
				owner = buildElement(project, parsedXML, ownerElement, ownerID);
			}
		}
		
		CameoUtils.logGUI("Creating DIAGRAM of type: " + modelElement.getType() + " and id: " + modelElement.getEAID() + " with parent " + modelElement.getParent() + ".");
		CommonElement element = cef.createElement(modelElement.getType(), modelElement.getAttribute("name"),
				modelElement.getEAID());
		Element newElement = element.createElement(project, owner, modelElement);
		String GUID = newElement.getID();
		modelElement.setCameoID(GUID);
		parentMap.put(id, GUID);
		
		//Orig
//		List<String> elementsStrings = modelElement.getChildElements(parsedXML);
//		List<String> elementsLocations = modelElement.getChildElements(parsedXML);
//		List<Rectangle> elementsRectangles = elementsStrings.stream().map(s -> {
//			Rectangle rect = modelElement.getLocation(s);
//			return rect;
//		}).collect(Collectors.toList());
		
		//Filter first by diagramType
		List<String> origStrings = modelElement.getChildElements(parsedXML);
		List<String> elementsStrings= new ArrayList<String>();
		
		String diagramType = modelElement.getType();
		List<String> diagramAllowedTypes = 	Arrays.asList(SysmlConstants.diagramTypeMap.get(diagramType));
				
//		for (String item: diagramAllowedTypes) {
//			CameoUtils.logGUI("Diagram,ALLOWED type: " + item);
//		}
		
		for (String str : origStrings) {
			String rawType = modelElement.getChildElementType(str);
			rawType = rawType.replaceAll("\\s+", "");
			CameoUtils.logGUI("Diagram,rawTyped: " + rawType);

			String type = rawType.split(Pattern.quote("."))[1];

			CameoUtils.logGUI("Diagram, element id: " + str);

			CameoUtils.logGUI("Diagram, element type: " + type);

			// if (diagramAllowedTypes.contains(type)) {
			if (CameoUtils.containsIgnoreCase(diagramAllowedTypes, type)) {
				elementsStrings.add(str);
				CameoUtils.logGUI("Diagram, ADDING element type: " + type);
			} else {
				CameoUtils.logGUI("Diagram, **NOT** ADDING element type: " + type);
				XMLItem item = parsedXML.get(str);
				if (item != null) {
					ImportLog.log("Element not added to diagram. Name: " + item.getAttribute("name") + " type: " + type + " ID: "
							+ item.getEAID());
				} else {
					ImportLog.log("Element not added to diagram. ID: " + str + " type: " + type);
				}
			}
		}
		
		List<Rectangle> elementsRectangles = elementsStrings.stream().map(s -> {
			Rectangle rect = modelElement.getLocation(s);
			return rect;
		}).collect(Collectors.toList());

		CameoUtils.logGUI("ElementStrings size: " + elementsStrings.size());
		for (String s: elementsStrings) {
			CameoUtils.logGUI("ADDING element id: " + s);
		}

		//map each string id to an actual element, and for all non-null created elements, add them to the elements list.
		List<Element> elements = elementsStrings.stream().map(s->(Element) tryToGetElement(project, parsedXML, s)).filter(x -> x!=null).collect(Collectors.toList());
		
		CameoUtils.logGUI("modelElement.getType() : " + modelElement.getType() );
		CameoUtils.logGUI("elementsAFTER size: " + elements.size());
     
		// add the composed elements
		((ModelDiagram) element).addElements(project, (Diagram) newElement, elements, elementsRectangles);

        diagramMap.put((ModelDiagram) element, (Diagram) newElement);
        modelElement.setCameoID(newElement.getID());

		// add the associations
        return newElement;
	}
	
	public static Element buildRelationship(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement, String id) {
		CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
		String ownerID = modelElement.getParent();
		XMLItem ownerElement = parsedXML.get(ownerID);
		Element owner = null;
		
		String cameoParentID = "";
		String clientCameoID = "";
		String supplierCameoID = "";
		if(ownerID.equals("")) {
			owner = primaryLocation;
		} else if (!ownerElement.getCameoID().equals("")) {
		    cameoParentID = ownerElement.getCameoID();
			owner = (Element) project.getElementByID(cameoParentID);
		} else {
			if(modelElement.getCategory().equals(SysmlConstants.ELEMENT)){
				owner = buildElement(project, parsedXML, ownerElement, ownerID);
			}
			if(modelElement.getCategory().equals(SysmlConstants.DIAGRAM)) {
				//owner = buildDiagram(project, parsedXML, modelElement, ownerID);
			}			
		}
		
		CameoUtils.logGUI("Creating relationship of type: " + modelElement.getType() + " and id: " + modelElement.getEAID() + " with parent " + modelElement.getParent() + ".");
		CommonRelationship relationship = crf.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
		
		//Get client element in Cameo if it exists or create element and set new element to client
		clientCameoID = parentMap.get(modelElement.getClient());
		CameoUtils.logGUI("....with Client cameo ID: " + clientCameoID + "  and EA ID: " + modelElement.getClient());
		Element client = null;
		if(clientCameoID != null) {
			client = (Element) project.getElementByID(clientCameoID);
		} else {
			String clientEAID = modelElement.getClient();
			if(clientEAID.startsWith("_")) {
				client = (Element)project.getElementByID(clientCameoID);
			}
			
			if(clientEAID.startsWith("_9_") && client == null) {
				client = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
				CameoUtils.logGUI(client.getLocalID());
			}
			if(client == null) {
				XMLItem clientElement = parsedXML.get(clientEAID);
				client = buildElement(project, parsedXML, clientElement, clientEAID);
			}
		}
		
		//Get supplier element in Cameo if it exists or create element and set new element to supplier
		supplierCameoID = parentMap.get(modelElement.getSupplier());
		
		CameoUtils.logGUI("....with Supplier cameo ID: " + supplierCameoID + "  and EA ID: " + modelElement.getSupplier());
		Element supplier = null;
		if(supplierCameoID != null) {
			supplier = (Element)project.getElementByID(supplierCameoID);
		} else {
			//if supplier not an element of model, check if it exists in an external/auxiliary library.
			String supplierEAID = modelElement.getSupplier();
			if(supplierEAID.startsWith("_")) {
				supplier = (Element) project.getElementByID(supplierEAID);
			}

			if(supplier == null) {
				XMLItem supplierElement = parsedXML.get(supplierEAID);
				supplier = buildElement(project, parsedXML, supplierElement, supplierEAID);
			}
		}
		
		if(modelElement.getType().equals(SysmlConstants.OBJECTFLOW) || modelElement.getType().equals(SysmlConstants.CONTROLFLOW)) {
			try {
				if(!(owner instanceof Activity)) {
					owner = CameoUtils.findNearestActivity(project, supplier);
				}
			} catch(NullPointerException npe) {
				String logMessage = "Invalid parent. Parent invalid for element " + modelElement.getName() + " with id " + modelElement.getEAID() + ". Element could not be placed in model.";
				ImportLog.log(logMessage);
				return null;
			}
		} else if(modelElement.getType().equals(SysmlConstants.TRANSITION)) {
			owner = CameoUtils.findNearestRegion(project, supplier);
			if(owner == null) {
				String logMessage = "Invalid parent. Parent invalid for element " + modelElement.getName() + " with id " + modelElement.getEAID() + ". Element could not be placed in model.";
				ImportLog.log(logMessage);
				return null;
			}
		} else if (modelElement.getType().equals(SysmlConstants.BINDINGCONNECTOR) || modelElement.getType().equals(SysmlConstants.CONNECTOR)) {
			// owner is unchanged.
		} else {
			// Check to see if supplier element exists in the model being imported or exists in an external library.
			if(parentMap.containsKey(supplier.getLocalID())) {
				owner = CameoUtils.findNearestPackage(project,  supplier);
			} else {
				owner = CameoUtils.findNearestPackage(project,  client);
			}
			
		}
		if(modelElement.getType().equals(SysmlConstants.ASSOCIATION) && (supplier instanceof Port || client instanceof Port)) {
			return null;
		}
		//Create relationship in Cameo 
		if (!modelElement.isCreated()) {
			CameoUtils.logGUI("Physically creating Relationship in Cameo");
			CameoUtils.logGUI("Setting owner to elemnt with id " + owner.getLocalID());
			Element newElement = relationship.createElement(project, owner, client, supplier, modelElement);
			if(newElement != null) {
				String GUID = newElement.getID();
				parentMap.put(id, GUID);
				modelElement.setCameoID(GUID);
				Map.Entry<Element, Element> entry = new AbstractMap.SimpleEntry<Element, Element>(supplier, client);
				linktoPair.put(id, entry);

				return newElement;
			}
			return null;
		} else {
			CameoUtils.logGUI("Relationship already created!");
			return null;
		}
	}
	
	public static Element buildElement(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement, String id) {
		if(modelElement.hasAttribute("name")) {
			CameoUtils.logGUI("Starting Build Element with name: " + modelElement.getAttribute("name") + " of type: " + modelElement.getType() + " and id: " + modelElement.getEAID());
		} else {
			CameoUtils.logGUI("Starting Build Element with no name of type: " + modelElement.getType() + " and id: " + modelElement.getEAID());
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		String ownerID = modelElement.getParent();
		XMLItem ownerElement = parsedXML.get(ownerID);
		Element owner = null;
		
		if(modelElement.getParent().isEmpty() || ownerElement == null) {
			// Set owned equal to Primary model if no hasParent attribute in XML -> parent field in XMLItem == ""
			owner = primaryLocation;
		} else if(!ownerElement.getCameoID().equals("")){
			// Check if parent Element has been created. If parent created, parentMap will return a non null string cameo ID. 
			owner = (Element) project.getElementByID(ownerElement.getCameoID());
		} else {
			String category = ownerElement.getCategory();
			if(category.equals(SysmlConstants.ELEMENT)) {
				owner = buildElement(project, parsedXML, ownerElement, ownerElement.getEAID());
			} else if (category.equals(SysmlConstants.RELATIONSHIP)) {
				owner = buildRelationship(project, parsedXML, ownerElement, ownerElement.getEAID());
			} else if (category.equals(SysmlConstants.DIAGRAM)) {
			//	owner = buildDiagram(project, parsedXML, ownerElement, ownerElement.getEAID());
			} else {
				CameoUtils.logGUI("Element of type " + modelElement.getType() + " is not currently supported");
			}
		}

		if(!modelElement.isCreated()) {
			if(ownerElement != null) {
				CameoUtils.logGUI("Creating element " + modelElement.getAttribute("name") + " of type: " + modelElement.getType() + " and id: " + modelElement.getEAID() + " with parent " + ownerElement.getAttribute("name") + " with id " + ownerElement.getParent() + "and cameo id " + ownerElement.getCameoID());
			} else {
				CameoUtils.logGUI("Creating " + modelElement.getAttribute("name") + " of type: " + modelElement.getType() + " with no initial owner.");
			}
			if (!SessionManager.getInstance().isSessionCreated(project)) {
				SessionManager.getInstance().createSession(project, "Create " +  modelElement.getType() + " Element");
			}
			
			if(Arrays.asList(SysmlConstants.SYSMLELEMENTS).contains(modelElement.getType())) {
				CommonElement element = cef.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
				element.createDependentElements(project, parsedXML, modelElement);
				Element newElement = element.createElement(project, owner, modelElement);
				if(newElement != null) {
					String GUID = newElement.getID();
					modelElement.setCameoID(GUID);
					parentMap.put(id, GUID);
					addStereotypes(newElement, modelElement);
					return newElement;
				} else {
					ImportLog.log("Element not created. Name: " + modelElement.getAttribute("name") + ". ID: " + modelElement.getEAID());
				}
			}
		} else {
			return (Element) project.getElementByID(modelElement.getCameoID());
		}
		return owner;
	}
	
	//Get stereotypes from paredXML. Find profiles for those stereotypes. Get stereotypes. Apply stereotypes
	public static void addStereotypes(Element newElement, XMLItem modelElement) {
		HashMap<String, String> stereotypes = modelElement.getStereotypes();
		if(!MapUtils.isEmpty(stereotypes)) {
			for(String stereotype : stereotypes.keySet()) {
				addStereotype(newElement, stereotype, stereotypes.get(stereotype));
				addStereotypeFields(newElement, modelElement);
			}
		}
	}
	
	public static HashMap<String, XMLItem> buildModelMap(NodeList dataNodes) {
		HashMap<String, XMLItem> modelElements = completeXML;
		
		//Loop through all of the <data> nodes
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList fieldNodes = dataNode.getChildNodes();
				XMLItem modelElement = new XMLItem();
				//Loop through all of the fields in each dataNode
				for(int j = 0; j < fieldNodes.getLength(); j++) {
					Node fieldNode = fieldNodes.item(j);
					
					if(fieldNode.getNodeType() == Node.ELEMENT_NODE) {
						//get model element type (ex. Sysml.Package)
						if(fieldNode.getNodeName().contentEquals("type")) {
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
					//	if(modelElement.getEAID() != null) {
						if ((modelElement.getEAID() != null)  && !(modelElement.getEAID().isEmpty())) {
							modelElements.put(modelElement.getEAID(),  modelElement);
							if(modelElement.getType().contentEquals("Stereotype")) {
								stereotypesXML.put(modelElement.getEAID(), modelElement);
							}
						}
					}
				}
			}
		}
		completeXML = modelElements;
		return modelElements;
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
	public static void buildValidationSuite() {
		createModelValidationPackage();
		findStereotypes(project.getPrimaryModel());
		
	}
	
	/**
	 * Creates a package under the primary model of the project with the validationSuite stereotype
	 * to hold constraints for the model validations automatically created on profile/metamodel import.
	 * @param project Current project the metamodel/profile is being imported into.
	 * @param element
	 */
	public static void createModelValidationPackage() {
		ElementsFactory f = project.getElementsFactory();
		Profile umlProfile = StereotypesHelper.getProfile(project, "Validation Profile");
		Stereotype validationSuite = StereotypesHelper.getStereotype(project, "validationSuite", umlProfile);
		MODEL_VALIDATION_PACKAGE = f.createPackageInstance();
		((NamedElement)MODEL_VALIDATION_PACKAGE).setName("Model Validation");
		MODEL_VALIDATION_PACKAGE.setOwner(project.getPrimaryModel());
		StereotypesHelper.addStereotype(MODEL_VALIDATION_PACKAGE, validationSuite);
	}
	
	private static void findStereotypes(Package pack) {
		//Write Package to xml here so parent is written before child
		
		//Look for child packages and child elements to recursively export
		Collection<Element> elementsInPackage = new ArrayList<Element> ();
		Collection<Package> packagesInPackage = new ArrayList<Package> ();
		
		boolean noPackages = false;
		boolean noElements = false;
		
		try {
			elementsInPackage = pack.getOwnedElement();
		} catch(NullPointerException e) {
			noElements = true;
		}
		
		try {
			packagesInPackage = pack.getNestedPackage();
		} catch(NullPointerException e) {
			noPackages = true;
		}
		
		if(!noElements) {
			for(Element element : elementsInPackage) {
				if(element instanceof Stereotype) {
					boolean isRelationship = isMetaclassRelationship(element);
					if(!isRelationship) {
						if(CHECK_CLASSES == null) {
							createCheckClasses(element);
						} else {
							updateCheckClasses(element);
						}
					} else {
						if(CHECK_RELATIONSHIPS == null) {
							createCheckRelationships(element);
						} else {
							updateCheckRelationships(element);
						}
					}
				}
			}
		}
						
		if(!noPackages) {
			for(Package nextPackage : packagesInPackage) {
				//Description: Get stereotypes of package. Packages with stereotypes (Ex. auxiliaryResource, modellibrary) should not be exported
				Profile magicdrawProfile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
				Stereotype auxiliaryStereotype = StereotypesHelper.getStereotype(project,  "auxiliaryResource", magicdrawProfile);
				List<Stereotype> packageStereotypes = StereotypesHelper.getStereotypes(nextPackage);
				
				// Check if package is editable -- external dependencies and imported projects will be read-only (not editable) 
				if(!packageStereotypes.contains(auxiliaryStereotype) && !nextPackage.getHumanName().equals("Package Unit Imports")) {
//					CameoUtils.logGUI("Package with name " + nextPackage.getHumanName() + "\twith type: " + nextPackage.getHumanType());
//					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Exporting Package " + nextPackage.getHumanName());
					findStereotypes(nextPackage);					
				}
			}
		}
	}
	
	/**
	 * Checks the stereotype's metaclass to determine if it is a relationship.
	 * @return True if the
	 */
	public static boolean isMetaclassRelationship(Element element) {
		Stereotype stereotype = (Stereotype)element;
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class relationshipMetaclass = StereotypesHelper.getMetaClassByName(project, "Relationship");
		if(StereotypesHelper.hasSuperMetaClass(stereotype, relationshipMetaclass)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a constraint in the model validation folder that validates whether element classes used in the 
	 * model are of this type. Constraint uses OCL 2.0.
	 * @param project Project that is being imported to (i.e. current project).
	 * @param element Stereotype being imported into the model.
	 */
	public static void createCheckClasses(Element element) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Constraint Element");
		}
		String elementName = ((NamedElement)element).getName();
		if(!elementName.contains(" ") && !elementName.contains("&")) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = f.createConstraintInstance();
			((NamedElement)constraint).setName("checkClasses");
			constraint.setOwner(MODEL_VALIDATION_PACKAGE);
			
			//Get UML Class element from metamodel and set as constrained element
			Element umlClass = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
			constraint.getConstrainedElement().add(umlClass);
			
			// Create Opaque Expression which holds the OCL expression for model validation
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = f.createOpaqueExpressionInstance();
			ValueSpecification vs = (ValueSpecification) oe;
			constraint.setSpecification(vs);
			
			//Set value specification text here
			String body = "";
			oe.getLanguage().add("OCL2.0");
			
			NamedElement profile = (NamedElement) CameoUtils.findNearestProfile(project, element);
			String profileName = profile.getName();
			body = org.aero.huddle.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
			oe.getBody().add(body);
					
			//Set stereotype of constraint to validationRule
			com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile validationProfile = StereotypesHelper.getProfile(project,  "Validation Profile");
			Stereotype validationRule = StereotypesHelper.getStereotype(project, "validationRule", validationProfile);
			StereotypesHelper.addStereotype(constraint, validationRule);
			
			//Set Severity
			StereotypesHelper.setStereotypePropertyValue(constraint, validationRule, "severity", "error");
			
			//Set error message
			StereotypesHelper.setStereotypePropertyValue(constraint, validationRule, "errorMessage", "Class is not supported by profile.");
			
			CHECK_CLASSES = constraint;		
		}
		SessionManager.getInstance().closeSession(project);
	}
	
	/**
	 * Updates existing check classes constraint as a part of the automatic model validation set-up on import. Adds
	 * an oclIsKindOf expression to the existing check classes constraint using an 'or' expression in OCL 2.0.
	 * @param project
	 * @param element
	 */
	public static void updateCheckClasses(Element element) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Constraint Element");
		}
		String elementName = ((NamedElement)element).getName();
		if(!elementName.contains(" ") && !elementName.contains("&")) {
			OpaqueExpression oe = (OpaqueExpression) ((Constraint)CHECK_CLASSES).getSpecification();
			List<String> bodies = oe.getBody();
			String currentBody = null;
			Iterator<String> bodyIter = bodies.iterator();
			
			if(bodyIter.hasNext()) {
				currentBody = bodyIter.next();
			}
			String profileName = ((NamedElement)CameoUtils.findNearestProfile(project, element)).getName();
			currentBody = currentBody + " or " + org.aero.huddle.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
			oe.getBody().clear();
			oe.getBody().add(currentBody);
			
			oe.getLanguage().clear();
			oe.getLanguage().add("OCL2.0");
		} else {
			// Log stereotype name prevents it from being included in the validation results
			// Elements with this stereotype will be in violation of the validation rules as a result.
		}
		
		SessionManager.getInstance().closeSession(project);
	}
	
	/**
	 * Creates a constraint in the model validation folder that validates whether relationship classes used in the 
	 * model are of this type. Constraint uses OCL 2.0.
	 * @param project Project that is being imported to (i.e. current project).
	 * @param element Stereotype being imported into the model.
	 */
	public static void createCheckRelationships(Element element) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Constraint Element");
		}
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = f.createConstraintInstance();
		((NamedElement)constraint).setName("checkRelationships");
		constraint.setOwner(MODEL_VALIDATION_PACKAGE);
		
		//Get UML Class element from metamodel and set as constrained element
		Element umlClass = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
		constraint.getConstrainedElement().add(umlClass);
		
		// Create Opaque Expression which holds the OCL expression for model validation
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = f.createOpaqueExpressionInstance();
		ValueSpecification vs = (ValueSpecification) oe;
		constraint.setSpecification(vs);
		
		//Set value specification text here
		String language = "OCL 2.0";
		String body = "";
		oe.getLanguage().add(language);
		
		NamedElement profile = (NamedElement) CameoUtils.findNearestProfile(project, element);
		String profileName = profile.getName();
		String elementName = ((NamedElement)element).getName();
		body = org.aero.huddle.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
		oe.getBody().add(body);
				
		//Set stereotype of constraint to validationRule
		com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile validationProfile = StereotypesHelper.getProfile(project,  "Validation Profile");
		Stereotype validationRule = StereotypesHelper.getStereotype(project, "validationRule", validationProfile);
		StereotypesHelper.addStereotype(constraint, validationRule);
		
		//Set Severity
		StereotypesHelper.setStereotypePropertyValue(constraint, validationRule, "severity", "error");
		
		//Set error message
		StereotypesHelper.setStereotypePropertyValue(constraint, validationRule, "errorMessage", "Class is not supported by profile.");
		
		CHECK_RELATIONSHIPS = constraint;		
		SessionManager.getInstance().closeSession(project);
	}
	
	/**
	 * Updates existing check relationships constraint as a part of the automatic model validation set-up on import. 
	 * Adds an oclIsKindOf expression to the existing check relationships constraint using an 'or' expression in OCL 2.0.
	 * @param project
	 * @param element
	 */
	public static void updateCheckRelationships(Element element) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Update Check Relationships Validation");
		}
		OpaqueExpression oe = (OpaqueExpression) ((Constraint)CHECK_RELATIONSHIPS).getSpecification();
		List<String> bodies = oe.getBody();
		String currentBody = null;
		Iterator<String> bodyIter = bodies.iterator();
		
		if(bodyIter.hasNext()) {
			currentBody = bodyIter.next();
		}
		String profileName = ((NamedElement)CameoUtils.findNearestProfile(project, element)).getName();
		String elementName = ((NamedElement)element).getName();
		currentBody = currentBody + " or " + org.aero.huddle.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
		oe.getBody().clear();
		oe.getBody().add(currentBody);
		
		SessionManager.getInstance().closeSession(project);
	}
	
	public static void addStereotype(Element newElement, String stereotypeName, String profileName) {
		//Need to implement mapping of all SysML base stereotypes and which internal library they come from (SysML, MD Customization for SysML, UML Standard Profile, etc.)
		Profile umlStandardProfile = StereotypesHelper.getProfile(project,  "UML Standard Profile");
		if(stereotypeName.contentEquals("metaclass")) {
			Stereotype stereotypeObj = StereotypesHelper.getStereotype(project, "Metaclass", umlStandardProfile);
			StereotypesHelper.addStereotype(newElement,  stereotypeObj);
		} else {
			CameoUtils.logGUI("Looking for profile name " + profileName);
			Profile profile = StereotypesHelper.getProfile(project,  profileName);
			if(profile != null) {
				CameoUtils.logGUI("Looking for stereotype name " + stereotypeName);
				Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
				if(stereotype != null) {
					StereotypesHelper.addStereotype(newElement,  stereotype);
				}
			}
		}
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
			Node idNode = idNodes.item(k);
			if (idNode.getNodeType() == Node.ELEMENT_NODE) {
				if (idNode.getNodeName() == "hasParent") {
					modelElement.setParent(idNode.getTextContent());
				}
				if (idNode.getNodeName() == "element") {
					modelElement.addChildElement(idNode.getTextContent());

					// ingest optional attributes if defined
					NamedNodeMap elementAttributes = idNode.getAttributes();

					if (elementAttributes != null) {
						int x = 0;
						int y = 0;
						int width = 0;
						int height = 0;

						int bottom = 0;
						int right = 0;
						String type = "";
						int numAttrs = elementAttributes.getLength();
						for (int i = 0; i < numAttrs; i++) {
							Attr attr = (Attr) idNode.getAttributes().item(i);
							String attrName = attr.getNodeName();
							String attrValue = attr.getNodeValue();
							// System.out.println("\t[" + attrName + "]=" + attrValue);
							// modelElement.addAttribute(attrName, attrValue);
//							top y
//							left x
//							bottom y+height
//							right x + width
							if (attrName.contentEquals("top")) {
								y = Integer.parseInt(attrValue);
							} else if (attrName.contentEquals("left")) {
								x = Integer.parseInt(attrValue);
							} else if (attrName.contentEquals("bottom")) {
								bottom = Integer.parseInt(attrValue);
							} else if (attrName.contentEquals("right")) {
								right = Integer.parseInt(attrValue);
							} else if (attrName.contentEquals("type")) {
								type = attrValue;
							}
						}

						// convert from EA standard to Cameo standard
						y = -y;
						bottom = -bottom;

						width = right - x;
						height = bottom - y;
						modelElement.addLocation(idNode.getTextContent(), new Rectangle(x, y, width, height));
						modelElement.addChildElementType(idNode.getTextContent(), type);
					}

				}
				
				if (idNode.getNodeName().contentEquals(XmlTagConstants.SUPPLIER)
						|| idNode.getNodeName().contentEquals(XmlTagConstants.CLIENT)) {
					modelElement.addAttribute(idNode.getNodeName(), idNode.getTextContent());
				}
				if (idNode.getNodeName() == XmlTagConstants.CLASSIFIED_BY) {
					modelElement.addAttribute(XmlTagConstants.CLASSIFIED_BY, idNode.getTextContent());
				}
			}
		}
		return modelElement;
	}
	
	
	public static XMLItem getAttributes(Node fieldNode, XMLItem modelElement) {	
		NodeList attributeNodes = fieldNode.getChildNodes();
		
		for(int k = 0; k < attributeNodes.getLength(); k++) {
			Node attribute = attributeNodes.item(k);
			if(attribute.getNodeType() == Node.ELEMENT_NODE) {
				if(attribute.getNodeName().contentEquals("stereotype")) {
					org.w3c.dom.Element attributeElement = (org.w3c.dom.Element)attribute;
					if(attributeElement.getAttribute("profile") == "") {
						if(attribute.getTextContent().contains(".")) {
							String prefix = attribute.getTextContent().split(Pattern.quote("."))[0];
							String postfix = attribute.getTextContent().split(Pattern.quote("."))[1];
//							CameoUtils.logGUI("Adding stereotype " + postfix + "with profile " + prefix);
							modelElement.addStereotype(postfix, prefix);
						} else {
							modelElement.addStereotype(attribute.getTextContent(), attributeElement.getAttribute("profile"));
						}
					} else {
						modelElement.addStereotype(attribute.getTextContent(), attributeElement.getAttribute("profile"));
					}
				} else if (attribute.getNodeName().contentEquals("relationshipStereotype")) {
					org.w3c.dom.Element attributeElement = (org.w3c.dom.Element)attribute;
					modelElement.addRelationshipStereotype(attributeElement.getAttribute("profile"), attribute.getTextContent());
					modelElement.addAttribute("customizationTargetID", attributeElement.getAttribute("id"));
				} else {
					modelElement.addAttribute(attribute.getNodeName(), attribute.getTextContent());
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
				if(idNode.getNodeName().contentEquals(XmlTagConstants.HUDDLE_ID)) {
					modelElement.setEAID(idNode.getTextContent());
					return modelElement;
				}
				modelElement.setEAID(idNode.getTextContent());
			}
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
	@SuppressWarnings("unused")
	public static void testAllCommonElements() {
		Project project = Application.getInstance().getProject();
		Element owner = project.getPrimaryModel();
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
		
		CameoUtils.logGUI("Creating Block");
		CommonElement block = cef.createElement("Block", "TestBlock", "EA_123");
		Element cameoBlock = block.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Package");
		CommonElement sysmlPackage = cef.createElement("Package", "testPackage", "EA_123");
		sysmlPackage.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Operation");
		CommonElement operation = cef.createElement("Operation", "TestOperation", "EA_123");
		operation.createElement(project, cameoBlock, null);
		
		CameoUtils.logGUI("Creating State Machine");
		CommonElement statemachine =cef.createElement("StateMachine", "TestStateMachine", "EA_123");
		Element cameoStateMachine = statemachine.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Activity");
		CommonElement activity = cef.createElement("Activity",  "TestActivity",  "EA_123");
		Element cameoActivity = activity.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Property");
		CommonElement property = cef.createElement("Property", "TestProeprty", "EA_123");
		property.createElement(project,  cameoBlock, null);
		
		CameoUtils.logGUI("Creating Interface Block");
		CommonElement interfaceBlock = cef.createElement("InterfaceBlock",  "TestInterfaceBlock",  "EA_123");
		interfaceBlock.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Value Type");
		CommonElement valueType = cef.createElement("ValueType", "TestValueType", "EA_123");
		valueType.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Stereotype");
		CommonElement stereotype = cef.createElement("Stereotype",  "TestStereotype",  "EA_123");
		Element testStereotype = stereotype.createElement(project,  owner, null);
		
		CameoUtils.logGUI("Creating Class");
		CommonElement sysmlClass = cef.createElement("Class",  "TestClass",  "EA_123");
		Element cameoClass = sysmlClass.createElement(project,  owner, null);
		
		CameoUtils.logGUI("Creating InitialPseudoState");
		CommonElement isState = cef.createElement("InitialPseudoState", "TestInitialPseudoState", "EA_123");
		isState.createElement(project,  cameoStateMachine, null);
		
		CameoUtils.logGUI("CreatingFinalState");
		CommonElement finalState = cef.createElement("FinalState", "TestFinalState", "EA_123");
		finalState.createElement(project,  cameoStateMachine, null);
		
		CameoUtils.logGUI("Creating State");
		CommonElement state = cef.createElement("State", "TestState", "EA_123");
		state.createElement(project,  cameoStateMachine, null);
		
		CameoUtils.logGUI("Creating Value Property");
		CommonElement valProp = cef.createElement("ValueProperty", "TestValueProperty", "EA_123");
		valProp.createElement(project,  cameoBlock, null);
		
		CameoUtils.logGUI("Creating Block 1");
		CommonElement block1 = cef.createElement("Block", "Block 1", "EA_123");
		Element cameoBlock1 = block1.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Block 2");
		CommonElement block2 = cef.createElement("Block", "Block 2", "EA_123");
		Element cameoBlock2 = block2.createElement(project, owner, null);

		CameoUtils.logGUI("Creating Block 3");
		CommonElement block3 = cef.createElement("Block", "Block 3", "EA_123");
		Element cameoBlock3 = block3.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Block 4");
		CommonElement block4 = cef.createElement("Block", "Block 4", "EA_123");
		Element cameoBlock4 = block4.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Association");
		CommonRelationship association = crf.createElement("Association", "TestAssociation", "EA_123");
		association.createElement(project, owner, cameoBlock1, cameoBlock2, null);
		
		CameoUtils.logGUI("Creating Aggregation");
		CommonRelationship aggregation = crf.createElement("Aggregation", "TestAggregation", "EA_123");
		aggregation.createElement(project, owner, cameoBlock2, cameoBlock3, null);
		
//		CameoUtils.logGUI("Creating Allocate");
//		CommonRelationship allocate = crf.createElement("Allocate", "TestAllocate", "EA_123");
//		allocate.createElement(project, owner, cameoBlock3, cameoBlock4);
		
		CameoUtils.logGUI("Creating Composition");
		CommonRelationship composition = crf.createElement("Composition", "TestComposition", "EA_123");
		composition.createElement(project, owner, cameoBlock4, cameoBlock1, null);
		
		CameoUtils.logGUI("Creating Generalization");
		CommonRelationship generalization = crf.createElement("Generalization", "TestGeneralization", "EA_123");
		generalization.createElement(project, owner, cameoBlock, cameoBlock1, null);
		
		CameoUtils.logGUI("Creating Requirement");
		CommonElement requirement = cef.createElement("Requirement", "TestRequirement", "EA_123");
		requirement.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Extended Requirement");
		CommonElement extendedRequirement = cef.createElement("ExtendedRequirement", "TestExtendedRequirement", "EA_123");
		extendedRequirement.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Functional Requirement");
		CommonElement functionalRequirement = cef.createElement("FunctionalRequirement", "TestFunctionalRequirement", "EA_123");
		functionalRequirement.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Interface Requirement");
		CommonElement interfaceRequirement = cef.createElement("InterfaceRequirement", "TestInterfaceRequirement", "EA_123");
		interfaceRequirement.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Performance Requirement");
		CommonElement performanceRequirement = cef.createElement("PerformanceRequirement", "TestPerformanceRequirement", "EA_123");
		performanceRequirement.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Physical Requirement");
		CommonElement physicalRequirement = cef.createElement("PhysicalRequirement", "TestPhysicalRequirement", "EA_123");
		physicalRequirement.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Design Constraint");
		CommonElement designConstraint = cef.createElement("DesignConstraint", "TestDesignConstraint", "EA_123");
		designConstraint.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Collaboration");
		CommonElement collaboration = cef.createElement("Collaboration", "TestCollaboration", "EA_123");
		collaboration.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Interaction");
		CommonElement interaction = cef.createElement("Interaction", "TestInteraction", "EA_123");
		Element cameoInteraction = interaction.createElement(project, owner, null);
		
		
		//Lifeline and Combined fragment created as Interactions - Why?
		CameoUtils.logGUI("Creating Lifeline");
		CommonElement lifeline = cef.createElement("Lifeline", "TestLifeline", "EA_123");
		Element cameoLifeline = lifeline.createElement(project, cameoInteraction, null);
		
		CameoUtils.logGUI("Creating Combined Fragment");
		CommonElement combinedFragment = cef.createElement("CombinedFragment", "TestCombinedFragment", "EA_123");
		Element cameoCF = combinedFragment.createElement(project, cameoInteraction, null);
		
		CameoUtils.logGUI("Creating Interaction Use");
		CommonElement interactionUse = cef.createElement("InteractionUse", "TestInteractionUse", "EA_123");
		Element cameoIU = interactionUse.createElement(project, cameoInteraction, null);
		
		CameoUtils.logGUI("Creating Use Case");
		CommonElement useCase = cef.createElement("UseCase", "TestUseCase", "EA_123");
		Element cameoUC = useCase.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Actor");
		CommonElement actor = cef.createElement("Actor", "TestActor", "EA_123");
		Element cameoActor = actor.createElement(project, owner, null);
		
		CameoUtils.logGUI("Creating Action");
		CommonElement action = cef.createElement("Action",  "TestAction",  "EA_123");
		Element cameoAction = action.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Accept Event Action");
		CommonElement acceptEvent = cef.createElement("AcceptEventAction",  "TestAcceptEventAction", "EA_123");
		acceptEvent.createElement(project,  cameoActivity,  null);
		
		CameoUtils.logGUI("Creating Activity Parameter Node");
		CommonElement parameterNode = cef.createElement(SysmlConstants.ACTIVITYPARAMETERNODE, "TestParamterNode", "EA_123");
		parameterNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Activity Partition");
		CommonElement partition = cef.createElement(SysmlConstants.ACTIVITYPARTITION, "TestActivityPartition", "EA_123");
		partition.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Call Behavior Action");
		CommonElement callBehavior = cef.createElement(SysmlConstants.CALLBEHAVIORACTION, "TestCallBehaviorAction", "EA_123");
		callBehavior.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Call Operation Action");
		CommonElement callOperation = cef.createElement(SysmlConstants.CALLOPERATIONACTION, "TestCallOperationAction", "EA_123");
		callOperation.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Central Buffer Node");
		CommonElement centralBuffer = cef.createElement(SysmlConstants.CENTRALBUFFERNODE, "TestCentralBufferNode", "EA_123");
		centralBuffer.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Conditional Node");
		CommonElement conditionalNode = cef.createElement(SysmlConstants.CONDITIONALNODE, "TestConditionalNode", "EA_123");
		conditionalNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Create Object Action");
		CommonElement createObject = cef.createElement(SysmlConstants.CREATEOBJECTACTION, "TestCreateObjectAction", "EA_123");
		createObject.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Data Store Node");
		CommonElement dataStore = cef.createElement(SysmlConstants.DATASTORENODE, "TestDataStoreNode", "EA_123");
		dataStore.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Decision Node");
		CommonElement decisionNode = cef.createElement(SysmlConstants.DECISIONNODE, "TestDecisionNode", "EA_123");
		decisionNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Destroy Object Action");
		CommonElement destroyObject = cef.createElement(SysmlConstants.DESTROYOBJECTACTION, "TestDestroyObjectAction", "EA_123");
		destroyObject.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Activity Final Node");
		CommonElement activityFinal = cef.createElement(SysmlConstants.ACTIVITYFINALNODE, "TestActivityFinalNode", "EA_123");
		activityFinal.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Flow Final Node");
		CommonElement flowFinal = cef.createElement(SysmlConstants.FLOWFINALNODE, "TestFlowFinalNode", "EA_123");
		flowFinal.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Fork Node");
		CommonElement forkNode = cef.createElement(SysmlConstants.FORKNODE, "TestForkNode", "EA_123");
		forkNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Initial Node");
		CommonElement initialNode = cef.createElement(SysmlConstants.INITIALNODE, "TestInitialNode", "EA_123");
		initialNode.createElement(project, cameoActivity, null);
		
		
		CameoUtils.logGUI("Creating Input Pin");
		CommonElement inputPin = cef.createElement(SysmlConstants.INPUTPIN, "TestInputPin", "EA_123");
		inputPin.createElement(project, cameoAction, null);
		
		CameoUtils.logGUI("Creating Join Node");
		CommonElement joinNode = cef.createElement(SysmlConstants.JOINNODE, "TestJoinNode", "EA_123");
		joinNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Loop Node");
		CommonElement loopNode = cef.createElement(SysmlConstants.LOOPNODE, "TestLoopNode", "EA_123");
		loopNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Merge Node");
		CommonElement mergeNode = cef.createElement(SysmlConstants.MERGENODE, "TestMergeNode", "EA_123");
		mergeNode.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Output Pin");
		CommonElement outputPin = cef.createElement(SysmlConstants.OUTPUTPIN, "TestOutputPin", "EA_123");
		outputPin.createElement(project, cameoAction, null);
		
		CameoUtils.logGUI("Creating Send Signal Action Pin");
		CommonElement sendSignal = cef.createElement(SysmlConstants.SENDSIGNALACTION, "TestSendSignalAction", "EA_123");
		sendSignal.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Action");
		Element cameoAction2 = action.createElement(project, cameoActivity, null);
		
		CameoUtils.logGUI("Creating Control Flow");
		CommonRelationship controlFlow = crf.createElement(SysmlConstants.CONTROLFLOW, "TestControlFlow", "EA_123");
		controlFlow.createElement(project, cameoActivity, cameoAction, cameoAction2, null);
		
		CameoUtils.logGUI("Creating Object Flow");
		CommonRelationship objectFlow = crf.createElement(SysmlConstants.OBJECTFLOW, "TestObjectFlow", "EA_123");
		objectFlow.createElement(project, cameoActivity, cameoAction, cameoAction2, null);
		
		CameoUtils.logGUI("Creating Opaque Action");
		CommonElement opaqueAction = cef.createElement(SysmlConstants.OPAQUEACTION, "TestOpaqueAction", "EA_123");
		opaqueAction.createElement(project, cameoActivity, null);
		
		XMLItem customizationXML = new XMLItem();
		customizationXML.addAttribute("applyToSource",  testStereotype.getLocalID());
		customizationXML.addAttribute("applyToTarget", "");
		customizationXML.addAttribute("allowedRelationships", "");
		customizationXML.addAttribute("disallowedRelationships", "");
		CameoUtils.logGUI("Creating Customization");
		CommonElement customization = cef.createElement(SysmlConstants.CUSTOMIZATION,  "TestCustomization",  "12345");
		customization.createElement(project, owner,  customizationXML);
		JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Elements created successfully!");
	}
	
	public static void setProject() {
		Application.getInstance().getProject();
	}
}
