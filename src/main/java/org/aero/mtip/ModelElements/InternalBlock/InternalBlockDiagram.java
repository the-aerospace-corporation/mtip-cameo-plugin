/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.InternalBlock;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;

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
		 this.metamodelConstant = SysMLConstants.SYSML_INTERNAL_BLOCK_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.INTERNALBLOCKDIAGRAM;
		 this.cameoDiagramConstant = SysMLConstants.SYSML_INTERNAL_BLOCK_DIAGRAM;
		 this.allowableElements = SysmlConstants.IBD_TYPES;
	}
	
	public boolean createPresentationElement(Project project, Element element, List<Rectangle> locations, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Rectangle location = locations.get(counter);
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999) {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
			this.shapeElements.put(element.getID(), shape);
			noPosition = true;
		} else if(MDCustomizationForSysMLProfile.isPartProperty(element) || MDCustomizationForSysMLProfile.isValueProperty(element)) {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			if(shape != null) {
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} 
			PartView pv = PresentationElementsManager.getInstance().createPartShape((Property) element, shape, null, false, point);
			this.shapeElements.put(element.getID(), pv);
		} else {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			if(shape != null) {
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} 
		}
		if(shape != null) {
			CameoUtils.logGUI("Placing element " + ((NamedElement)element).getName() + " at x:" + Integer.toString(location.x) + " y:" + Integer.toString(location.y));
			this.shapeElements.put(element.getID(), shape);
		} else {
			CameoUtils.logGUI("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getID() + " on diagram.");
			ImportLog.log("Error placing element " + ((NamedElement)element).getName() + " with ID: " + element.getID() + " on diagram.");
		}
		return noPosition;
	}
}
