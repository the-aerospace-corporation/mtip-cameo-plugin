package org.aero.huddle.ModelElements.Activity;

import java.util.Collection;
import java.util.Iterator;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;
import com.nomagic.uml2.impl.ElementsFactory;

public class AcceptEventAction extends CommonElement {

	public AcceptEventAction(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Accept Event Action Element");
		}
		Element sysmlElement = f.createAcceptEventActionInstance();
		((NamedElement)sysmlElement).setName(name);
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
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
