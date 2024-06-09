package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PackageImport extends CommonRelationship {

	public PackageImport(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.PACKAGE_IMPORT;
		this.xmlConstant = XmlTagConstants.PACKAGEIMPORT;
		this.element = f.createPackageImportInstance();
	}
}
