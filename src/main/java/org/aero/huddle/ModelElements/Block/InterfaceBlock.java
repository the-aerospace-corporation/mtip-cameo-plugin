package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class InterfaceBlock extends CommonElement {
	public InterfaceBlock(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.INTERFACEBLOCK;
		this.xmlConstant = XmlTagConstants.INTERFACEBLOCK;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), "SysML"); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysMLProfile.INTERFACEBLOCK_STEREOTYPE, creationProfile);
	}
}
