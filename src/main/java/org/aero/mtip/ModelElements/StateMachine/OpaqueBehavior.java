/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class OpaqueBehavior extends CommonElement {

	public OpaqueBehavior(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OPAQUEBEHAVIOR;
		this.xmlConstant = XmlTagConstants.OPAQUEBEHAVIOR;
		this.sysmlElement = f.createOpaqueBehaviorInstance();
	}
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior ob = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior)super.createElement(project, owner, xmlElement);
		if(xmlElement.hasAttribute(XmlTagConstants.BODY)) {
			String body = xmlElement.getAttribute(XmlTagConstants.BODY);
			ob.getBody().add(body);
		} else {
			CameoUtils.logGUI("Opaque Behavior with id: " + EAID + " has no body attribute.");
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.LANGUAGE)) {
			String language = xmlElement.getAttribute(XmlTagConstants.LANGUAGE);
			ob.getLanguage().add(language);
		} else {
			CameoUtils.logGUI("Opaque Behavior with id: " + EAID + " has no language attribute.");
		}
		return ob;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());

		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior ob = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior)element;
		if(ob.hasBody()) {
			List<String> bodies = ob.getBody();
			String body = bodies.get(0);
			org.w3c.dom.Element bodyTag = createStringAttribute(xmlDoc, XmlTagConstants.BODY, body);
			attributes.appendChild(bodyTag);
		}
		
		if(ob.hasLanguage()) {
			List<String> languages = ob.getLanguage();
			String language = languages.get(0);
			org.w3c.dom.Element languageTag = createStringAttribute(xmlDoc,XmlTagConstants.LANGUAGE, language);
			attributes.appendChild(languageTag);
		}

		return data;
	}

}
