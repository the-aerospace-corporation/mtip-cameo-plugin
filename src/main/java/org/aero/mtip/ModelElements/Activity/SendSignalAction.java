/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;

public class SendSignalAction extends ActivityNode {

	public SendSignalAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.SEND_SIGNAL_ACTION;
		this.xmlConstant = XmlTagConstants.SEND_SIGNAL_ACTION;
		this.element = f.createSendSignalActionInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction ssa = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction)element;
		
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNAL_TAG)) {
			String signalCameoId = Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.SIGNAL_TAG));
			Signal signal = (Signal)project.getElementByID(signalCameoId);
			ssa.setSignal(signal);
		}
		
		return element;
	}
	
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.SIGNAL_TAG)) {
			String signalID = modelElement.getAttribute(XmlTagConstants.SIGNAL_TAG);
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(signalID));
		}
	}
	
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeSignal(relationships, element);
		
		return data;
	}
	
	private void writeSignal(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction ssa = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction)element;
		Signal signal = ssa.getSignal();
		
		if(signal == null) {
			return;
		}
			
		org.w3c.dom.Element signalTag = XmlWriter.createMtipRelationship(signal, XmlTagConstants.SIGNAL_TAG);
		XmlWriter.add(relationships, signalTag);
	}
}
