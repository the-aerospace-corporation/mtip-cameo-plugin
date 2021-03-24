package org.aero.huddle.ModelElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.NoRectangleDefinedException;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition;
import com.nomagic.uml2.impl.ElementsFactory;

public abstract class  AbstractDiagram  extends CommonElement implements ModelDiagram{
	public static Map<String,String> diagramToType;
	protected int elementCount = 0;
	protected int relationshipCount = 0;
	protected HashMap<String, ShapeElement> shapeElements = new HashMap<String, ShapeElement>();
	protected List<String> diagramElementIDs = new ArrayList<String> ();
	
	protected Element exportingDiagram = null;
    static {
    	
// ALL POSSIBLE DIAGRAM TYPES
//        Class Diagram
//        Use Case Diagram
//        Sequence Diagram
//        Activity Diagram
//        State Machine Diagram
//        Component Diagram
//        Object Diagram
//        Package Diagram
//        Deployment Diagram
//        Communication Diagram
//        Protocol State Machine Diagram
//        Composite Structure Diagram
//        Interaction Overview Diagram
//        Profile Diagram
//        Any Diagram
//        Static Diagram
//        Behavior Diagram
//        Interaction Diagram
//        Free Form Diagram
//        Networking Diagram
//        Requirement Diagram
//        Simulation Configuration Diagram
//        SysML Activity Diagram
//        SysML Block Definition Diagram
//        SysML Internal Block Diagram
//        SysML Package Diagram
//        SysML Parametric Diagram
//        SysML Sequence Diagram
//        SysML State Machine Diagram
//        SysML Use Case Diagram
//        Time Diagram
//        User Interface Modeling Diagram
//        Views and Viewpoints Diagram
//        Content Diagram
//        Dependency Matrix
//        Derive Requirement Matrix
//        Refine Requirement Matrix
//        Satisfy Requirement Matrix
//        SysML Allocation Matrix
//        Verify Requirement Matrix
//        Generic Table
//        Instance Table
//        Glossary Table
//        Activity Decomposition Map
//        Instance Map
//        Relation Map Diagram
//        Requirement Containment Map
//        Requirement Derivation Map
//        Structure Decomposition Map
//        Requirement Table
//        ProfileUpgradeStereotypesOrTagsMappingTable
//        ProfileUpgradeStereotypesOrTagsClearingTable
//        Metric Table
//        Blackbox ICD Table
//        Whitebox ICD Table
    	
        Map<String, String> aMap = new HashMap<String,String>();
      
//    	public static final String BDD = "BlockDefinitionDiagram";
//    	public static final String IBD = "InternalBlockDiagram";
//    	public static final String PKG = "PackageDiagram";
//    	public static final String REQ = "RequirementsDiagram";
//    	public static final String PAR = "ParametricDiagram";
//    	public static final String STM = "StateMachineDiagram";
//    	public static final String ACT = "ActivityDiagram";
//    	public static final String SEQ = "SequenceDiagram";
//    	public static final String UC = "UseCaseDiagram";
//    	public static final String PROFILEDIAGRAM = "ProfileDiagram";
//    	public static final String CLASSDIAGRAM = "ClassDiagram";

        aMap.put("Class Diagram", SysmlConstants.CLASSDIAGRAM);
        aMap.put("Use Case Diagram",SysmlConstants.UC );
        aMap.put("Sequence Diagram",SysmlConstants.SEQ );
        aMap.put("Activity Diagram", SysmlConstants.ACT);
        aMap.put("State Machine Diagram", SysmlConstants.STM);
//        aMap.put("Component Diagram", );
//        aMap.put("Object Diagram", );
        aMap.put("Package Diagram", SysmlConstants.PKG);
//        aMap.put("Deployment Diagram", );
//        aMap.put("Communication Diagram", );
//        aMap.put("Protocol State Machine Diagram", );
//        aMap.put("Composite Structure Diagram", );
//        aMap.put("Interaction Overview Diagram", );
        aMap.put("Profile Diagram", SysmlConstants.PROFILEDIAGRAM);
//        aMap.put("Any Diagram", );
//        aMap.put("Static Diagram", );
//        aMap.put("Behavior Diagram", );
//        aMap.put("Interaction Diagram", );
//        aMap.put("Free Form Diagram", );
//        aMap.put("Networking Diagram", );
        aMap.put("Requirement Diagram",SysmlConstants.REQ );
//        aMap.put("Simulation Configuration Diagram", );
        
        aMap.put("SysML Activity Diagram", SysmlConstants.ACT );
        aMap.put("SysML Block Definition Diagram", SysmlConstants.BDD );
        aMap.put("SysML Internal Block Diagram", SysmlConstants.IBD );
        aMap.put("SysML Package Diagram", SysmlConstants.PKG );
        aMap.put("SysML Parametric Diagram", SysmlConstants.PAR);
        aMap.put("SysML Sequence Diagram", SysmlConstants.SEQ);
        aMap.put("SysML State Machine Diagram", SysmlConstants.STM);
        aMap.put("SysML Use Case Diagram", SysmlConstants.UC);
        
//        aMap.put("Time Diagram", );
//        aMap.put("User Interface Modeling Diagram", );
//        aMap.put("Views and Viewpoints Diagram", );
//        aMap.put("Content Diagram", );
        aMap.put("Dependency Matrix", SysmlConstants.DEPENDENCY_MATRIX);
//        aMap.put("Derive Requirement Matrix", );
//        aMap.put("Refine Requirement Matrix", );
//        aMap.put("Satisfy Requirement Matrix", );
//        aMap.put("SysML Allocation Matrix", );
//        aMap.put("Verify Requirement Matrix", );
        aMap.put("Generic Table", SysmlConstants.GENERIC_TABLE);
//        aMap.put("Instance Table", );
//        aMap.put("Glossary Table", );
//        aMap.put("Activity Decomposition Map", );
//        aMap.put("Instance Map", );
//        aMap.put("Relation Map Diagram", );
//        aMap.put("Requirement Containment Map", );
//        aMap.put("Requirement Derivation Map", );
//        aMap.put("Structure Decomposition Map", );
//        aMap.put("Requirement Table", );
//        aMap.put("ProfileUpgradeStereotypesOrTagsMappingTable", );
//        aMap.put("ProfileUpgradeStereotypesOrTagsClearingTable", );
//        aMap.put("Metric Table", );
//        aMap.put("Blackbox ICD Table", );
//        aMap.put("Whitebox ICD Table", );
        
           
        diagramToType = Collections.unmodifiableMap(aMap);
    }
    
