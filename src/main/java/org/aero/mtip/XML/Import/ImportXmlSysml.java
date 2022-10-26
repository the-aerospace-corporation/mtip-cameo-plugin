/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Import;

import java.awt.Rectangle;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import org.aero.mtip.ModelElements.Matrix.AbstractMatrix;
import org.aero.mtip.ModelElements.Profile.RelationshipConstraint;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.apache.commons.collections.MapUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

import uaf.UAFConstants;

public class ImportXmlSysml {
    static Map<String,Entry<Element, Element>> linktoPair = new HashMap<String,Entry<Element, Element>>();
    static Map<ModelDiagram,Diagram> diagramMap = new HashMap<ModelDiagram,Diagram>();
    public static HashMap<String, XMLItem> completeXML = new HashMap<String, XMLItem>();
    private static HashMap<String, XMLItem> stereotypesXML = new HashMap<String, XMLItem>();
    private static HashMap<String, XMLItem> profileXML = new HashMap<String, XMLItem>();
    private static Project project = Application.getInstance().getProject();
    private static HashMap<String, String> parentMap = new HashMap<String, String>();
    private static HashMap<String, String> pluginCreatedIDs = new HashMap<String, String>();
    private static String  metaModel = null;
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
		
		MODEL_VALIDATION_PACKAGE = null;
		CHECK_CLASSES = null;
		CHECK_RELATIONSHIPS = null;
		metaModel = null;
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
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Refresh");
		}
		SessionManager.getInstance().closeSession(project);
		project.getOptions().setAutoNumbering(true);
	}

	public static void buildModel(HashMap<String, XMLItem> parsedXML) {
		metaModel = CameoUtils.determineMetamodel(project);
		for (Entry<String, XMLItem> entry : parsedXML.entrySet()) {
			XMLItem modelElement = entry.getValue();
			String id = entry.getKey();
			if(modelElement == null) {
				String message = "Error parsing data for XML item with id " + id + ". Element will not be imported";
				ImportLog.log(message);
				CameoUtils.logGUI(message);
			} else {
				String category = modelElement.getCategory();
				if(!category.isEmpty()) {
					if (category.equals(SysmlConstants.ELEMENT)) {
						if (parentMap.get(id) == null) {
							buildElement(project, parsedXML, modelElement, id);
						}
					}
					if (category.equals(SysmlConstants.RELATIONSHIP)) {
						if (parentMap.get(id) == null) {
							buildRelationship(project, parsedXML, modelElement, id);
						}
					}
					if (modelElement.getCategory().equals(SysmlConstants.DIAGRAM)) {
						if (parentMap.get(id) == null) {
							buildDiagram(project, parsedXML, modelElement, id);
						}
					}
				} else {
					String message = "Element " + modelElement.getName() + " with id " + modelElement.getEAID() + " is uncategorized and will not be imported.";
					ImportLog.log(message);
					CameoUtils.logGUI(message);
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
	
	public static Element getOrBuildElement(Project project, HashMap<String, XMLItem> parsedXML, String eaID) {
		String cameoUnique = parentMap.get(eaID);

		if (cameoUnique == null) {
			XMLItem newItem = parsedXML.get(eaID);
			return buildElement(project, parsedXML, newItem, eaID);

		} else {
			Element element = (Element) project.getElementByID(cameoUnique);
			return element;
		}
	}
	
	public static Element getOrBuildRelationship(Project project, HashMap<String, XMLItem> parsedXML, String eaID) {
		String cameoUnique = parentMap.get(eaID);

		if (cameoUnique == null) {
			XMLItem newItem = parsedXML.get(eaID);
			return buildRelationship(project, parsedXML, newItem, eaID);

		} else {
			Element element = (Element) project.getElementByID(cameoUnique);
			return element;
		}
	}
	
	public static Element buildDiagram(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement, String id) {
		if(!Arrays.asList(SysmlConstants.SYSMLDIAGRAMS).contains(modelElement.getType()) 
				&& !Arrays.asList(DoDAFConstants.DODAF_DIAGRAMS).contains(modelElement.getType())  
				&& !Arrays.asList(UAFConstants.UAF_DIAGRAMS).contains(modelElement.getType())) {

			ImportLog.log(modelElement.getType() + " type is not supported. " + modelElement.getEAID());
			return null;
		}
		
		Element owner = GetImportedOwner(modelElement, parsedXML);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create " +  modelElement.getType() + " Element");
		}
		
		Diagram newDiagram = null;
	 
		CameoUtils.logGUI("Creating diagram of type: " + modelElement.getType() + " and id: " + modelElement.getEAID() + " with parent " + modelElement.getParent() + ".");
		AbstractDiagram diagram = (AbstractDiagram) cef.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
		newDiagram = (Diagram) diagram.createElement(project, owner, modelElement);
		project.getDiagram(newDiagram).open(); 
		TrackIds(newDiagram, modelElement);
		
		SessionManager.getInstance().closeSession(project);
		
		// Opens and closes its own session while populating the diagram
		populateDiagram(diagram, newDiagram, modelElement, parsedXML);
		return newDiagram;
	}
	
	public static Element buildRelationship(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement, String id) {
		if(!IsRelationshipSupported(modelElement)) { 
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
		
		//Create relationship in Cameo 
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, String.format("Creating CommonRelationship of type %s.",modelElement.getType()));
		}
		CommonRelationship relationship = crf.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
		
		SessionManager.getInstance().closeSession(project);

		relationship.createDependentElements(project, parsedXML, modelElement);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, String.format("Creating Relationship of type %s.", modelElement.getType()));
		}
		
		Element newElement = relationship.createElement(project, owner, client, supplier, modelElement);
		
		SessionManager.getInstance().closeSession(project);
				
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
		ImportLog.log(String.format("Created relationship of type: %s and id: %s with parent %s.", modelElement.getType(), modelElement.getEAID(), modelElement.getParent()));
		return newElement;
	}
	
	public static Element buildElement(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement, String id) {
		if(!IsElementSupported(modelElement)) { 
			ImportLog.log(String.format("%s type not supported. Import id %s", modelElement.getType(), modelElement.getEAID()));
			return null;
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		
		if (modelElement.isCreated()) {
			return (Element) project.getElementByID(modelElement.getCameoID());
		}
		
		Element owner = GetImportedOwner(modelElement, parsedXML);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Initialize element " +  modelElement.getType() + " Element");
		}
	
		CommonElement element = cef.createElement(modelElement.getType(), modelElement.getAttribute("name"), modelElement.getEAID());
		element.createDependentElements(project, parsedXML, modelElement);
		
		// Create new session if tagged values or other dependent elements are found, built, and session is closed.
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create " +  modelElement.getType() + " with dependent Elements");
		}
		
		Element newElement = element.createElement(project, owner, modelElement);
		// addStereotypeTaggedValues and addDependentElements will call and end their own sessions. End session here.
		SessionManager.getInstance().closeSession(project);
		
		if(newElement != null) {
			TrackIds(newElement, modelElement);
			addStereotypes(newElement, modelElement);
			element.addStereotypeTaggedValues(modelElement);					
			element.addDependentElements(parsedXML, modelElement);
			
			if(newElement.getOwner() == null) {
				newElement.dispose();
				ImportLog.log("Owner failed to be set including any default owners. Element with id " + modelElement.getEAID() + " not created.");
				return null;
			}
			
			ImportLog.log("Created element " + modelElement.getAttribute("name") + " of type: " + modelElement.getType() + " with no initial owner.");
			return newElement;
		}
		
		ImportLog.log("Element not created. Name: " + modelElement.getAttribute("name") + ". ID: " + modelElement.getEAID());
		return owner;
	}
	
	//Get stereotypes from parssedXML. Find profiles for those stereotypes. Get stereotypes. Apply stereotypes
	public static void addStereotypes(Element newElement, XMLItem modelElement) {
		HashMap<String, String> stereotypes = modelElement.getStereotypes();
		if(!MapUtils.isEmpty(stereotypes)) {
			for(String stereotype : stereotypes.keySet()) {
				addStereotype(newElement, stereotype, stereotypes.get(stereotype));
				addStereotypeFields(newElement, modelElement);
			}
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
			body = org.aero.mtip.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
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
			currentBody = currentBody + " or " + org.aero.mtip.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
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
		body = org.aero.mtip.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
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
		currentBody = currentBody + " or " + org.aero.mtip.ModelElements.Profile.OpaqueExpression.CHECK_CLASSES_START + profileName + "::" + elementName + ")";
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
				} else if(attributeKey.contentEquals(XmlTagConstants.STEREOTYPE_TAGGED_VALUE)) {
					try {
						TaggedValue tv = new TaggedValue(attribute);
						modelElement.addStereotypeTaggedValue(tv);
					} catch (NullPointerException npe) {
						ImportLog.log("Error parsing stereotype tagged value from HUDS XML for element: " + modelElement.getEAID());
					}
					
				} else if(attributeType.contentEquals(XmlTagConstants.ATTRIBUTE_TYPE_LIST)) {
					// Add logic to parse list attributes into a dict of lists in XMLItem
					try {
						List<Node> listAttributes = getListSubAttributes(attribute);
						for(Node listAttribute : listAttributes) {
							modelElement.addListAttribute(attributeKey, listAttribute.getTextContent());
						}
						
					} catch (NullPointerException npe) {
						ImportLog.log("Error parsing attribute for element with id: " + modelElement.getEAID());
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
		} else {
			return null;
		}
				
	}
	
	public static Element GetImportedOwner(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		String ownerID = modelElement.getParent();
		XMLItem ownerElement = parsedXML.get(ownerID);
		
		if(modelElement.getParent().trim().isEmpty() || ownerElement == null) {
			return primaryLocation; // Set owned equal to Primary model if no hasParent attribute in XML -> parent field in XMLItem == ""
		} 
		if(!ownerElement.getCameoID().trim().isEmpty()){
			return (Element) project.getElementByID(ownerElement.getCameoID());
		} 

		String category = ownerElement.getCategory();
		if(category.equals(SysmlConstants.ELEMENT)) {
			return buildElement(project, parsedXML, ownerElement, ownerElement.getEAID());
		} else if (category.equals(SysmlConstants.RELATIONSHIP)) {
			return buildRelationship(project, parsedXML, ownerElement, ownerElement.getEAID());
		}
		return null;
	}
	
	public static Element GetImportedClient(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		String clientCameoImportedId = parentMap.get(modelElement.getClient());
		
		if(clientCameoImportedId != null && !clientCameoImportedId.trim().isEmpty()) {
			return  (Element) project.getElementByID(clientCameoImportedId);
		} 
		
		String clientXmlId = modelElement.getClient();
		if(clientXmlId.startsWith("_")) {
			return (Element)project.getElementByID(clientXmlId);
		}
			
		if(clientXmlId.startsWith("_9_")) {
			Element client = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
			if(client != null) {
				return client;
			}
		}
		XMLItem clientElement = parsedXML.get(clientXmlId);
		if(clientElement != null) {
			return buildElement(project, parsedXML, clientElement, clientXmlId);
		} 
			
		ImportLog.log("Client Element with id : " + clientXmlId + " not exported with XML.");
		return null;
	}
	
	public static Element GetImportedSupplier(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		String supplierImportedId = parentMap.get(modelElement.getSupplier());
		
		if(supplierImportedId != null && !supplierImportedId.trim().isEmpty()) {
			return (Element)project.getElementByID(supplierImportedId);
		}
			//if supplier not an element of model, check if it exists in an external/auxiliary library.
		String supplierEAID = modelElement.getSupplier();
		if(supplierEAID.startsWith("_")) {
			Element supplier = (Element) project.getElementByID(supplierEAID);
			if(supplier != null) {
				return supplier;
			}
		}

		XMLItem supplierElement = parsedXML.get(supplierEAID);
		if(supplierElement != null) {
			return buildElement(project, parsedXML, supplierElement, supplierEAID);
		}
		
		ImportLog.log("Supplier Element with id : " + supplierEAID + " not exported with XML.");
		return null;
	}
	
	public static boolean IsElementSupported(XMLItem modelElement) {
		return Arrays.asList(SysmlConstants.SYSMLELEMENTS).contains(modelElement.getType());
	}
	
	public static boolean IsRelationshipSupported(XMLItem modelElement) {
		return Arrays.asList(SysmlConstants.SYSMLRELATIONSHIPS).contains(modelElement.getType());
	}
	
	public static boolean IsDiagramSupported(XMLItem modelElement) {
		return Arrays.asList(SysmlConstants.SYSMLDIAGRAMS).contains(modelElement.getType());
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
		List<String> diagramAllowedTypes = 	Arrays.asList(SysmlConstants.diagramTypeMap.get(modelElement.getType()));
		List<String> importElementIDs = modelElement.getChildElements(parsedXML);
		HashMap<Element, Rectangle> elementsOnDiagram = new HashMap<Element, Rectangle> ();
		
		for(String importID : importElementIDs) {
			String cameoID = parentMap.get(importID);
			modelElement.addImportID(cameoID, importID);
			String elementType = parsedXML.get(importID).getType();
			if(cameoID != null) {
				if(diagramAllowedTypes.contains(elementType)) {
					Element elementOnDiagram = (Element)project.getElementByID(cameoID);
					if(elementOnDiagram != null) {
						elementsOnDiagram.put(elementOnDiagram, modelElement.getLocation(importID));
						String message = "Adding element with id " + importID + " of type " + elementType + " to diagram.";
					} else {
						String message = "Could not add element with id " + importID + " of type " + elementType + " to diagram. Element was not created during import";
						ImportLog.log(message);
					}
				} else {
					String message = "Element with id " + importID + " of type " + elementType + " not allowed on diagrams of type " + modelElement.getType();
					ImportLog.log(message);
				}				
			}
		}
		return elementsOnDiagram;
	}
	
	public static List<Element> GetImportedRelationshipsOnDiagram(XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		List<String> importRelationshipIDs = modelElement.getChildRelationships(parsedXML);
		List<Element> relationshipsOnDiagram = new ArrayList<Element> ();
				
		for(String importRelationshipID : importRelationshipIDs) {
			String cameoID = parentMap.get(importRelationshipID);
			if(cameoID != null) {
				Element elementOnDiagram = (Element)project.getElementByID(cameoID);
				if(elementOnDiagram != null) {
					String elementType = parsedXML.get(importRelationshipID).getType();
					// Check for element type on diagram if necessary
					relationshipsOnDiagram.add((Element)project.getElementByID(cameoID));
				}
			}
		}
		return relationshipsOnDiagram;
	}
	
	public static void populateDiagram(AbstractDiagram diagram, Diagram newDiagram, XMLItem modelElement, HashMap<String, XMLItem> parsedXML) {
		diagram.createDependentElements(project, parsedXML, modelElement);
		
		HashMap<Element, Rectangle> elementsOnDiagram = GetImportedElementsOnDiagram(modelElement, parsedXML);
		List<Element> relationshipsOnDiagram = GetImportedRelationshipsOnDiagram(modelElement, parsedXML);
			
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Adding elements to diagram with type " +  modelElement.getType() + ".");
		}
				
		boolean noPosition = diagram.addElements(project, newDiagram, elementsOnDiagram, modelElement);
		if(noPosition) {
			Application.getInstance().getProject().getDiagram(newDiagram).layout(true, new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());
		}
		diagram.addRelationships(project, newDiagram, relationshipsOnDiagram);
		project.getDiagram(newDiagram).close(); 
		SessionManager.getInstance().closeSession(project);
		
	    diagramMap.put((ModelDiagram) diagram, newDiagram);
	}
}
