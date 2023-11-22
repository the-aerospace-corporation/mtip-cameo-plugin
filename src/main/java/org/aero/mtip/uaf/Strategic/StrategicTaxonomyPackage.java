package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.SysmlPackage;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StrategicTaxonomyPackage extends SysmlPackage{

	public StrategicTaxonomyPackage(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STRATEGIC_TAXONOMY_PACKAGE;
		this.xmlConstant = XmlTagConstants.STRATEGIC_TAXONOMY_PACKAGE;
	}
}
