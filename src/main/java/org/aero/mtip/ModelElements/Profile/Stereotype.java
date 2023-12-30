/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

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
		this.element = f.createStereotypeInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		//Check that stereotype is not from SysML profile
		com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype stereotype = StereotypesHelper.getStereotype(project, name, sysmlProfile);
		if(stereotype != null) {
			return stereotype;
		}
		super.createElement(project, owner, xmlElement);
		return element;
	}
	
	
}