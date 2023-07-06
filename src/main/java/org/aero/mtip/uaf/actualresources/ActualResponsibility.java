package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ActualResponsibility extends InstanceSpecification {

	public ActualResponsibility(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_RESPONSIBILITY;
		this.xmlConstant = XmlTagConstants.ACTUAL_RESPONSIBILITY;
		this.sysmlElement = f.createInstanceSpecificationInstance();
	}
}
