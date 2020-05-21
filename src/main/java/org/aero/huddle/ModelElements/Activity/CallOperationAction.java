package org.aero.huddle.ModelElements.Activity;

import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;

public class CallOperationAction extends CommonElement {

	public CallOperationAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CALLOPERATIONACTION;
		this.xmlConstant = XmlTagConstants.CALLOPERATIONACTION;
		this.sysmlElement = f.createCallOperationActionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		((NamedElement)sysmlElement).setName(name);
		setOwner(owner);
		
		if(xmlElement != null) {
			Operation operation = (Operation) project.getElementByID(xmlElement.getNewOperation());
			((com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction)sysmlElement).setOperation(operation);
		}
		return sysmlElement;
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		Element operation = ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getOperation()), modelElement.getOperation());
		modelElement.setNewOperation(operation.getLocalID());
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
