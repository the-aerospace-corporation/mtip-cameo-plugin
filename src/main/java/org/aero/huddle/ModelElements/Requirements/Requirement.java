package org.aero.huddle.ModelElements.Requirements;

import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Requirement extends CommonElement {

	public Requirement(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Requirement Element");
		}
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML");
		Stereotype requirementStereotype = StereotypesHelper.getStereotype(project,  "Requirement", sysmlProfile);
		Element requirement = createClassWithStereotype(project, name, requirementStereotype, owner);
		
		if (owner != null) {
			requirement.setOwner(owner);
		} else {
			requirement.setOwner(project.getPrimaryModel());
		}
		
		
		//Check if text exists, set with stereotypeshelper
		
		SessionManager.getInstance().closeSession(project);
		return requirement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.REQUIREMENT));
		data.appendChild(type);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		// Get requirement specific data/attributes
		
		//Add Requirement text to attributes in XML
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML");
		Stereotype requirementStereotype = StereotypesHelper.getStereotype(project,  "Requirement", sysmlProfile);
		List<String> textList = StereotypesHelper.getStereotypePropertyValueAsString(element, requirementStereotype, "Text");
		String text = "";
				
		if(textList.size() > 0) {
			text = textList.get(0);
		}
		
		// Add text to attributes node in XML
		org.w3c.dom.Element textElement = xmlDoc.createElement("text");
		textElement.appendChild(xmlDoc.createTextNode(text));
		attributes.appendChild(textElement);
		
		//Add reqID to attributes in XML
		
		
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}

}
