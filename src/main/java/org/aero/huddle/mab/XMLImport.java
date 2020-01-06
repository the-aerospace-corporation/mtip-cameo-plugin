package org.aero.huddle.mab;

import org.aero.huddle.util.CameoUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.Interface;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.VisibilityKindEnum;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

public class XMLImport {
	static Project project;
	private static final String XML_FRAMEWORK = "Framework";
	private static final String XML_BASELINE = "L1_Baseline";
	private static final String XML_TASK = "L2_Task";
	
	public static void importMAB(Document doc, Element parentPackage) {
		XMLImport.project = Application.getInstance().getProject();
		Profile mabProfile = createMABProfile();
		ElementsFactory ef = project.getElementsFactory();
		
		Node packet = doc.getDocumentElement();
		NodeList dataNodes = packet.getChildNodes();
		
		Stereotype frameworkStereotype = StereotypesHelper.getStereotype(project, "Framework", mabProfile);
		Stereotype baselineStereotype = StereotypesHelper.getStereotype(project, "L1 Baseline", mabProfile);
		Stereotype taskStereotype = StereotypesHelper.getStereotype(project, "L2 Task", mabProfile);
		
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);

			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				if (!SessionManager.getInstance().isSessionCreated(project)) {
					SessionManager.getInstance().createSession(project, "Create Action Element");
				}
				
				org.w3c.dom.Element xmlElement = (org.w3c.dom.Element)dataNode;
				
				//Find type tag and set stereotype based on that tag
				Node xmlType = xmlElement.getElementsByTagName("type").item(0);
				String type = xmlType.getTextContent();
				String taskType = "";
				
				Element newElement = ef.createClassInstance();
				
				if(type.contains(".")) {
					taskType = type.split("[.]")[1];
					
					if(taskType.equals(XML_FRAMEWORK)) {
						CameoUtils.logGUI("Creating Framework element");
						StereotypesHelper.addStereotype(newElement,  frameworkStereotype);
					} else if(taskType.contentEquals(XML_BASELINE)) {
						CameoUtils.logGUI("Baseline found");
						StereotypesHelper.addStereotype(newElement, baselineStereotype);
					} else if(taskType.contentEquals(XML_TASK)) {
						StereotypesHelper.addStereotype(newElement, taskStereotype);
						CameoUtils.logGUI("Baseline found");
					}
				}
				
				//Find attributes tag and set attributes based on element type
				org.w3c.dom.Element xmlAttributes = (org.w3c.dom.Element)xmlElement.getElementsByTagName("attributes").item(0);
				
				if(taskType.equals(XML_FRAMEWORK)) {
					Node taskTypeNode = xmlAttributes.getElementsByTagName("Task_Type").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, frameworkStereotype, "Task Type", taskTypeNode.getTextContent());
					
