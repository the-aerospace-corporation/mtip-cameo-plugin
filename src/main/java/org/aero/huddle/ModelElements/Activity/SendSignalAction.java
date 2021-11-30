package org.aero.huddle.ModelElements.Activity;

import java.util.Map;

import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;

public class SendSignalAction extends ActivityNode {

	public SendSignalAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.SENDSIGNALACTION;
		this.xmlConstant = XmlTagConstants.SENDSIGNALACTION;
		this.sysmlElement = f.createSendSignalActionInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction ssa = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction)sysmlElement;
		
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNAL_TAG)) {
			String signalCameoId = ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.SIGNAL_TAG));
			Signal signal = (Signal)project.getElementByID(signalCameoId);
			ssa.setSignal(signal);
		}
		
		return sysmlElement;
	}
	
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.SIGNAL_TAG)) {
			String signalID = modelElement.getAttribute(XmlTagConstants.SIGNAL_TAG);
			ImportXmlSysml.getOrBuildElement(project, parsedXML, signalID);
		}
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction ssa = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction)element;
		Signal signal = ssa.getSignal();
		if(signal != null) {
			CameoUtils.logGUI("Send signal action with id " + this.EAID + " has signal with id " + signal.getLocalID());
			org.w3c.dom.Element signalTag = createRel(xmlDoc, signal, XmlTagConstants.SIGNAL_TAG);
			relationships.appendChild(signalTag);
		} else {
			CameoUtils.logGUI("Send signal action with id " + this.EAID + " has no signal.");
		}
		return data;
	}
}
