package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class PackageImport extends CommonRelationship {

	public PackageImport(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PACKAGEIMPORT;
		this.xmlConstant = XmlTagConstants.PACKAGEIMPORT;
		this.sysmlElement = f.createPackageImportInstance();
	}

}
