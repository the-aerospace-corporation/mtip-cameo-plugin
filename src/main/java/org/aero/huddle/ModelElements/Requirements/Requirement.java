package org.aero.huddle.ModelElements.Requirements;

import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Requirement extends CommonElement {

	public Requirement(String name, String EAID) {
		super(name, EAID);		
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.REQUIREMENT;
		this.xmlConstant = XmlTagConstants.REQUIREMENT;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.SYSML_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysMLProfile.REQUIREMENT_STEREOTYPE, creationProfile);
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		// Get requirement specific data/attributes
		
		//Add Requirement text to attributes in XML
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML");
		Stereotype requirementStereotype = StereotypesHelper.getStereotype(project,  "Requirement", sysmlProfile);
		List<String> textList = StereotypesHelper.getStereotypePropertyValueAsString(element, requirementStereotype, "Text");
		String text = "";
				
		if(textList.size() > 0) {
			text = textList.get(0).replaceAll("\\<.*?>","");
		}
		if(!text.isEmpty()) {
			org.w3c.dom.Element textTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_TEXT, text);
			attributes.appendChild(textTag);
		}	
		
		//Add reqID to attributes in XML
		String id = (String) StereotypesHelper.getStereotypePropertyFirst(element, requirementStereotype, "Id");
		org.w3c.dom.Element idTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_ID, id);
		attributes.appendChild(idTag);

		return data;
	}
}
