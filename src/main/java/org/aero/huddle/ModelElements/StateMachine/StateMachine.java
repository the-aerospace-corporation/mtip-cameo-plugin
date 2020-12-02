package org.aero.huddle.ModelElements.StateMachine;

import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class StateMachine extends CommonElement {
	public StateMachine(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine sysmlElement = f.createStateMachineInstance();
		((NamedElement)sysmlElement).setName(name);
		
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		// Remove auto-created region as they are defined explicitly in the XML
		sysmlElement.getRegion().clear();
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
	
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.isSubmachine() && !modelElement.newSubmachineCreated()) {
			String submachineID = modelElement.getSubmachine();
			XMLItem submachine = parsedXML.get(submachineID);
			Element submachineElement = ImportXmlSysml.buildElement(project, parsedXML, submachine, submachineID);
			modelElement.setNewSubmachineID(submachineElement.getLocalID());
		}
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.STATEMACHINE));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}
}
