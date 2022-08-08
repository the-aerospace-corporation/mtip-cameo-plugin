
/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aero.mtip.XML.Export.ExportXmlSysml;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import uaf.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.NoRectangleDefinedException;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.LinkView;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.magicdraw.uml.symbols.shapes.CallBehaviorActionView;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;

public abstract class  AbstractDiagram  extends CommonElement implements ModelDiagram {
	public static Map<String,String> diagramToType;
	protected int elementCount = 0;
	protected int relationshipCount = 0;
	protected HashMap<String, ShapeElement> shapeElements = new HashMap<String, ShapeElement>();
	protected List<String> diagramElementIDs = new ArrayList<String> ();
	protected String[] allowableElements = null;
<<<<<<< HEAD
<<<<<<< HEAD
	protected List<Element> subElements = null;
=======
	protected String cameoDiagramConstant = null;
>>>>>>> 03f657b (fixed merge conflicts and import for UAFConstant for resource and projects)
=======
	protected String cameoDiagramConstant = null;
>>>>>>> 2d7e9fc (Added support for exporting capability views from UAF/DoDAF models.)
	
	
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
        aMap.put("Derive Requirement Matrix", SysmlConstants.DERIVE_REQUIREMENT_MATRIX);
        aMap.put("Refine Requirement Matrix", SysmlConstants.REFINE_REQUIREMENT_MATRIX);
        aMap.put("Satisfy Requirement Matrix", SysmlConstants.SATISFY_REQUIREMENT_MATRIX);
        aMap.put("SysML Allocation Matrix", SysmlConstants.ALLOCATION_MATRIX);
        aMap.put("Verify Requirement Matrix", SysmlConstants.VERIFY_REQUIREMENT_MATRIX);
        aMap.put("Generic Table", SysmlConstants.GENERIC_TABLE);
        aMap.put("Instance Table", SysmlConstants.INSTANCE_TABLE);
        aMap.put("Glossary Table", SysmlConstants.GLOSSARY_TABLE);
//        aMap.put("Activity Decomposition Map", );
//        aMap.put("Instance Map", );
//        aMap.put("Relation Map Diagram", );
//        aMap.put("Requirement Containment Map", );
//        aMap.put("Requirement Derivation Map", );
//        aMap.put("Structure Decomposition Map", );
        aMap.put("Requirement Table", SysmlConstants.REQUIREMENT_TABLE);
//        aMap.put("ProfileUpgradeStereotypesOrTagsMappingTable", );
//        aMap.put("ProfileUpgradeStereotypesOrTagsClearingTable", );
        aMap.put("Metric Table", SysmlConstants.METRIC_TABLE);
//        aMap.put("Blackbox ICD Table", );
//        aMap.put("Whitebox ICD Table", );
       
        //UAF Diagram Mapping
        aMap.put("Operational Process Flow", UAFConstants.OPERATIONAL_PROCESS_FLOW);

        // DoDAF Diagram mapping
        aMap.put("CV-1 Vision", DoDAFConstants.CV1);
        aMap.put("CV-2 Capability Taxonomy", DoDAFConstants.CV2);
        aMap.put("CV-3", DoDAFConstants.CV3);
        aMap.put("CV-4 Capability Dependencies", DoDAFConstants.CV4);
        aMap.put("DODAF2_CV-5", DoDAFConstants.CV5);
        aMap.put("CV-6 Capability to Operational Activities Mapping", DoDAFConstants.CV6);
        aMap.put("CV-7 Capability to Services Mapping", DoDAFConstants.CV7);
        
        aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV1);
        aMap.put("SV-2 Systems Communications Description", DoDAFConstants.SV2);
        // aMap.put("SV-3 Systems-Systems Matrix", DoDAFConstants.SV3);
        aMap.put("SV-4 Systems Functionality Description", DoDAFConstants.SV4);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV5A);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV5B);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV5C);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV6);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV7);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV8);
        // aMap.put("SV-1 Systems Interface Description", DoDAFConstants.SV9);
        aMap.put("SV-10a Systems Parametric", DoDAFConstants.SV10A);
        aMap.put("SV-10b Systems State Transition Description", DoDAFConstants.SV10B);
        aMap.put("SV-10c Systems Event-Trace Description", DoDAFConstants.SV10C);
        aMap.put("OV-1 High-Level Operational Concept Graphic", DoDAFConstants.OV1);
        aMap.put("OV-2 Operational Resource Flow Description", DoDAFConstants.OV2);
        // aMap.put("OV-3 Operational Resource Flow Description", DoDAFConstants.OV3); Table
        aMap.put("OV-4 Organizational Relationships Chart", DoDAFConstants.OV4);
        aMap.put("OV-5a Operational Activity Decomposition Tree", DoDAFConstants.OV5A);
        aMap.put("OV-5b Operational Activity Model", DoDAFConstants.OV5B);
        // aMap.put("OV-1 High-Level Operational Concept Graphic", DoDAFConstants.OV6A); Table
        aMap.put("OV-6b Operational State Transition Description", DoDAFConstants.OV6B);
        aMap.put("OV-6c Operational Event-Trace Description", DoDAFConstants.OV6C);

        
<<<<<<< HEAD
=======
        // DoDAF Diagram mapping
        aMap.put("CV-1 Vision", DoDAFConstants.CV1);
        aMap.put("CV-2 Capability Taxonomy", DoDAFConstants.CV2);
        aMap.put("CV-3", DoDAFConstants.CV3);
        aMap.put("CV-4 Capability Dependencies", DoDAFConstants.CV4);
        aMap.put("DODAF2_CV-5", DoDAFConstants.CV5);
        aMap.put("CV-6 Capability to Operational Activities Mapping", DoDAFConstants.CV6);
        aMap.put("CV-7 Capability to Services Mapping", DoDAFConstants.CV7);
        
           
>>>>>>> 2d7e9fc (Added support for exporting capability views from UAF/DoDAF models.)
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
		((NamedElement) sysmlElement).setName(name);

		return sysmlElement;
	}
	
	public boolean addElements(Project project, Diagram diagram, HashMap<Element, Rectangle> elements, XMLItem xmlElement) {
		boolean noPosition = false;
		try {
			DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
			exportingDiagram = (Element)diagram;
			int counter = 0;
			
			for (Map.Entry<Element, Rectangle> entry : elements.entrySet()) {
				noPosition = createPresentationElement(project, entry.getKey(), entry.getValue(), presentationDiagram, counter, noPosition);
				counter++;
			}
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Diagram " + diagram.getHumanName() + " is ready only. No elements will be added.");
		}
		return noPosition;
	}
	
	public boolean createPresentationElement(Project project, Element element, Rectangle location, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		try {
			if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999 && !CameoUtils.isAssociationBlock(element, project)) {
				shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
				noPosition = true;
			} else {
				try {
					shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
					PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
				} catch(NullPointerException npe) {
					CameoUtils.logGUI("Null Pointer Exception creating or placing element " + ((NamedElement)element).getName() + " with ID: " + element.getID() + " on diagram.");
					ImportLog.log("Null Pointer Exception creating or placing element " + ((NamedElement)element).getName() + " with ID: " + element.getID() + " on diagram.");
				} catch(IllegalArgumentException iae) {
					CameoUtils.logGUI("Illegal Argument Exception creating or placing element " + ((NamedElement)element).getName() + " with ID: " + element.getID() + " on diagram.");
					ImportLog.log("Illegal Argument Exception creating or placing element " + ((NamedElement)element).getName() + " with ID: " + element.getID() + " on diagram.");
				}
			}
		} catch(ClassCastException cce) {
			CameoUtils.logGUI("Caught Class cast exception adding " + element.getHumanName() + " " + "with id " + element.getID() + " to diagram.");
			ImportLog.log("Caught Class cast exception adding " + element.getID() + " to diagram.");
		}
		
		if(shape != null) {
			CameoUtils.logGUI("Placing element " + ((NamedElement)element).getName() + " at x:" + Integer.toString(location.x) + " y:" + Integer.toString(location.y));
			this.shapeElements.put(element.getID(), shape);
		}
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
				
				try {
					if(clientPE != null && supplierPE != null) {
						PresentationElementsManager.getInstance().createPathElement(relationship, clientPE ,supplierPE);
						CameoUtils.logGUI("Placing relationship " + relationship.getHumanName() + " on to diagram.");
					} else {
						ImportLog.log("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
						CameoUtils.logGUI("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
					}
				} catch(ClassCastException cce) {
					ImportLog.log("Class cast exception creating path element.");
				} catch(NullPointerException npe) {
					ImportLog.log("Null pointer exception creating path element.");
				}
				
								
			}
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Diagram " + diagram.getHumanName() + " is ready only. No relationships will be added.");
		}
