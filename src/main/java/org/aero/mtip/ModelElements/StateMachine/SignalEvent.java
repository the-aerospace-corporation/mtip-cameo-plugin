/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;

public class SignalEvent extends CommonElement {
	private Signal signalElement;
	private final String signalElementTag = "signalElement";

	public SignalEvent(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.SIGNALEVENT;
		this.xmlConstant = XmlTagConstants.SIGNALEVENT;
		this.element = f.createSignalEventInstance();
	}
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent se = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent)super.createElement(project, owner, xmlElement);
		if(xmlElement.hasElement(signalElementTag)) {
			Signal signal = (Signal) xmlElement.getElement(signalElementTag);
			se.setSignal(signal);
		} else {
			CameoUtils.logGUI("Signal event with id: " + EAID + " has no signal element.");
		}
		return se;
	}
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem xmlElement) {
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNAL_TAG)) {
			String signal = xmlElement.getAttribute(XmlTagConstants.SIGNAL_TAG);
			signalElement = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal)ImportXmlSysml.getOrBuildElement(project, parsedXML, signal);
			xmlElement.addElement(signalElementTag, signalElement);
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());

		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent se= (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent)element;
		Signal signal = se.getSignal();
		if(signal != null) {
			org.w3c.dom.Element signalTag = createRel(xmlDoc, signal, XmlTagConstants.SIGNAL_TAG);
			relationships.appendChild(signalTag);
		}
		
		return data;
	}

}
