/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Comment extends CommonElement {

	public Comment(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.COMMENT;
		this.xmlConstant = XmlTagConstants.COMMENT;
		this.element = f.createCommentInstance();
	}
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_BODY)) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)element;
			comment.setBody(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_BODY));
		}
		
		return element;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeBody(attributes, element);
		
		return data;
	}
	
	public void writeBody(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)element;
		
		String body = comment.getBody();
		
		if(body == null || body.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element bodyTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_BODY, body);
		XmlWriter.add(attributes, bodyTag);
	}
	
	
	@Override
	public void setOwner(Element owner) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)element;
		comment.setOwningElement(owner);
	}
}