					Node priorityNode = xmlAttributes.getElementsByTagName("Priority").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, frameworkStereotype, "Priority", priorityNode.getTextContent());	
							
					Node maFrameworkTagNode = xmlAttributes.getElementsByTagName("MA_Framework_Tag").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, frameworkStereotype, "MA Framework Tag", maFrameworkTagNode.getTextContent());	
					
					Node maFrameworkNode = xmlAttributes.getElementsByTagName("MA_Framework").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, frameworkStereotype, "MA Framework", maFrameworkNode.getTextContent());	
					
					((NamedElement)newElement).setName(maFrameworkNode.getTextContent());
				}
				
				if(taskType.contentEquals(XML_BASELINE)) {
					Node taskTypeNode = xmlAttributes.getElementsByTagName("Task_Type").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, baselineStereotype, "Task Type", taskTypeNode.getTextContent());
					
					Node priorityNode = xmlAttributes.getElementsByTagName("Priority").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, baselineStereotype, "Priority", priorityNode.getTextContent());	
					
					Node l1KeyIdNode = xmlAttributes.getElementsByTagName("Level_1_Key_ID").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, baselineStereotype, "Level 1 Key ID", l1KeyIdNode.getTextContent());	
					
					Node l1TaskNumberNode = xmlAttributes.getElementsByTagName("Level_1_Task_Number").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, baselineStereotype, "Level 1 Task Number", l1TaskNumberNode.getTextContent());	
					
					Node l1NumberNode = xmlAttributes.getElementsByTagName("Level_1_Number").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, baselineStereotype, "Level 1 Number", l1NumberNode.getTextContent());	
					
					Node l1TaskNode = xmlAttributes.getElementsByTagName("Level_1_Task").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, baselineStereotype, "Level 1 Task", l1TaskNode.getTextContent());	
					
					((NamedElement)newElement).setName(l1TaskNode.getTextContent());
				}		
				
				if(taskType.contentEquals(XML_TASK)) {					
					Node taskTypeNode = xmlAttributes.getElementsByTagName("Task_Type").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Task Type", taskTypeNode.getTextContent());
					
					Node priorityNode = xmlAttributes.getElementsByTagName("Priority").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Priority", priorityNode.getTextContent());	
					
					Node descriptionNode = xmlAttributes.getElementsByTagName("Description").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Description", descriptionNode.getTextContent());
					
					Node referencesNode = xmlAttributes.getElementsByTagName("References").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "References", referencesNode.getTextContent());
					
					Node phase0Node = xmlAttributes.getElementsByTagName("Phase_0").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase 0", phase0Node.getTextContent());
					
					Node phaseANode = xmlAttributes.getElementsByTagName("Phase_A").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase A", phaseANode.getTextContent());
					
					Node phaseBNode = xmlAttributes.getElementsByTagName("Phase_B").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase B", phaseBNode.getTextContent());
					
					Node phaseCNode = xmlAttributes.getElementsByTagName("Phase_C").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase C", phaseCNode.getTextContent());
					
					Node phaseD1Node = xmlAttributes.getElementsByTagName("Phase_D1").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase D1", phaseD1Node.getTextContent());
					
					Node phaseD2Node = xmlAttributes.getElementsByTagName("Phase_D2").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase D2", phaseD2Node.getTextContent());
					
					Node phaseD3Node = xmlAttributes.getElementsByTagName("Phase_D3").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Phase D3", phaseD3Node.getTextContent());
					
					Node l2TaskNumberNode = xmlAttributes.getElementsByTagName("Level_2_Task_Number").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Level 2 Task Number", l2TaskNumberNode.getTextContent());
					
					Node l2TaskNode = xmlAttributes.getElementsByTagName("Level_2_Task").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Level 2 Task", l2TaskNode.getTextContent());
					
					Node l2KeyIdNode = xmlAttributes.getElementsByTagName("Level_2_Key_ID").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Level 2 Key ID", l2KeyIdNode.getTextContent());
					
					Node l2NumberNode = xmlAttributes.getElementsByTagName("Level_2_Number").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Level 2 Number", l2NumberNode.getTextContent());
					
					Node artifactsNode = xmlAttributes.getElementsByTagName("Artifacts").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "Artifacts", artifactsNode.getTextContent());
					
					Node sfwcNode = xmlAttributes.getElementsByTagName("SFWC").item(0);
					StereotypesHelper.setStereotypePropertyValue(newElement, taskStereotype, "SFWC", sfwcNode.getTextContent());
					
					((NamedElement)newElement).setName(l2TaskNode.getTextContent());
					
				}
				newElement.setOwner(parentPackage);
				SessionManager.getInstance().closeSession(project);
			}
		}
	}
	
	public static Profile createMABProfile() {
		Element owner = project.getPrimaryModel();
		Profile mabProfile = createProfile(owner, "Mission Assurance Baseline");
		createFrameworkStereotype(mabProfile, "Framework");
		createBaselineStereotype(mabProfile, "L1 Baseline");
		createTaskStereotype(mabProfile, "L2 Task");

		return mabProfile;
	}
	
