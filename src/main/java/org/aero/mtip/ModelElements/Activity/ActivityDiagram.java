/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.magicdraw.uml.symbols.shapes.SwimlaneHeaderView;
import com.nomagic.magicdraw.uml.symbols.shapes.SwimlaneView;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;


public class ActivityDiagram extends AbstractDiagram {

	public ActivityDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_ACTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTIVITYDIAGRAM;
		 this.allowableElements = SysmlConstants.ACT_TYPES;
	}
	@Override
	public void writeElement(Document xmlDoc, org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		if(presentationElement instanceof PathElement) {
			org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presentationElement);
			if(relationshipTag != null) {
				relationshipListTag.appendChild(relationshipTag);
			}
		} else if(presentationElement instanceof SwimlaneView && !(presentationElement instanceof SwimlaneHeaderView)) {
			org.w3c.dom.Element elementTag = createSwimlaneTag(xmlDoc, presentationElement, parentPresentationElement);
			if(elementTag != null) {
				elementListTag.appendChild(elementTag);
			}	
		} else {
			org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presentationElement, parentPresentationElement);
			if(elementTag != null) {
				elementListTag.appendChild(elementTag);
			}
		}
		
		for(PresentationElement subPresentationElement : presentationElement.getPresentationElements()) {
			writeElement(xmlDoc, elementListTag, relationshipListTag, subPresentationElement, presentationElement);
		}
	}
	
	public org.w3c.dom.Element createSwimlaneTag(Document xmlDoc, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		SwimlaneView slv = (SwimlaneView)presentationElement;
		org.w3c.dom.Element swimlaneTag = createDiagramElementTagNoElement(xmlDoc, presentationElement, parentPresentationElement, XmlTagConstants.SWIMLANE);
		
		return swimlaneTag;
	}
	
	@Override
	public boolean createPresentationElement(Project project, Element element, List<Rectangle> locations, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Rectangle location = locations.get(counter);
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		try {
			if(element instanceof com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition) {
				CameoUtils.logGUI("Creating swimlane view for ActivityPartition with id: " + element.getLocalID());
				shape = PresentationElementsManager.getInstance().createSwimlane(Collections.emptyList(), (List<? extends com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition>) Arrays.asList(element), (DiagramPresentationElement)presentationDiagram);
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} else if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999 && !CameoUtils.isAssociationBlock(element, project)) {
				shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
				noPosition = true;
			} else {
				shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
				if(shape != null) {
					PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
				} else {
					CameoUtils.logGUI("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getLocalID() + " on diagram.");
					ImportLog.log("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getLocalID() + " on diagram.");
				}
			}
		} catch(ClassCastException cce) {
			CameoUtils.logGUI("Caught Class cast exception adding " + element.getHumanName() + " " + "with id " + element.getLocalID() + " to diagram.");
			ImportLog.log("Caught Class cast exception adding " + element.getLocalID() + " to diagram.");
		}
		
		if(shape != null) {
			CameoUtils.logGUI("Placing element " + ((NamedElement)element).getName() + " at x:" + Integer.toString(location.x) + " y:" + Integer.toString(location.y));
			this.shapeElements.put(element.getLocalID(), shape);
		}
		return noPosition;
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_ACTIVITY_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.ACTIVITYDIAGRAM;
	}

}
