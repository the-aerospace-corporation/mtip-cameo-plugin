package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.SysmlPackage;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class StrategicTaxonomyPackage extends SysmlPackage{

	public StrategicTaxonomyPackage(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.STRATEGIC_TAXONOMY_PACKAGE;
		this.xmlConstant = XmlTagConstants.STRATEGIC_TAXONOMY_PACKAGE;
		this.sysmlElement = f.createPackageInstance();
		this.creationProfile = UAFProfile.UAF_PROFILE;
		this.creationStereotype = UAFProfile.STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE;
	}
}
