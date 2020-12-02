package org.aero.huddle.ModelElements.StateMachine;

import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
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
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(xmlConstant));
		data.appendChild(type);
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior ob = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior)element;
		if(ob.hasBody()) {
			List<String> bodies = ob.getBody();
			String body = bodies.get(0);
			org.w3c.dom.Element bodyTag = xmlDoc.createElement(XmlTagConstants.BODY);
			bodyTag.appendChild(xmlDoc.createTextNode(body));
			attributes.appendChild(bodyTag);
		}
		
		if(ob.hasLanguage()) {
			List<String> languages = ob.getLanguage();
			String language = languages.get(0);
			org.w3c.dom.Element languageTag = xmlDoc.createElement(XmlTagConstants.LANGUAGE);
			languageTag.appendChild(xmlDoc.createTextNode(language));
			attributes.appendChild(languageTag);
		}
		
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}

}