//	public static HashMap<String, XMLItem> buildModelMap(NodeList dataNodes) {
//		HashMap<String, XMLItem> modelElements = new HashMap<String,XMLItem>();
//		
//		//Loop through all of the <data> nodes
//		for(int i = 0; i < dataNodes.getLength(); i++) {
//			Node dataNode = dataNodes.item(i);
//			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
//				NodeList fieldNodes = dataNode.getChildNodes();
//				XMLItem modelElement = new XMLItem();
//				//Loop through all of the fields in each dataNode
//				for(int j = 0; j < fieldNodes.getLength(); j++) {
//					Node fieldNode = fieldNodes.item(j);
//					
//					if(fieldNode.getNodeType() == Node.ELEMENT_NODE) {
//						//get model element type (ex. Sysml.Package)
//						if(fieldNode.getNodeName().contentEquals("type")) {
//							String type = fieldNode.getTextContent();
//							if(type.contains(".")) {
//								String element = type.split("[.]")[1];
//								modelElement.setType(element);
//							} else {
//								modelElement.setType("");
//							}
//						}
//						//get ID associated with each model element (for now, EA GUID)
//						if(fieldNode.getNodeName().equals("id")) {
//							modelElement = getIDs(fieldNode, modelElement);
//						}
//						//Loop through attributes and pull out necessary information
//						if(fieldNode.getNodeName().equals("attributes")) {
//							modelElement = getAttributes(fieldNode, modelElement);
//						}
//						//Loop through relationships look for parent, if diagram look for elements on diagram
//						if(fieldNode.getNodeName().contentEquals("relationships")) {
//							modelElement = getRelationships(fieldNode, modelElement);
//						}
//						
//						//Add model element attributes to parsedXML hashmap passed back to main function
//						if(modelElement.getEAID() != null) {
//							modelElements.put(modelElement.getEAID(),  modelElement);
//						}
//					}
//				}
//			}
//		}
//		return modelElements;
//	}
	
	public static Stereotype createTaskStereotype(Element owner, String name) {
		Stereotype frameworkStereotype = createBaselineStereotype(owner, name);
		createProperty(frameworkStereotype, "Level 2 Key ID");
		createProperty(frameworkStereotype, "Level 2 Number");
		createProperty(frameworkStereotype, "Level 2 Task Number");
		createProperty(frameworkStereotype, "Level 2 Task");
		createProperty(frameworkStereotype, "Phase 0");
		createProperty(frameworkStereotype, "Phase A");
		createProperty(frameworkStereotype, "Phase B");
		createProperty(frameworkStereotype, "Phase C");
		createProperty(frameworkStereotype, "Phase D1");
		createProperty(frameworkStereotype, "Phase D2");
		createProperty(frameworkStereotype, "Phase D3");
		createProperty(frameworkStereotype, "References");
		createProperty(frameworkStereotype, "Description");
		createProperty(frameworkStereotype, "SFWC");
		createProperty(frameworkStereotype, "Artifacts");
		createProperty(frameworkStereotype, "Comments");
		createProperty(frameworkStereotype, "Artifact 1");
		createProperty(frameworkStereotype, "Artifact 2");
		createProperty(frameworkStereotype, "Artifact 3");
		createProperty(frameworkStereotype, "Artifact 4");
		createProperty(frameworkStereotype, "Artifact 5");
		createProperty(frameworkStereotype, "Working Group Change Type");
		
		return frameworkStereotype;
	}
	
	
	public static Stereotype createBaselineStereotype(Element owner, String name) {
		Stereotype frameworkStereotype = createFrameworkStereotype(owner, name);
		createProperty(frameworkStereotype, "Level 1 Key ID");
		createProperty(frameworkStereotype, "Level 1 Number");
		createProperty(frameworkStereotype, "Level 1 Task Number");
		createProperty(frameworkStereotype, "Level 1 Task");
		createProperty(frameworkStereotype, "Common Tasks");
	
		return frameworkStereotype;
	}
	
	public static Stereotype createFrameworkStereotype(Element owner, String name) {
		Stereotype frameworkStereotype = createStereotype(owner, name);
		createProperty(frameworkStereotype, "Task Type");
		createProperty(frameworkStereotype, "MA Framework Tag");
		createProperty(frameworkStereotype, "MA Framework");
		createProperty(frameworkStereotype, "Priority");
	
		return frameworkStereotype;
	}
	
	public static Element createProperty(Element owner, String name) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Property Element");
		}
		Element property = f.createPropertyInstance();
		((NamedElement)property).setName(name);
		
		if(owner != null) {
			property.setOwner(owner);
		} else {
			property.setOwner(project.getPrimaryModel());
		}
		
		if(owner instanceof Interface) {
			((NamedElement)property).setVisibility(VisibilityKindEnum.PUBLIC);
		}
		
		SessionManager.getInstance().closeSession(project);
		return property;
	}
	
	public static Stereotype createStereotype(Element owner, String name) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Stereotype Element");
		}
		
		Stereotype sysmlElement = f.createStereotypeInstance();
		((NamedElement)sysmlElement).setName(name);
		
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
	
	public static Profile createProfile(Element owner, String name) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Profile Element");
		}
		Profile sysmlElement = f.createProfileInstance();
		((NamedElement)sysmlElement).setName(name);
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
}
