
/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.core;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.metamodel.core.general.Link;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.NoRectangleDefinedException;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.LinkView;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.paths.TransitionView;
import com.nomagic.magicdraw.uml.symbols.shapes.ImageView;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.magicdraw.uml.symbols.shapes.TransitionToSelfView;
import com.nomagic.ui.ResizableIcon;
import com.nomagic.uml2.ext.jmi.helpers.ElementImageHelper;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;

public abstract class AbstractDiagram extends CommonElement {
	private static Map<String,String> cameoToMtipType = createCameoToMtipMap();
	
	protected int elementCount = 0;
	protected int relationshipCount = 0;
	protected HashMap<String, ShapeElement> shapeElements = new HashMap<String, ShapeElement>();
	protected List<String> diagramElementIDs = new ArrayList<String> ();
	
	protected List<Element> subElements = null;
	protected String cameoDiagramConstant = null;	
	protected Element exportingDiagram = null;
	
	protected boolean hasNoPositionData = true;
	
    public static String getMetamodelConstant(String cameoDiagramConstant) {
      return cameoToMtipType.get(cameoDiagramConstant);
    }
    
	public AbstractDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	public String getCameoDiagramConstant() {
		return this.cameoDiagramConstant;
	}
	
	public String getDiagramType() {
		return this.xmlConstant;
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if (getCameoDiagramConstant() == null) {
			Logger.log(String.format("Internal error. No diagram constant defined for %s", xmlElement.getType()));
			return null;
		}
		
		try {
			element = ModelElementsManager.getInstance().createDiagram(getCameoDiagramConstant(), (Namespace) owner);
		} catch (ReadOnlyElementException e) {
			Logger.log(String.format("ReadOnlyElementException encountered creating diagram %s.", getCameoDiagramConstant()));
		} catch (NullPointerException npe) {
			Logger.log(String.format("Failed to create diagram based on cameo diagram constant %s for id %s", getCameoDiagramConstant(), xmlElement.getImportId()));
			return null;
		}
		
		((NamedElement) element).setName(name);
		return element;
	}
	
	public void addElements(Project project, Diagram diagram, HashMap<Element, Rectangle> elements, XMLItem xmlElement) {
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
			
		for (Map.Entry<Element, Rectangle> entry : elements.entrySet()) {
			createPresentationElement(project, entry.getKey(), entry.getValue(), presentationDiagram);
		}
	}
	
