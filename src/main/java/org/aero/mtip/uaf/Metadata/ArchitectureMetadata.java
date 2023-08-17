package org.aero.mtip.uaf.Metadata;

import java.util.List;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ArchitectureMetadata extends Comment {

	public ArchitectureMetadata(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.INFORMATION;
		this.xmlConstant = XmlTagConstants.INFORMATION;
		this.sysmlElement = f.createCommentInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment) super.createElement(project, owner, xmlElement);
		if(xmlElement.hasAttribute(XmlTagConstants.BODY)) {
			String body = xmlElement.getAttribute(XmlTagConstants.BODY);
			comment.setBody(body);
		}
		
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.INFORMATION_STEREOTYPE);
		
		return sysmlElement;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());

		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment)element;
		String body = comment.getBody();
		
		if (body != null && !body.isEmpty()) 
		{
			org.w3c.dom.Element bodyTag = createStringAttribute(xmlDoc, XmlTagConstants.BODY, body);
			attributes.appendChild(bodyTag);
		}
		
		return data;
	}

}