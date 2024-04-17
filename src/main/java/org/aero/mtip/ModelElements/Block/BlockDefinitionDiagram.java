/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.shapes.PartView;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class BlockDefinitionDiagram  extends AbstractDiagram {

	public BlockDefinitionDiagram(String name, String EAID) {
		 super(name, EAID);
		 // SysMLConstants.SYSML_BLOCK_DEFINITION_DIAGRAM
		 this.sysmlConstant = "SysML Block Definition Diagram";
		 this.xmlConstant = XmlTagConstants.BLOCKDEFINITIONDIAGRAM;
		 this.allowableElements = SysmlConstants.BDD_TYPES;
		 
	}
	
	@Override
	public  String getSysmlConstant() {
		return this.sysmlConstant;
	}
	
	@Override
	public  String getDiagramType() {
		return XmlTagConstants.BLOCKDEFINITIONDIAGRAM;
	}
	
	public boolean createPresentationElement(Project project, Element element, List<Rectangle> locations, PresentationElement presentationDiagram, int counter, boolean noPosition) throws ReadOnlyElementException {
		Rectangle location = locations.get(counter);
		Point point = new Point(location.x, location.y);
		
		ShapeElement shape = null;
		if (location.x == -999 && location.y == -999 && location.width == -999 && location.height == -999) {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
			this.shapeElements.put(element.getID(), shape);
			noPosition = true;
		} else if(MDCustomizationForSysML.isPartProperty(element) || MDCustomizationForSysML.isValueProperty(element)) {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			if(shape != null) {
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} 
			PartView pv = PresentationElementsManager.getInstance().createPartShape((Property) element, shape, null, false, point);
			this.shapeElements.put(element.getID(), pv);
		} else if(SysML.isAssociationBlock(element)) {
			Element relationship = element;
			Element client = ModelHelper.getClientElement(relationship);
			Element supplier = ModelHelper.getSupplierElement(relationship);
			PresentationElement clientPE = ((DiagramPresentationElement) presentationDiagram).findPresentationElementForPathConnecting(client, null);
			PresentationElement supplierPE = ((DiagramPresentationElement) presentationDiagram).findPresentationElementForPathConnecting(supplier, null);
			
			if(clientPE != null && supplierPE != null) {
				PresentationElementsManager.getInstance().createPathElement(relationship, clientPE ,supplierPE);
			} else {
				ImportLog.log("Client or supplier presentation element does not exist. Could not create representation of relationship on diagram.");
			}
		} else {
			shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
			if(shape != null) {
				PresentationElementsManager.getInstance().reshapeShapeElement(shape, location);
			} 
		}
		if(shape != null) {
			this.shapeElements.put(element.getID(), shape);
		} else {
			ImportLog.log(String.format("Error placing element %s with ID: %s on diagram.", ((NamedElement)element).getName(), element.getID()));
		}
		
		return noPosition;
	}
}
