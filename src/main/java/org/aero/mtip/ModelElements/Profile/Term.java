/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class Term extends CommonElement {

	public Term(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.TERM;
		this.xmlConstant = XmlTagConstants.TERM;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.MAGICDRAW_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysmlConstants.TERM, creationProfile);
	}
}
