/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Profile;

import java.util.Iterator;
import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class OpaqueExpression extends CommonElement {
	
	public final static String CHECK_CLASSES_START = "self.oclIsKindOf(";
	private final String BODY = "body";
	private final String LANGUAGE = "language";
	
	public OpaqueExpression(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OPAQUEEXPRESSION;
		this.xmlConstant = XmlTagConstants.OPAQUEEXPRESSION;
		this.sysmlElement = f.createOpaqueExpressionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression)sysmlElement;
		String body = xmlElement.getAttribute(BODY);
		String language = xmlElement.getAttribute(LANGUAGE);
		
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
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Opaque Expression Element");
		}
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = f.createOpaqueExpressionInstance();
		((NamedElement)oe).setName(name);
		
		setOwner(project, owner);
		
		oe.getBody().add(body);
		oe.getLanguage().clear();
		oe.getLanguage().add(language);
		
		SessionManager.getInstance().closeSession(project);
		return oe;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		Element owner = element.getOwner();
		// OpaqueExpressions of Control Flows and Object flows will be captured as attributes due to EA's limitations
		// Abstract to list of elements that are treatd similarly in SysmlConstants.
		if(owner instanceof ControlFlow || owner instanceof ObjectFlow) {
			return null;
		}
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression oe = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression)element;
		List<String> bodies = oe.getBody();
		List<String> languages = oe.getLanguage();
		String body = null;
		String language = null;
		
		Iterator<String> bodyIter = bodies.iterator();
		Iterator<String> langIter = languages.iterator();
		
		if(bodyIter.hasNext()) {
			body = bodyIter.next();
		}
		
		if(langIter.hasNext()) {
			language = langIter.next();
		}
		
		if(body != null && !body.isEmpty()) {
			org.w3c.dom.Element bodyTag = createStringAttribute(xmlDoc, BODY, body);
			attributes.appendChild(bodyTag);
		}
		
		if(language != null && !language.isEmpty()) {
			org.w3c.dom.Element langTag = createStringAttribute(xmlDoc, LANGUAGE, language);
			attributes.appendChild(langTag);
		}
		
		return data;
	}
	
}
