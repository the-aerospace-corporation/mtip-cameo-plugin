/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import java.util.Iterator;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class OpaqueExpression extends CommonElement {
	public final static String CHECK_CLASSES_START = "self.oclIsKindOf(";
	
	public OpaqueExpression(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OPAQUE_EXPRESSION;
		this.xmlConstant = XmlTagConstants.OPAQUE_EXPRESSION;
		this.element = f.createOpaqueExpressionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression)element;
		String body = xmlElement.getAttribute(XmlTagConstants.BODY);
		String language = xmlElement.getAttribute(XmlTagConstants.LANGUAGE);
		
		oe.getBody().add(body);
		oe.getLanguage().clear();
		oe.getLanguage().add(language);

		return oe;
	}
	/**
	 * 
	 * @param project Project into which you are importing data.
	 * @param owner Parent element of the opaque expression that is created by this method.
	 * @param body String body for the expression.
	 * @param language Language of the body to be evaluated.
	 * @return Opaque expression element that is created.
	 */
	public Element createElement(Project project, Element owner, String body, String language) {
		ElementsFactory f = project.getElementsFactory();
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = f.createOpaqueExpressionInstance();
		((NamedElement)oe).setName(name);
		
		setOwner(owner);
		
		oe.getBody().add(body);
		oe.getLanguage().clear();
		oe.getLanguage().add(language);
		
		return oe;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		Element owner = element.getOwner();
		// OpaqueExpressions of Control Flows and Object flows will be captured as attributes due to EA's limitations
		// Abstract to list of elements that are treatd similarly in SysmlConstants.
		if(owner instanceof ControlFlow || owner instanceof ObjectFlow) {
			return null;
		}
		
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeBody(attributes, element);
		writeLanguage(attributes, element);

		return data;
	}
	
	private void writeBody(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression)element;
		
		List<String> bodies = oe.getBody();
		Iterator<String> bodyIter = bodies.iterator();
		
		if(!bodyIter.hasNext()) {
			return;
		}
		
		String body = bodyIter.next();
		
		if(body == null || body.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element bodyTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.BODY, body);
		XmlWriter.add(attributes, bodyTag);
	}
	
	public void writeLanguage(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression)element;
		
		List<String> languages = oe.getLanguage();
		Iterator<String> langIter = languages.iterator();
		
		if(!langIter.hasNext()) {
			return;
		}
		
		String language = langIter.next();
		
		if(language == null || language.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element langTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.LANGUAGE, language);
		XmlWriter.add(attributes, langTag);
	}
}
