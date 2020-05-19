package org.aero.huddle.ModelElements.Activity;

import java.util.Collection;
import java.util.Iterator;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;

public class AcceptEventAction extends CommonElement {

	public AcceptEventAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACCEPTEVENTACTION;
		this.xmlConstant = XmlTagConstants.ACCEPTEVENTACTION;
		this.sysmlElement = f.createAcceptEventActionInstance();
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.ACCEPTEVENTACTION));
		data.appendChild(type);
		
		Collection<Trigger> triggers = ((com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction)element).getTrigger();
		Iterator<Trigger> triggerIter = triggers.iterator();
		Trigger trigger = null;
		
		if(triggerIter.hasNext()) {
			trigger = triggerIter.next();
			
			org.w3c.dom.Element eventElement = xmlDoc.createElement("trigger");
			eventElement.appendChild(xmlDoc.createTextNode(trigger.getLocalID()));
			attributes.appendChild(eventElement);
		}
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
