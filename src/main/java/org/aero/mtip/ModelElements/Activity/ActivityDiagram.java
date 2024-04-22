/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Activity;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.Logger;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.InterruptibleActivityRegionView;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.magicdraw.uml.symbols.shapes.SwimlaneHeaderView;
import com.nomagic.magicdraw.uml.symbols.shapes.SwimlaneView;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;


public class ActivityDiagram extends AbstractDiagram {
	protected Map<String, PresentationElement> presentationElementById = new HashMap<String, PresentationElement> ();
	public ActivityDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = SysMLConstants.SYSML_ACTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTIVITYDIAGRAM;
		 this.cameoDiagramConstant = SysMLConstants.SYSML_ACTIVITY_DIAGRAM;
		 this.allowableElements = SysmlConstants.ACT_TYPES;
	}
	@Override
	public void writeDiagramElementRecursively(org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		writeDiagramEntity(elementListTag, relationshipListTag, presentationElement, parentPresentationElement);
		
		for(PresentationElement subPresentationElement : presentationElement.getPresentationElements()) {
			writeDiagramElementRecursively(elementListTag, relationshipListTag, subPresentationElement, presentationElement);
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void writeDiagramEntity(org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		if(presentationElement instanceof PathElement) {
			writeDiagramRelationship(relationshipListTag, presentationElement);
			return;
		}
		
		if(presentationElement instanceof SwimlaneView && !(presentationElement instanceof SwimlaneHeaderView)) {
			writeDiagramElementNoElement(elementListTag, presentationElement, parentPresentationElement, XmlTagConstants.SWIMLANE);
		} 
		
		if (presentationElement instanceof InterruptibleActivityRegionView) {
			writeDiagramElementNoElement(elementListTag, presentationElement, parentPresentationElement, XmlTagConstants.ATTRIBUTE_NAME_INTERRUPTIBLE_ACTIVITY_REGION);	
		} 

		writeDiagramElement(elementListTag, presentationElement, parentPresentationElement);
	}
	
	@Override
	public boolean createPresentationElement(Project project, Element element, Rectangle location, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		try {
			// Adding Pins to the diagram re-adds their parent element duplicating elements on the diagram on import. Filtering them out fixes this.
			if(element instanceof com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OutputPin
				|| element instanceof com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.InputPin) {
				
				return false;
			}
			
			if(element instanceof com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition) {
				shape = PresentationElementsManager.getInstance().createSwimlane(Collections.emptyList(), (List<? extends com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition>) Arrays.asList(element), (DiagramPresentationElement)presentationDiagram);
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
				
				return false;
			}
			
			if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999 && !SysML.isAssociationBlock(element)) {
				shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
				noPosition = true;
			} 
			
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			
			if(shape == null) {
				Logger.log(String.format("Error placing element %s with Id: %s on diagram.", element.getHumanName(), element.getID()));
				return false;
			}
			
			shapeElements.put(element.getID(), shape);
			presentationElementById.put(element.getID(), shape);
			PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			
		} catch(ClassCastException cce) {
			Logger.log(String.format("Caught Class cast exception adding %s with id %s to activity diagram.", element.getHumanName(), element.getID()));
		}
		
		return noPosition;
	}
}
