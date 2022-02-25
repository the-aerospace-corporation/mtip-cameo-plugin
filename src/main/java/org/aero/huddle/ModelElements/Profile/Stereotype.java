package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Stereotype extends CommonElement {	
	
	com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile sysmlProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(),  SysmlConstants.SYSML_PROFILE_NAME);
	
	public Stereotype(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STEREOTYPE;
		this.xmlConstant = XmlTagConstants.STEREOTYPE;
		this.sysmlElement = f.createStereotypeInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		//Check that stereotype is not from SysML profile
		com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype stereotype = StereotypesHelper.getStereotype(project, name, sysmlProfile);
		if(stereotype != null) {
			return stereotype;
		}
		super.createElement(project, owner, xmlElement);
		return sysmlElement;
	}
	
	
}