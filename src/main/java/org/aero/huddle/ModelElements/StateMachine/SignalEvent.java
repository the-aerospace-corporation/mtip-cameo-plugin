package org.aero.huddle.ModelElements.StateMachine;

import java.util.List;
import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
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
		this.sysmlConstant = SysmlConstants.SIGNALEVENT;
		this.xmlConstant = XmlTagConstants.SIGNALEVENT;
		this.sysmlElement = f.createSignalEventInstance();
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
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem xmlElement) {
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNAL_TAG)) {
			String signal = xmlElement.getAttribute(XmlTagConstants.SIGNAL_TAG);
			signalElement = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal)ImportXmlSysml.getOrBuildElement(project, parsedXML, signal);
			xmlElement.addElement(signalElementTag, signalElement);
		}
	}
	
	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(xmlConstant));
		data.appendChild(type);
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent se= (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent)element;
		Signal signal = se.getSignal();
		if(signal != null) {
			String signalId = signal.getLocalID();
			org.w3c.dom.Element signalTag = xmlDoc.createElement(XmlTagConstants.SIGNAL_TAG);
			signalTag.appendChild(xmlDoc.createTextNode(signalId));
			attributes.appendChild(signalTag);
		}
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}

}
