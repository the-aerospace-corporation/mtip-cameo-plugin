package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class WholeLifeConfigurationKind extends Enumeration{
	public WholeLifeConfigurationKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.WHOLE_LIFE_CONFIGURATION_KIND;
		this.xmlConstant = XmlTagConstants.WHOLE_LIFE_CONFIGURATION_KIND;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.WHOLE_LIFE_CONFIGURATION_KIND, creationProfile);
	}
}