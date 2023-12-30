/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class OpaqueBehavior extends CommonElement {

	public OpaqueBehavior(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OPAQUE_BEHAVIOR;
		this.xmlConstant = XmlTagConstants.OPAQUE_BEHAVIOR;
		this.element = f.createOpaqueBehaviorInstance();
	}
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior ob = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior)super.createElement(project, owner, xmlElement);
		
		if(xmlElement.hasAttribute(XmlTagConstants.BODY)) {
			String body = xmlElement.getAttribute(XmlTagConstants.BODY);
			ob.getBody().add(body);
		} 
		
		if(xmlElement.hasAttribute(XmlTagConstants.LANGUAGE)) {
			String language = xmlElement.getAttribute(XmlTagConstants.LANGUAGE);
			ob.getLanguage().add(language);
		}
		
		return ob;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());

		writeBody(attributes, element);
		writeLanguage(attributes, element);
		


		return data;
	}
	
	protected void writeBody(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior ob = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior)element;
		
		if(!ob.hasBody()) {
			return;
		}
		
		List<String> bodies = ob.getBody();
		
		if (bodies.size() == 0) {
			return;
		}
		
		String body = bodies.get(0);
		
		if (body.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element bodyTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.BODY, body);
		XmlWriter.add(attributes, bodyTag);
	}
	
	protected void writeLanguage(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior ob = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior)element;
		
		if(!ob.hasLanguage()) {
			return;
		}
		
		List<String> languages = ob.getLanguage();
		
		if (languages.size() == 0) {
			return;
		}
		
		String language = languages.get(0);
		
		if (language.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element languageTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.LANGUAGE, language);
		XmlWriter.add(attributes, languageTag);
	}
}
