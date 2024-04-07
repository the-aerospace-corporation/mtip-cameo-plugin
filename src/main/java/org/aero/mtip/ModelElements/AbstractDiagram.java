/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
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
	protected List<Element> subElements = null;
	
	
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
			element = ModelElementsManager.getInstance().createDiagram(getSysmlConstant(), (Namespace) owner);
		} catch (ReadOnlyElementException e) {
			ImportLog.log(String.format("ReadOnlyElementException encountered creating diagram %s.", getSysmlConstant()));
		}
		
		((NamedElement) element).setName(name);

		return element;
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
			ImportLog.log(String.format("Diagram %s is ready only. No elements will be added.", diagram.getHumanName()));
		}
		
		return noPosition;
	}
	
	public boolean createPresentationElement(Project project, Element element, Rectangle location, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		try {
			if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999 && !SysML.isAssociationBlock(element)) {
				shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
				noPosition = true;
			} else {
				try {
					shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
					PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
				} catch(NullPointerException npe) {
					ImportLog.log(String.format("Null Pointer Exception creating or placing element %s with ID: %s on diagram.", ((NamedElement)element).getName(), element.getID()));
				} catch(IllegalArgumentException iae) {
					ImportLog.log(String.format("Illegal Argument Exception creating or placing element %s with ID: %s on diagram.", ((NamedElement)element).getName(), element.getID()));
				}
			}
		} catch(ClassCastException cce) {
			ImportLog.log(String.format("Caught Class cast exception adding %s to diagram.", element.getID()));
		}
		
		if(shape != null) {
			this.shapeElements.put(element.getID(), shape);
		}
		
		return noPosition;
	}
	
	
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships) throws ReadOnlyElementException {
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
		
		for(PresentationElement presentationElement : presentationDiagram.getPresentationElements()) {
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
				} else {
					ImportLog.log("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
				}
			} catch(ClassCastException cce) {
				ImportLog.log("Class cast exception creating path element.");
			} catch(NullPointerException npe) {
				ImportLog.log("Null pointer exception creating path element.");
			}		
		}
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		List<String> diagramElements = modelElement.getChildElements(parsedXML);
		
		for(String diagramElement : diagramElements) {
			XMLItem diagramElementXML = parsedXML.get(diagramElement);
			ImportXmlSysml.buildElement(project, parsedXML, diagramElementXML);
		}
		
		List<String> diagramRelationships = modelElement.getChildRelationships(parsedXML);
		
		for(String diagramRelationship : diagramRelationships) {
			XMLItem diagramRelationshipXML = parsedXML.get(diagramRelationship);
			ImportXmlSysml.buildRelationship(project, parsedXML, diagramRelationshipXML);
		}
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getType(data.getChildNodes(), XmlTagConstants.RELATIONSHIPS);
		
		org.w3c.dom.Element displayTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.DISPLAY_AS, XmlTagConstants.DISPLAY_AS_DIAGRAM);
		XmlWriter.add(attributes, displayTag);
		
		org.w3c.dom.Element elementListTag = XmlWriter.createTag(XmlTagConstants.ELEMENT, XmlTagConstants.ATTRIBUTE_TYPE_LIST);		
		org.w3c.dom.Element relationshipListTag = XmlWriter.createTag(XmlTagConstants.DIAGRAM_CONNECTOR, XmlTagConstants.ATTRIBUTE_TYPE_LIST);
		
		writeDiagramEntities(elementListTag, relationshipListTag, element);
		
		relationships.appendChild(elementListTag);
		relationships.appendChild(relationshipListTag);
		
		return data;
	}
	
	protected void writeDiagramEntities(org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, Element element) {
		Diagram diagram = (Diagram) element;
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
		presentationDiagram.open();
		
		for(PresentationElement presentationElement : presentationDiagram.getPresentationElements()) {
			writeDiagramElementRecursively(elementListTag, relationshipListTag, presentationElement, null);
		}
		
		presentationDiagram.close();
	}
	
	public void writeDiagramElementRecursively(org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		if(presentationElement instanceof PathElement) {
			if(presentationElement instanceof LinkView) {
//				exportLink(presentationElement);
				return;
			}
			
			writeDiagramRelationship(relationshipListTag, presentationElement);
			return;
		}
		
		writeDiagramElement(elementListTag, presentationElement, parentPresentationElement);
		
		for(PresentationElement subPresentationElement : presentationElement.getPresentationElements()) {
			if(!isValidPresentationElement(presentationElement)) {
				continue;
			}
			
			writeDiagramElementRecursively(elementListTag, relationshipListTag, subPresentationElement, presentationElement);
		}
	}
	
	protected org.w3c.dom.Element exportLink(Document xmlDoc, PresentationElement presentationElement) {
		Element element = presentationElement.getElement();
		InstanceSpecification link = (InstanceSpecification)element;
		Link commonLink = new Link(link.getName(), link.getID());
		org.w3c.dom.Element data = commonLink.writeToXML(element, this.project, xmlDoc, presentationElement);
		return data;
	}
	
	protected void writeDiagramElement(org.w3c.dom.Element elementListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		Element element = presentationElement.getElement();
		
		// Presentation elements without an element attached will not be exported. This includes TextViews and other diagram info and styling not currently supported.
		if (element == null || !isValidDiagramElement(element)) {
			return;
		}
		
		Rectangle bounds = getBounds(presentationElement);

		if(bounds == null) {
			return; 
		}
			
		if(diagramElementIDs.contains(element.getID())) {
			return;
		}
		
		diagramElementIDs.add(element.getID());
		
		org.w3c.dom.Element elementTag = XmlWriter.createMtipDiagramElement(elementCount);
		org.w3c.dom.Element idTag = XmlWriter.createSimpleIdTag(element);
		org.w3c.dom.Element typeTag = XmlWriter.createSimpleTypeTag(element);
		
		writeRelationshipMetadata(elementTag, presentationElement);
		
		XmlWriter.add(elementTag, idTag);
		XmlWriter.add(elementTag, typeTag);				
		XmlWriter.add(elementListTag, elementTag);
		
		elementCount++;
	}	
	
	protected void writeDiagramElementNoElement(org.w3c.dom.Element elementListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement, String type) {
		Rectangle bounds = getBounds(presentationElement);
		
		if(bounds == null) {
			return;
		}
		
		org.w3c.dom.Element elementTag = XmlWriter.createMtipDiagramElement(elementCount);
		@SuppressWarnings("deprecation")
		org.w3c.dom.Element idTag = XmlWriter.createSimpleIdTag(presentationElement.getID());
		org.w3c.dom.Element typeTag = XmlWriter.createSimpleTypeTag(type);
						
		writeRelationshipMetadata(elementTag, presentationElement);

		XmlWriter.add(elementTag, idTag);
		XmlWriter.add(elementTag, typeTag);
		XmlWriter.add(elementListTag, elementTag);
		
		elementCount++;
	}	
	
	protected void writeDiagramRelationship(org.w3c.dom.Element relationshipListTag, PresentationElement presentationElement) {
		Element relationship = presentationElement.getElement();
		
		if(relationship == null) {
			return;
		}
		if(diagramElementIDs.contains(relationship.getID())) {
			return;
		}
		
		diagramElementIDs.add(relationship.getID());
		
		org.w3c.dom.Element relationshipTag = XmlWriter.createMtipDiagramConnector(relationshipCount);
		org.w3c.dom.Element idTag = XmlWriter.createSimpleIdTag(relationship);
		org.w3c.dom.Element typeTag = XmlWriter.createSimpleTypeTag(relationship);
		
		writeRelationshipMetadata(relationshipTag, presentationElement);
	
		XmlWriter.add(relationshipTag, idTag);
		XmlWriter.add(relationshipTag, typeTag);
		XmlWriter.add(relationshipListTag, relationshipTag);

		relationshipCount++;
	}
	
	protected void writeRelationshipMetadata(org.w3c.dom.Element diagramElementTag, PresentationElement presentationElement) {
		org.w3c.dom.Element relDataTag = XmlWriter.createTag(XmlTagConstants.RELATIONSHIP_METADATA, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		Rectangle bounds = presentationElement.getBounds();
		
		org.w3c.dom.Element topTag = XmlWriter.createTag(XmlTagConstants.TOP, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(-bounds.y));
		org.w3c.dom.Element bottomTag = XmlWriter.createTag(XmlTagConstants.BOTTOM, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(-bounds.y - bounds.height));
		org.w3c.dom.Element leftTag = XmlWriter.createTag(XmlTagConstants.LEFT, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(bounds.x));
		org.w3c.dom.Element rightTag = XmlWriter.createTag(XmlTagConstants.RIGHT, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(bounds.x + bounds.width));
	
		XmlWriter.add(relDataTag, topTag);
		XmlWriter.add(relDataTag, bottomTag);
		XmlWriter.add(relDataTag, leftTag);
		XmlWriter.add(relDataTag, rightTag);
		
		XmlWriter.add(diagramElementTag, relDataTag);
	}
	
	protected boolean isValidDiagramElement(Element element) {
		if (element.getHumanType().contentEquals("Diagram") || element instanceof ConnectorEnd) {
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	protected boolean isValidPresentationElement(PresentationElement presentationElement) {
		if (presentationElement instanceof com.nomagic.magicdraw.uml.symbols.shapes.RoleView) {
			return false;
		}
		
		return true;
	}
	
	protected Rectangle getBounds(PresentationElement presentationElement) {
		try {
			Rectangle bounds = presentationElement.getBounds();
			return bounds;
		} catch(NoRectangleDefinedException nrde) {
			return null;
		}
	}
}