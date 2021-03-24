package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Comment extends CommonElement {

	public Comment(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.COMMENT;
		this.xmlConstant = XmlTagConstants.COMMENT;
		this.sysmlElement = f.createCommentInstance();
	}
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_BODY)) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)sysmlElement;
			comment.setBody(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_BODY));
		}
		
		return sysmlElement;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)sysmlElement;
		String body = comment.getBody();
		if(!body.isEmpty()) {
			org.w3c.dom.Element bodyTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_BODY, body);
			attributes.appendChild(bodyTag);
		}
		return data;
	}
	
	
	@Override
	public void setOwner(Project project, Element owner) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)sysmlElement;
		comment.setOwningElement(owner);
	}
}
