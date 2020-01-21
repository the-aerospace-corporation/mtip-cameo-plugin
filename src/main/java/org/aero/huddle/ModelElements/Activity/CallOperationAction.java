package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.impl.ElementsFactory;

public class CallOperationAction extends CommonElement {

	public CallOperationAction(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Call Operation Action Element");
		}
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction coa = f.createCallOperationActionInstance();
		((NamedElement)coa).setName(name);
		if(owner != null) {
			coa.setOwner(owner);
		} else {
			coa.setOwner(project.getPrimaryModel());
		}
		
		if(xmlElement != null) {
			Operation operation = (Operation) project.getElementByID(xmlElement.getNewOperation());
			coa.setOperation(operation);
		}
				
		SessionManager.getInstance().closeSession(project);
		return coa;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.CALLOPERATIONACTION));
		data.appendChild(type);
		
		Operation operation = ((com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction)element).getOperation();
		org.w3c.dom.Element operationTag = xmlDoc.createElement("operation");
		operationTag.appendChild(xmlDoc.createTextNode(operation.getLocalID()));
		attributes.appendChild(operationTag);

		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