	public AbstractDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	//Abstract methods
	public abstract String getSysmlConstant();
	public abstract String getDiagramType();

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		try {
			CameoUtils.logGUI("Creating element from abstract diagram class");
			sysmlElement = ModelElementsManager.getInstance().createDiagram(getSysmlConstant(), (Namespace) owner);
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Caught read only exception");
		}
//		setOwner(project, sysmlElement);
		((NamedElement) sysmlElement).setName(name);

		return sysmlElement;
	}
	
	@Override
	public boolean addElements(Project project, Diagram diagram, List<Element> elements, List<Rectangle> locations) {
		boolean noPosition = false;
		try {
			DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
			exportingDiagram = (Element)diagram;
			
//			project.getDiagram(diagram).open();

			int counter = 0;
			ShapeElement shape = null;
			for (Element element : elements) {
//				ViewHelper.canElementHaveSymbolInDiagram(Element element) 
				Rectangle location = locations.get(counter);
				Point point = new Point(location.x, location.y);
				if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999) {
					shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
					noPosition = true;
				} else {
					if(!(element instanceof ActivityPartition)) {
						shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
						if(shape != null) {
							PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);	
							shapeElements.put(element.getLocalID(), shape);
							CameoUtils.logGUI("Placing element " + ((NamedElement)element).getName() + " at x:" + Integer.toString(location.x) + " y:" + Integer.toString(location.y));
						} else {
							CameoUtils.logGUI("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getLocalID() + " on diagram.");
							ImportLog.log("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getLocalID() + " on diagram.");
						}
					}
				}
				counter++;
			}
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Diagram " + diagram.getHumanName() + " is ready only. No elements will be added.");
		}
//		project.getDiagram(diagram).close();
		return noPosition;
	}
	
	@Override
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships) {
		try {
			DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
//			Application.getInstance().getProject().getDiagram(diagram).layout(true, new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());
//			project.getDiagram(diagram).open();
			List<PresentationElement> presentationElements = presentationDiagram.getPresentationElements();
			for(PresentationElement presentationElement : presentationElements) {
				if(presentationElement instanceof PathElement) {
					PresentationElementsManager.getInstance().deletePresentationElement(presentationElement);
				}
			}
			for (Element relationship : relationships) {
				Element client = ModelHelper.getClientElement(relationship);
				Element supplier = ModelHelper.getSupplierElement(relationship);
				PresentationElement clientPE = presentationDiagram.findPresentationElementForPathConnecting(client, null);
				PresentationElement supplierPE = presentationDiagram.findPresentationElementForPathConnecting(supplier, null);
				
				if(clientPE != null && supplierPE != null) {
					PresentationElementsManager.getInstance().createPathElement(relationship, clientPE ,supplierPE);
					CameoUtils.logGUI("Placing relationship " + relationship.getHumanName() + " on to diagram.");
				} else {
					ImportLog.log("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
					CameoUtils.logGUI("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
				}
								
			}
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Diagram " + diagram.getHumanName() + " is ready only. No relationships will be added.");
		}
//		project.getDiagram(diagram).close();
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		List<String> diagramElements = modelElement.getChildElements(parsedXML);
		for(String diagramElement : diagramElements) {
			XMLItem diagramElementXML = parsedXML.get(diagramElement);
			ImportXmlSysml.buildElement(project, parsedXML, diagramElementXML, diagramElement);
		}
		List<String> diagramRelationships = modelElement.getChildRelationships(parsedXML);
		for(String diagramRelationship : diagramRelationships) {
			XMLItem diagramRelationshipXML = parsedXML.get(diagramRelationship);
			if(Arrays.asList(SysmlConstants.SYSMLRELATIONSHIPS).contains(diagramRelationshipXML.getType())) {
				ImportXmlSysml.buildRelationship(project, parsedXML, diagramRelationshipXML, diagramRelationship);
			}
		}
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);

		// get attributes part
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element displayTag = createStringAttribute(xmlDoc, XmlTagConstants.DISPLAY_AS, XmlTagConstants.DISPLAY_AS_DIAGRAM);
		attributes.appendChild(displayTag);
		
		org.w3c.dom.Element relationships = getType(data.getChildNodes(), XmlTagConstants.RELATIONSHIPS);
		org.w3c.dom.Element elementListTag = xmlDoc.createElement(XmlTagConstants.ELEMENT);
		elementListTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_LIST);
		
		
		org.w3c.dom.Element relationshipListTag = xmlDoc.createElement(XmlTagConstants.DIAGRAM_CONNECTOR);
		relationshipListTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_LIST);
		
		writeElements(xmlDoc, project, elementListTag, relationshipListTag, element);
		
		relationships.appendChild(elementListTag);
		relationships.appendChild(relationshipListTag);
		
		return data;
	}
	
	protected void writeElements(Document xmlDoc, Project project, org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, Element element) {
		CameoUtils.logGUI("Finding presentation elements to add to diagram xml...");
		Diagram diagram = (Diagram) element;
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
		presentationDiagram.open();
		List<PresentationElement> presentationElements = presentationDiagram.getPresentationElements();
		Collection<Element> elements = presentationDiagram.getUsedModelElements();
		CameoUtils.logGUI("Found " + Integer.toString(elements.size()) + " elements used on the diagram.");
		int presElementCount = presentationElements.size();
//		for(Element elementOnDiagram : elements) {
//			PresentationElement presElement = presentationDiagram.findPresentationElement(elementOnDiagram, elementOnDiagram.getClass());
//			if(presElement != null) {
//				if(presElement instanceof PathElement) {
//					CameoUtils.logGUI("Adding relationship to diagram...");
//					org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presElement);
//					if(relationshipTag != null) {
//						relationshipListTag.appendChild(relationshipTag);
//					}
//				} else {
//					CameoUtils.logGUI("Adding element to diagram...");
//					org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presElement);
//					if(elementTag != null) {
//						elementListTag.appendChild(elementTag);
//					}
//				}
//			}
//		}
//		
		CameoUtils.logGUI("Found " + Integer.toString(presElementCount) + " presentation elements.");
		for(PresentationElement presentationElement : presentationElements) {
			if(presentationElement instanceof PathElement) {
				CameoUtils.logGUI("Adding relationship to diagram...");
				org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presentationElement);
				if(relationshipTag != null) {
					relationshipListTag.appendChild(relationshipTag);
				}
			} else {
				CameoUtils.logGUI("Adding element to diagram...");
				org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presentationElement);
				if(elementTag != null) {
					elementListTag.appendChild(elementTag);
				}
			}
		}
		presentationDiagram.close();
	}
	
	protected org.w3c.dom.Element createDiagramElementTag(Document xmlDoc, PresentationElement presentationElement) {
		Element curElement = presentationElement.getElement();
		if(curElement != null && !curElement.getHumanType().contentEquals("Diagram")) {
			Rectangle bounds = null;
			try {
				bounds = presentationElement.getBounds();
			} catch(NoRectangleDefinedException nrde) {
				CameoUtils.logGUI("Presentation element with name " + curElement.getHumanName() + " has no bounds. Cannot write position");
			}
			 
			if(curElement != null && bounds != null) {
				// Check whether the actual element is relationship (could be hybrid type), and filter it out from the diagram explicit list
//				if (!(curElement instanceof Relationship) && !(curElement instanceof Connector) && !(curElement instanceof ActivityEdge) && !(curElement instanceof Transition)) {
				if(!this.diagramElementIDs.contains(curElement.getLocalID())) {
					diagramElementIDs.add(curElement.getLocalID());
				
					String curID = curElement.getID();
					String type = "sysml." + curElement.getHumanType();
					
					CameoUtils.logGUI("Adding element with id " + curID + " of type " + type + " to diagram " + this.name + 
							" with x:" + String.valueOf(bounds.x) + " y:" + String.valueOf(bounds.y) + " height:" + 
							String.valueOf(bounds.height) + " and width: " + String.valueOf(bounds.width));
					org.w3c.dom.Element elementTag = createListElement(xmlDoc, Integer.toString(elementCount));
					org.w3c.dom.Element idTag = xmlDoc.createElement(XmlTagConstants.ID);
					idTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
					idTag.appendChild(xmlDoc.createTextNode(curID));
					
					org.w3c.dom.Element typeTag = xmlDoc.createElement(XmlTagConstants.TYPE);
					typeTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
					typeTag.appendChild(xmlDoc.createTextNode(type));
									
					org.w3c.dom.Element relationshipMetadataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
					relationshipMetadataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
					
					org.w3c.dom.Element topTag = xmlDoc.createElement(XmlTagConstants.TOP);
					topTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_INT);
					topTag.appendChild(xmlDoc.createTextNode(String.valueOf(-bounds.y)));
					
					org.w3c.dom.Element bottomTag = xmlDoc.createElement(XmlTagConstants.BOTTOM);
					bottomTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_INT);
					bottomTag.appendChild(xmlDoc.createTextNode(String.valueOf(-bounds.y - bounds.height)));
					
					org.w3c.dom.Element leftTag = xmlDoc.createElement(XmlTagConstants.LEFT);
					leftTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_INT);
					leftTag.appendChild(xmlDoc.createTextNode(String.valueOf(bounds.x)));
					
					org.w3c.dom.Element rightTag = xmlDoc.createElement(XmlTagConstants.RIGHT);
					rightTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_INT);
					rightTag.appendChild(xmlDoc.createTextNode(String.valueOf(bounds.x + bounds.width)));
			
					elementTag.appendChild(idTag);
					elementTag.appendChild(typeTag);
					elementTag.appendChild(relationshipMetadataTag);
					relationshipMetadataTag.appendChild(topTag);
					relationshipMetadataTag.appendChild(bottomTag);
					relationshipMetadataTag.appendChild(leftTag);
					relationshipMetadataTag.appendChild(rightTag);
					
					this.diagramElementIDs.add(curElement.getLocalID());
					elementCount++;
					return elementTag;
				}
			}
		}
		return null;
	}
	
	protected org.w3c.dom.Element createDiagramRelationshipTag(Document xmlDoc, PresentationElement presentationElement) {
		Element relationship = presentationElement.getElement();
		if(relationship != null) {
			if(!this.diagramElementIDs.contains(relationship.getLocalID())) {
				diagramElementIDs.add(relationship.getLocalID());
				org.w3c.dom.Element relationshipTag = createDictRelationship(xmlDoc, Integer.toString(relationshipCount));
				org.w3c.dom.Element idTag = xmlDoc.createElement(XmlTagConstants.ID);
				idTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
				idTag.appendChild(xmlDoc.createTextNode(relationship.getLocalID()));
				
				org.w3c.dom.Element typeTag = xmlDoc.createElement(XmlTagConstants.TYPE);
				typeTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
				typeTag.appendChild(xmlDoc.createTextNode(relationship.getHumanType()));
				
				org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
				relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
				relationshipTag.appendChild(relDataTag);
				
				relationshipTag.appendChild(idTag);
				relationshipTag.appendChild(typeTag);

				
				relationshipCount++;
				return relationshipTag;
			}
		}
		return null;		
	}
}
