/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;

public class CallOperationAction extends ActivityNode {

	public CallOperationAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CALLOPERATIONACTION;
		this.xmlConstant = XmlTagConstants.CALLOPERATIONACTION;
		this.sysmlElement = f.createCallOperationActionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		if(xmlElement != null) {
			Operation operation = (Operation) project.getElementByID(xmlElement.getNewOperation());
			((com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction)sysmlElement).setOperation(operation);
		}
		return sysmlElement;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		Element operation = ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getOperation()), modelElement.getOperation());
		modelElement.setNewOperation(operation.getID());
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
				
		Operation operation = ((com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction)element).getOperation();
		if(operation != null) {
			org.w3c.dom.Element operationTag = createRel(xmlDoc, operation, XmlTagConstants.OPERATION_TAG);
			relationships.appendChild(operationTag);
		}
		return data;
	}
}
