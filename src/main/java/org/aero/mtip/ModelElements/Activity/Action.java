/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.Behavior;

public class Action extends ActivityNode {	
	public Action(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.ACTION;
		this.xmlConstant = XmlTagConstants.ACTION;
		this.element = f.createCallBehaviorActionInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element element = super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction action = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction)element;
		if(xmlElement.hasAttribute(XmlTagConstants.BEHAVIOR)) {
			String import_id = xmlElement.getAttribute(XmlTagConstants.BEHAVIOR);
			String behavior_id = ImportXmlSysml.idConversion(import_id);
			Element behavior = (Element) project.getElementByID(behavior_id);
			if(behavior instanceof Behavior) {
				action.setBehavior((Behavior) behavior);
			}
		}
		return action;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		super.createDependentElements(project, parsedXML, modelElement);
		if(modelElement.hasAttribute(XmlTagConstants.BEHAVIOR)) {
			ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.BEHAVIOR)), modelElement.getAttribute(XmlTagConstants.BEHAVIOR));
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction action = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction)element;
		Behavior behavior = action.getBehavior();
		if(behavior != null) {
			org.w3c.dom.Element behavior_tag = createRel(xmlDoc, behavior, XmlTagConstants.BEHAVIOR);
			relationships.appendChild(behavior_tag);
		}
		
		return data;
	}
}
