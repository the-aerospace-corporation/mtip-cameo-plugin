/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class InterfaceBlock extends CommonElement {
	public InterfaceBlock(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.INTERFACE_BLOCK;
		this.xmlConstant = XmlTagConstants.INTERFACE_BLOCK;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), "SysML"); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysMLProfile.INTERFACEBLOCK_STEREOTYPE, creationProfile);
	}
}
