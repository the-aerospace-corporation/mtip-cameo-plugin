/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;

public class CallOperationAction extends ActivityNode {

	public CallOperationAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.CALL_OPERATION_ACTION;
		this.xmlConstant = XmlTagConstants.CALL_OPERATION_ACTION;
		this.element = f.createCallOperationActionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		if(xmlElement != null) {
			Operation operation = (Operation) project.getElementByID(xmlElement.getNewOperation());
			((com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction)element).setOperation(operation);
		}
		return element;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		Element operation = Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getOperation()));
		modelElement.setNewOperation(MtipUtils.getId(operation));
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
				
		writeOperation(relationships, element);
		
		return data;
	}
	
	public void writeOperation(org.w3c.dom.Element relationships, Element element) {
		Operation operation = ((com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction)element).getOperation();
		
		if(operation == null) {
			return;
		}
		
		org.w3c.dom.Element operationTag = XmlWriter.createMtipRelationship(operation, XmlTagConstants.OPERATION_TAG);
		XmlWriter.add(relationships, operationTag);
	}
}