	public void createPresentationElement(Project project, Element element, Rectangle location, PresentationElement presentationDiagram) {
		ShapeElement shape = null;
		boolean hasLocation = hasLocation(location);
		
		try {
    	  if (hasLocation) {
            shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, new Point(location.x, location.y));
            PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
            hasNoPositionData = false;
    	  } else {
            shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
    	  }
		} catch (NullPointerException npe) {
			Logger.logException(npe);
		} catch (IllegalArgumentException iae) {
			Logger.logException(iae);
		} catch (ClassCastException cce) {
			Logger.logException(cce);
		} catch (ReadOnlyElementException roee) {
		    Logger.logException(roee);
		}
	}
	
	
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships) {
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
		removeAutopopulatedPathElements(presentationDiagram);
		
		for (Element relationship : relationships) {
			Element client = ModelHelper.getClientElement(relationship);
			Element supplier = ModelHelper.getSupplierElement(relationship);
			PresentationElement clientPE = presentationDiagram.findPresentationElementForPathConnecting(client, null);
			PresentationElement supplierPE = presentationDiagram.findPresentationElementForPathConnecting(supplier, null);
			
			try {
				if(clientPE != null && supplierPE != null) {
					PresentationElementsManager.getInstance().createPathElement(relationship, clientPE ,supplierPE);
				} else {
					Logger.log("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
				}
			} catch(ClassCastException cce) {
				Logger.log(String.format("Class cast exception creating path element %s.", relationship.getHumanName()));
				Logger.logException(cce);
			} catch(NullPointerException npe) {
				Logger.log(String.format("Null pointer exception creating path element %s.", relationship.getHumanName()));
				Logger.logException(npe);
			} catch (ReadOnlyElementException roee) {
			    Logger.logException(roee);
			}
		}
	}
	
	protected void removeAutopopulatedPathElements(DiagramPresentationElement presentationDiagram) {
	  try {
	    for(PresentationElement presentationElement : presentationDiagram.getPresentationElements()) {
          if(presentationElement instanceof PathElement) {
            PresentationElementsManager.getInstance().deletePresentationElement(presentationElement);
          }
        }
	  } catch (ReadOnlyElementException roee) {
	    Logger.logException(roee);
	  }
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		List<String> diagramElements = modelElement.getChildElements(parsedXML);
		
		for(String diagramElement : diagramElements) {
			XMLItem diagramElementXML = parsedXML.get(diagramElement);
			Importer.getInstance().buildElement(parsedXML, diagramElementXML);
		}
		
		List<String> diagramRelationships = modelElement.getChildRelationships(parsedXML);
		
		for(String diagramRelationship : diagramRelationships) {
			XMLItem diagramRelationshipXML = parsedXML.get(diagramRelationship);
			Importer.getInstance().buildRelationship(parsedXML, diagramRelationshipXML);
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
		if(presentationElement instanceof PathElement 
				|| presentationElement instanceof TransitionView
				|| presentationElement instanceof TransitionToSelfView) {
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
		Link commonLink = new Link(link.getName(), MtipUtils.getId(link));
		org.w3c.dom.Element data = commonLink.writeToXML(element, this.project, xmlDoc, presentationElement);
		return data;
	}
	
	protected void writeDiagramElement(org.w3c.dom.Element elementListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		Element element = presentationElement.getElement();
		
		// Presentation elements without an element attached will not be exported. This includes TextViews and other diagram info and styling not currently supported.
		if (element == null
				|| !isValidDiagramElement(element)
				|| !MtipUtils.isSupported(element)) {
		    if (element != null) {
		        Logger.log(String.format("Not exporting element %s, Invalid.", element.getHumanName()));
		    }
			return;
		}
		
		if(isAdded(element)) {
			return;
		}
		
		Rectangle bounds = getBounds(presentationElement);

		if(bounds == null) {
		    Logger.log(String.format("Not exporting element %s. No bounds.", element.getHumanName()));
			return; 
		}
		
		diagramElementIDs.add(MtipUtils.getId(element));
		
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
		
		if (relationship == null
				|| isAdded(relationship)
				|| !MtipUtils.isSupported(relationship)) {
			return;
		}
		
		diagramElementIDs.add(MtipUtils.getId(relationship));
		
		org.w3c.dom.Element relationshipTag = XmlWriter.createMtipDiagramConnector(relationshipCount);
		org.w3c.dom.Element idTag = XmlWriter.createSimpleIdTag(relationship);
		org.w3c.dom.Element typeTag = XmlWriter.createSimpleTypeTag(relationship);
		
		if (presentationElement instanceof PathElement) {
		  writeRelationshipMetadataConnector(relationshipTag, (PathElement)presentationElement);
		}
	
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
		
		writeImageMetadata(relDataTag, presentationElement);
				
		XmlWriter.add(diagramElementTag, relDataTag);
	}
	
	protected void writeImageMetadata(org.w3c.dom.Element relDataTag, PresentationElement presentationElement) {
	  if (!hasImage(presentationElement)) {	    
	    return;
	  }
	  
      org.w3c.dom.Element imageTag = XmlWriter.createImageTag(presentationElement);
      
      if (imageTag == null) {
        return;
      }
      
      XmlWriter.add(relDataTag, imageTag);
	}
	
	protected void writeRelationshipMetadataConnector(org.w3c.dom.Element diagramRelationshipTag, PathElement pathElement) {
	  if (pathElement.getSupplierPoint() == null || pathElement.getClientPoint() == null) {
	    return;
	  }
	  
	  org.w3c.dom.Element relDataTag = XmlWriter.createTag(XmlTagConstants.RELATIONSHIP_METADATA, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
	  
	  org.w3c.dom.Element supplierPointTag = XmlWriter.createTag(XmlTagConstants.SUPPLIER_POINT, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
	  writeCoordinates(supplierPointTag, pathElement.getSupplierPoint());
	  
	  org.w3c.dom.Element clientPointTag = XmlWriter.createTag(XmlTagConstants.CLIENT_POINT, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
	  writeCoordinates(clientPointTag, pathElement.getClientPoint());
	  
	  org.w3c.dom.Element breakPointsTag = XmlWriter.createTag(XmlTagConstants.BREAK_POINT, XmlTagConstants.ATTRIBUTE_TYPE_LIST);
	  
	  for (int i = 0; i < pathElement.getBreakPoints().size(); i++) {
	    org.w3c.dom.Element breakPointTag = XmlWriter.createTag(XmlTagConstants.BREAK_POINT, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
	    XmlWriter.addAttributeKey(breakPointTag, Integer.toString(i));
	    
	    writeCoordinates(breakPointTag, pathElement.getBreakPoints().get(i));
	    
	    XmlWriter.add(breakPointsTag, breakPointTag);
	  }
	  
	  XmlWriter.add(relDataTag, supplierPointTag);
	  XmlWriter.add(relDataTag, clientPointTag);
	  XmlWriter.add(relDataTag, breakPointsTag);
	  XmlWriter.add(diagramRelationshipTag, relDataTag);
	}
	
	protected void writeCoordinates(org.w3c.dom.Element pointTag, Point point) {
	  org.w3c.dom.Element xCoordTag = XmlWriter.createTag(XmlTagConstants.X_COORDINATE, XmlTagConstants.ATTRIBUTE_TYPE_INT);
	  XmlWriter.setText(xCoordTag, Integer.toString(point.x));
	  
	  org.w3c.dom.Element yCoordTag = XmlWriter.createTag(XmlTagConstants.Y_COORDINATE, XmlTagConstants.ATTRIBUTE_TYPE_INT);
	  XmlWriter.setText(yCoordTag, Integer.toString(point.y));
	  
	  XmlWriter.add(pointTag, xCoordTag);
	  XmlWriter.add(pointTag, yCoordTag);
	}
	
	protected boolean isValidDiagramElement(Element element) {
		if (element.getHumanType().contentEquals("Diagram") 
				|| element instanceof ConnectorEnd
				|| element instanceof Region) {
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
	
	@SuppressWarnings("deprecation")
    @CheckForNull
	protected ImageView getImageView(PresentationElement presentationElement) {
	  return (ImageView)presentationElement.getPresentationElements()
	      .stream()
	      .filter(x -> x instanceof ImageView && ((ImageView)x).getBounds().width != 0)
	      .findFirst()
	      .orElseGet(() -> null);
	}
	
	protected boolean isAdded(Element element) {
		return diagramElementIDs.contains(MtipUtils.getId(element));
	}
	
	protected boolean hasLocation(Rectangle location) {
	  if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999 && !SysML.isAssociationBlock(element)) {
	    return false;
	  }
	  
	  return true;
	}
	
	public boolean hasNoPositionData() {
	  return hasNoPositionData;
	}
	
	protected boolean hasImage(PresentationElement presentationElement) {
	  if (presentationElement.getElement() == null) {
	    return false;
	  }
	  
	  return MagicDraw.hasCustomImageHolderStereotype(presentationElement.getElement());
	}
	
	private static Map<String, String> createCameoToMtipMap() {
	  Map<String, String> aMap = new HashMap<String,String>();

	  // UML/Profile diagrams
      aMap.put(CameoDiagramConstants.CLASS_DIAGRAM, SysmlConstants.CLASSDIAGRAM);
      aMap.put(CameoDiagramConstants.PROFILE_DIAGRAM, SysmlConstants.PROFILEDIAGRAM);

      // SysML Diagrams
      aMap.put(SysMLConstants.SYSML_ACTIVITY_DIAGRAM, SysmlConstants.ACT );
      aMap.put(SysMLConstants.SYSML_BLOCK_DEFINITION_DIAGRAM, SysmlConstants.BDD );
      aMap.put(SysMLConstants.SYSML_INTERNAL_BLOCK_DIAGRAM, SysmlConstants.IBD );
      aMap.put(SysMLConstants.SYSML_PACKAGE_DIAGRAM, SysmlConstants.PKG );
      aMap.put(SysMLConstants.SYSML_PARAMETERIC_DIAGRAM, SysmlConstants.PAR);
      aMap.put(SysMLConstants.SYSML_SEQUENCE_DIAGRAM, SysmlConstants.SEQ);
      aMap.put(SysMLConstants.SYSML_STATE_MACHINE_DIAGRAM, SysmlConstants.STM);
      aMap.put(SysMLConstants.SYSML_USE_CASE_DIAGRAM, SysmlConstants.UC);

      // Matrices
      aMap.put("Dependency Matrix", SysmlConstants.DEPENDENCY_MATRIX);
      aMap.put("Derive Requirement Matrix", SysmlConstants.DERIVE_REQUIREMENT_MATRIX);
      aMap.put("Refine Requirement Matrix", SysmlConstants.REFINE_REQUIREMENT_MATRIX);
      aMap.put("Satisfy Requirement Matrix", SysmlConstants.SATISFY_REQUIREMENT_MATRIX);
      aMap.put("SysML Allocation Matrix", SysmlConstants.ALLOCATION_MATRIX);
      aMap.put("Verify Requirement Matrix", SysmlConstants.VERIFY_REQUIREMENT_MATRIX);
      
      // Tables
      aMap.put("Generic Table", SysmlConstants.GENERIC_TABLE);
      aMap.put("Glossary Table", SysmlConstants.GLOSSARY_TABLE);
      aMap.put("Instance Table", SysmlConstants.INSTANCE_TABLE);
      aMap.put("Metric Table", SysmlConstants.METRIC_TABLE);
      aMap.put("Requirement Table", SysmlConstants.REQUIREMENT_TABLE);
      
//    aMap.put("Component Diagram", );
//    aMap.put("Object Diagram", );
//      aMap.put("Deployment Diagram", );
//      aMap.put("Communication Diagram", );
//      aMap.put("Protocol State Machine Diagram", );
//      aMap.put("Composite Structure Diagram", );
//      aMap.put("Interaction Overview Diagram", );
//      aMap.put("Any Diagram", );
//      aMap.put("Static Diagram", );
//      aMap.put("Behavior Diagram", );
//      aMap.put("Interaction Diagram", );
//      aMap.put("Free Form Diagram", );
//      aMap.put("Networking Diagram", );
//      aMap.put("Simulation Configuration Diagram", );
//      aMap.put("Time Diagram", );
//      aMap.put("User Interface Modeling Diagram", );
//      aMap.put("Views and Viewpoints Diagram", );
//      aMap.put("Content Diagram", );
//      aMap.put("Activity Decomposition Map", );
//      aMap.put("Instance Map", );
//      aMap.put("Relation Map Diagram", );
//      aMap.put("Requirement Containment Map", );
//      aMap.put("Requirement Derivation Map", );
//      aMap.put("Structure Decomposition Map", );
//      aMap.put("ProfileUpgradeStereotypesOrTagsMappingTable", );
//      aMap.put("ProfileUpgradeStereotypesOrTagsClearingTable", );
//      aMap.put("Blackbox ICD Table", );
//      aMap.put("Whitebox ICD Table", );
      
      // Operational
      aMap.put(CameoDiagramConstants.OPERATIONAL_PROCESS_FLOW, UAFConstants.OPERATIONAL_PROCESS_FLOW);
      aMap.put(CameoDiagramConstants.OPERATIONAL_CONNECTIVITY, UAFConstants.OPERATIONAL_CONNECTIVITY);
      aMap.put(CameoDiagramConstants.OPERATIONAL_CONSTRAINTS_DEFINITION, UAFConstants.OPERATIONAL_CONSTRAINTS_DEFINITION);
      aMap.put(CameoDiagramConstants.OPERATIONAL_FREE_FORM_TAXONOMY, UAFConstants.OPERATIONAL_FREE_FORM_TAXONOMY);
      aMap.put(CameoDiagramConstants.OPERATIONAL_PROCESSES, UAFConstants.OPERATIONAL_PROCESSES_DIAGRAM);
      
      aMap.put(CameoDiagramConstants.OPERATIONAL_STRUCTURE, UAFConstants.OPERATIONAL_STRUCTURE);
      aMap.put(CameoDiagramConstants.OPERATIONAL_TAXONOMY, UAFConstants.OPERATIONAL_TAXONOMY);
      aMap.put(CameoDiagramConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY, UAFConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY);
      aMap.put(CameoDiagramConstants.OPERATIONAL_INTERACTION_SCENARIOS, UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS);
      aMap.put(CameoDiagramConstants.OPERATIONAL_INTERNAL_CONNECTIVITY, UAFConstants.OPERATIONAL_INTERNAL_CONNECTIVITY);
      aMap.put(CameoDiagramConstants.OPERATIONAL_PARAMETRIC, UAFConstants.OPERATIONAL_PARAMETRIC);
      aMap.put(CameoDiagramConstants.OPERATIONAL_STATES, UAFConstants.OPERATIONAL_STATES);
      
      // Actual Resources
      aMap.put(CameoDiagramConstants.ACTUAL_RESOURCES_CONNECTIVITY, UAFConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.ACTUAL_RESOURCES_STRUCTURE, UAFConstants.ACTUAL_RESOURCES_STRUCTURE_DIAGRAM);
      
      // Parameters
      aMap.put(CameoDiagramConstants.ENVIRONMENT, UAFConstants.ENVIRONMENT_DIAGRAM);
      
      // Personnel
      aMap.put(CameoDiagramConstants.PERSONNEL_CONNECTIVITY, UAFConstants.PERSONNEL_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_INTERACTION_SCENARIOS, UAFConstants.PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_INTERNAL_CONNECTIVITY, UAFConstants.PERSONNEL_INTERNAL_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_PROCESS_FLOW, UAFConstants.PERSONNEL_PROCESSES_FLOW_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_PROCESSES, UAFConstants.PERSONNEL_PROCESSES_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_STATES, UAFConstants.PERSONNEL_STATES_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_STRUCTURE, UAFConstants.PERSONNEL_STRUCTURE_DIAGRAM);
      aMap.put(CameoDiagramConstants.PERSONNEL_TAXONOMY, UAFConstants.PERSONNEL_TAXONOMY_DIAGRAM);

      // Projects
      aMap.put(CameoDiagramConstants.PROJECTS_TAXONOMY, UAFConstants.PROJECTS_TAXONOMY_DIAGRAM);
      aMap.put(CameoDiagramConstants.PROJECTS_STRUCTURE, UAFConstants.PROJECTS_STRUCTURE_DIAGRAM);
      aMap.put(CameoDiagramConstants.PROJECTS_CONNECTIVITY, UAFConstants.PROJECTS_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.PROJECTS_PROCESSES, UAFConstants.PROJECTS_PROCESSES_DIAGRAM);
      
      //Resources
      aMap.put(CameoDiagramConstants.RESOURCES_CONNECTIVITY, UAFConstants.RESOURCES_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.RESOURCES_INTERACTION_SCENARIOS, UAFConstants.RESOURCES_INTERACTION_SCENARIOS_DIAGRAM);
      aMap.put(CameoDiagramConstants.RESOURCES_PROCESSES, UAFConstants.RESOURCES_PROCESSES_DIAGRAM);
      aMap.put(CameoDiagramConstants.RESOURCES_STATES, UAFConstants.RESOURCES_STATES_DIAGRAM);
      aMap.put(CameoDiagramConstants.RESOURCES_STRUCTURE, UAFConstants.RESOURCES_STRUCTURE_DIAGRAM);
      aMap.put(CameoDiagramConstants.RESOURCES_TAXONOMY, UAFConstants.RESOURCES_TAXONOMY_DIAGRAM);
      aMap.put(CameoDiagramConstants.RESOURCES_PROCESS_FLOW, UAFConstants.RESOURCES_PROCESS_FLOW);
      
      // Security
      aMap.put(CameoDiagramConstants.SECURITY_TAXONOMY, UAFConstants.SECURITY_TAXONOMY_DIAGRAM);
      aMap.put(CameoDiagramConstants.SECURITY_STRUCTURE, UAFConstants.SECURITY_STRUCTURE_DIAGRAM);
      aMap.put(CameoDiagramConstants.SECURITY_CONNECTIVITY, UAFConstants.SECURITY_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.SECURITY_PROCESSES, UAFConstants.SECURITY_PROCESSES_DIAGRAM);
      aMap.put(CameoDiagramConstants.SECURITY_PROCESSES_FLOW, UAFConstants.SECURITY_PROCESSES_FLOW_DIAGRAM);
      aMap.put(CameoDiagramConstants.SECURITY_CONSTRAINTS, UAFConstants.SECURITY_CONSTRAINTS_DIAGRAM);

      // Services
      aMap.put(CameoDiagramConstants.SERVICES_CONNECTIVITY, UAFConstants.SERVICES_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.SERVICES_CONSTRAINTS_DEFINITION, UAFConstants.SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM);
      aMap.put(CameoDiagramConstants.SERVICES_INTERACTION_SCENARIOS, UAFConstants.SERVICES_INTERACTION_SCENARIOS_DIAGRAM);
      aMap.put(CameoDiagramConstants.SERVICES_PROCESSES, UAFConstants.SERVICES_PROCESSES_DIAGRAM);
      aMap.put(CameoDiagramConstants.SERVICES_STATES, UAFConstants.SERVICES_STATES_DIAGRAM);
      aMap.put(CameoDiagramConstants.SERVICES_STRUCTURE, UAFConstants.SERVICES_STRUCTURE_DIAGRAM);
      aMap.put(CameoDiagramConstants.SERVICES_TAXONOMY, UAFConstants.SERVICES_TAXONOMY_DIAGRAM);
      
      // Standards
      aMap.put("Standards Taxonomy", UAFConstants.STANDARDS_TAXONOMY_DIAGRAM);
      aMap.put("Standards Structure", UAFConstants.STANDARDS_STRUCTURE_DIAGRAM);
      
      // Strategic
      aMap.put(CameoDiagramConstants.STRATEGIC_TAXONOMY, UAFConstants.STRATEGIC_TAXONOMY_DIAGRAM);
      aMap.put(CameoDiagramConstants.STRATEGIC_STRUCTURE, UAFConstants.STRATEGIC_STRUCTURE_DIAGRAM);
      aMap.put(CameoDiagramConstants.STRATEGIC_CONNECTIVITY, UAFConstants.STRATEGIC_CONNECTIVITY_DIAGRAM);
      aMap.put(CameoDiagramConstants.STRATEGIC_STATES, UAFConstants.STRATEGIC_STATES_DIAGRAM);
      aMap.put(CameoDiagramConstants.STRATEGIC_CONSTRAINTS, UAFConstants.STRATEGIC_CONSTRAINTS_DIAGRAM);
      
      // Summary and Overview
      aMap.put(CameoDiagramConstants.SUMMARY_AND_OVERVIEW, UAFConstants.SUMMARY_AND_OVERVIEW_DIAGRAM);
      
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

      aMap.put("AV-1 Overview and Summary Information", DoDAFConstants.AV1);
//      aMap.put("AV-2?", DoDAFConstants.AV2);
      
      aMap.put("DIV-1 Conceptual Data Model", DoDAFConstants.DIV1);
      aMap.put("DIV-2 Logical Data Model", DoDAFConstants.DIV2);
      aMap.put("DIV-3 Physical Data Model", DoDAFConstants.DIV3);
      
      aMap.put("PV-1 Project Portfolio Relationships", DoDAFConstants.PV1);
//      aMap.put("PV-2", DoDAFConstants.PV2);
//      aMap.put("PV-3", DoDAFConstants.PV3);
      
      return Collections.unmodifiableMap(aMap);
	}
}