//		project.getDiagram(diagram).close();
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
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
		Diagram diagram = (Diagram) element;
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
		presentationDiagram.open();
		
		for(PresentationElement presentationElement : presentationDiagram.getPresentationElements()) {
			writeElement(xmlDoc, elementListTag, relationshipListTag, presentationElement, null);
		}
		
		presentationDiagram.close();
	}
	
	public void writeElement(Document xmlDoc, org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		if(presentationElement instanceof PathElement) {
			org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presentationElement);
			if(relationshipTag != null) {
				relationshipListTag.appendChild(relationshipTag);
			}
			if(presentationElement instanceof LinkView) {
				exportLink(xmlDoc, presentationElement);
			}
		} else {
			org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presentationElement, parentPresentationElement);
			if(elementTag != null) {
				elementListTag.appendChild(elementTag);
			}
		}
		
		for(PresentationElement subPresentationElement : presentationElement.getPresentationElements()) {
			if(!(subPresentationElement instanceof com.nomagic.magicdraw.uml.symbols.shapes.RoleView)) {
				writeElement(xmlDoc, elementListTag, relationshipListTag, subPresentationElement, presentationElement);
			}
		}
	}
	
	protected org.w3c.dom.Element exportLink(Document xmlDoc, PresentationElement presentationElement) {
		Element element = presentationElement.getElement();
		InstanceSpecification link = (InstanceSpecification)element;
		Link commonLink = new Link(link.getName(), link.getID());
		org.w3c.dom.Element data = commonLink.writeToXML(element, this.project, xmlDoc, presentationElement);
		return data;
	}
	
	protected org.w3c.dom.Element createDiagramElementTag(Document xmlDoc, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		Element curElement = presentationElement.getElement();
		
		// Presentation elements without an element attached will not be exported. This includes TextViews and other diagram info and styling not currently supportd.
		if(curElement != null && !curElement.getHumanType().contentEquals("Diagram") && !(curElement instanceof ConnectorEnd)) {
			// Remove the following check as you don't need to filter on export Arrays.asList(this.allowableElements).contains(ExportXmlSysml.getElementType(curElement)
			Rectangle bounds = null;
			try {
				bounds = presentationElement.getBounds();
			} catch(NoRectangleDefinedException nrde) {
				CameoUtils.logGUI("Presentation element with id " + curElement.getID() + " has no bounds. Cannot write position");
			}
			 
			if(curElement != null && bounds != null) {
				// Check whether the actual element is relationship (could be hybrid type), and filter it out from the diagram explicit list
//				if (!(curElement instanceof Relationship) && !(curElement instanceof Connector) && !(curElement instanceof ActivityEdge) && !(curElement instanceof Transition)) {
				if(!this.diagramElementIDs.contains(curElement.getID())) {
					ExportLog.log("Adding presentation element of type " + presentationElement.getClass().toString() + " to diagram.");
					diagramElementIDs.add(curElement.getID());
				
					String curID = curElement.getID();
					String type = "sysml." + curElement.getHumanType().replace(" ", "");
					
					org.w3c.dom.Element elementTag = createDictElement(xmlDoc, Integer.toString(elementCount));
					
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

					this.diagramElementIDs.add(curElement.getID());
					elementCount++;
					return elementTag;
				}
			}
		}
		return null;
	}	
	
	protected org.w3c.dom.Element createDiagramElementTagNoElement(Document xmlDoc, PresentationElement presentationElement, PresentationElement parentPresentationElement, String type) {
		Rectangle bounds = null;
		try {
			bounds = presentationElement.getBounds();
		} catch(NoRectangleDefinedException nrde) {
			CameoUtils.logGUI("Presentation element with id " + presentationElement.getID() + " has no bounds. Cannot write position");
		}
		 
		if(bounds != null) {
			org.w3c.dom.Element elementTag = createDictElement(xmlDoc, Integer.toString(elementCount));
			
			org.w3c.dom.Element idTag = xmlDoc.createElement(XmlTagConstants.ID);
			idTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
			idTag.appendChild(xmlDoc.createTextNode(presentationElement.getID()));
			
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

			elementCount++;
			return elementTag;
		}
		return null;
	}	
	
	protected org.w3c.dom.Element createDiagramRelationshipTag(Document xmlDoc, PresentationElement presentationElement) {
		Element relationship = presentationElement.getElement();
		if(relationship != null) {
			if(!this.diagramElementIDs.contains(relationship.getID())) {
				diagramElementIDs.add(relationship.getID());
				org.w3c.dom.Element relationshipTag = createDictRelationship(xmlDoc, Integer.toString(relationshipCount));
				org.w3c.dom.Element idTag = xmlDoc.createElement(XmlTagConstants.ID);
				idTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
				idTag.appendChild(xmlDoc.createTextNode(relationship.getID()));
				
				org.w3c.dom.Element typeTag = xmlDoc.createElement(XmlTagConstants.TYPE);
				typeTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
				typeTag.appendChild(xmlDoc.createTextNode(relationship.getHumanType()));
				
				org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
				relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
				relationshipTag.appendChild(relDataTag);
				
				// Get Position
				Rectangle bounds = presentationElement.getBounds();
				
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
				
				relDataTag.appendChild(topTag);
				relDataTag.appendChild(bottomTag);
				relDataTag.appendChild(leftTag);
				relDataTag.appendChild(rightTag);
				
				relationshipTag.appendChild(idTag);
				relationshipTag.appendChild(typeTag);

				
				relationshipCount++;
				return relationshipTag;
			}
		}
		return null;		
	}
<<<<<<< HEAD
}
=======
	
	public String[] getAllowableElements() {
		return this.allowableElements;
	}
}


//String importID = xmlElement.getImportID(element.getLocalID());
//CameoUtils.logGUI("Import id : " + importID);
//String parentImportID = xmlElement.getDiagramParent(importID);
//CameoUtils.logGUI("Diagram Parent Import id : " + parentImportID);
//if(parentImportID != null) {
//	String parentCameoID = ImportXmlSysml.idConversion(parentImportID);
//	CameoUtils.logGUI(importID + " should have diagram parent " + parentImportID);
//	PresentationElement parentPresentationElement = this.shapeElements.get(parentCameoID);
//	if(parentPresentationElement == null) {
//		createPresentationElement(project, (Element) project.getElementByID(parentCameoID), locations, presentationDiagram, counter, noPosition);
//	}
//	if(!(parentPresentationElement instanceof AssociationView)) {
//		noPosition = createPresentationElement(project, element, locations, parentPresentationElement, counter, noPosition);
//	} else {
//		noPosition = createPresentationElement(project, element, locations, presentationDiagram, counter, noPosition);
//	}
//} else {
>>>>>>> 03f657b (fixed merge conflicts and import for UAFConstant for resource and projects)
