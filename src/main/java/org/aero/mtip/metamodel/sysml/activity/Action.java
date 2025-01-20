/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.activity;

import java.util.HashMap;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.Behavior;

public class Action extends ActivityNode {	
	public Action(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
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
			String behavior_id = Importer.idConversion(import_id);
			Element behavior = (Element) project.getElementByID(behavior_id);
			if(behavior instanceof Behavior) {
				action.setBehavior((Behavior) behavior);
			}
		}
		return action;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		super.createDependentElements(parsedXML, modelElement);
		if(modelElement.hasAttribute(XmlTagConstants.BEHAVIOR)) {
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.BEHAVIOR)));
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeBehavior(relationships, element);
		
		return data;
	}
	
	public void writeBehavior(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction action = (com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction)element;
		Behavior behavior = action.getBehavior();
		
		if(behavior == null) {
			return;
		}
		
		org.w3c.dom.Element behaviorTag = XmlWriter.createMtipRelationship(behavior, XmlTagConstants.BEHAVIOR);
		XmlWriter.add(relationships, behaviorTag);
	}
}
