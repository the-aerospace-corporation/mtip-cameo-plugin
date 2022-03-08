/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.InternalBlock;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.shapes.PartView;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class InternalBlockDiagram  extends AbstractDiagram{

	public InternalBlockDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_INTERNAL_BLOCK_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.INTERNALBLOCKDIAGRAM;
		 this.allowableElements = SysmlConstants.IBD_TYPES;
	}

	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_INTERNAL_BLOCK_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.INTERNALBLOCKDIAGRAM;
	}
	
	public boolean createPresentationElement(Project project, Element element, List<Rectangle> locations, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Rectangle location = locations.get(counter);
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999) {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
			this.shapeElements.put(element.getLocalID(), shape);
			noPosition = true;
		} else if(MDCustomizationForSysMLProfile.isPartProperty(element) || MDCustomizationForSysMLProfile.isValueProperty(element)) {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			if(shape != null) {
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} 
			PartView pv = PresentationElementsManager.getInstance().createPartShape((Property) element, shape, null, false, point);
			this.shapeElements.put(element.getLocalID(), pv);
		} else {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			if(shape != null) {
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} 
		}
		if(shape != null) {
			CameoUtils.logGUI("Placing element " + ((NamedElement)element).getName() + " at x:" + Integer.toString(location.x) + " y:" + Integer.toString(location.y));
			this.shapeElements.put(element.getLocalID(), shape);
		} else {
			CameoUtils.logGUI("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getLocalID() + " on diagram.");
			ImportLog.log("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getLocalID() + " on diagram.");
		}
		return noPosition;
	}
}
