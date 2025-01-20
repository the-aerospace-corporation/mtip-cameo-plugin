package org.aero.mtip.metamodel.uaf.Metadata;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Enumeration;

public class DataModelKind extends Enumeration {
	public DataModelKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.DATA_MODEL_KIND;
		this.xmlConstant = XmlTagConstants.DATA_MODEL_KIND;
	}
}
