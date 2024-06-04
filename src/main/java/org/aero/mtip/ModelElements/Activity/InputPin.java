/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class InputPin extends ActivityNode {


	public InputPin(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.INPUT_PIN;
		this.xmlConstant = XmlTagConstants.INPUT_PIN;
		this.element = f.createInputPinInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element element = super.createElement(project, owner, xmlElement);
		
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.InputPin inputPin = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.InputPin)element;
		
		if(xmlElement.hasAttribute(XmlTagConstants.SYNC_ELEMENT)) {
			element.setSyncElement((Element) project.getElementByID(xmlElement.getAttribute(Importer.idConversion(XmlTagConstants.SYNC_ELEMENT))));
		}
		return inputPin;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		super.createDependentElements(parsedXML, modelElement);
		if(modelElement.hasAttribute(XmlTagConstants.SYNC_ELEMENT)) {
			String syncElementId = modelElement.getAttribute(XmlTagConstants.SYNC_ELEMENT);
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(syncElementId));
		}
	}
	@Override
	public void setOwner(Element owner) {
		if(!(owner instanceof com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Action)) {
			owner = CameoUtils.findNearestActivity(owner);
		}
		element.setOwner(owner);
	}
}
