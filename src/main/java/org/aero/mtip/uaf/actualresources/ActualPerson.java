package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ActualPerson extends InstanceSpecification {
	
	public ActualPerson(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_PERSON;
		this.xmlConstant = XmlTagConstants.ACTUAL_PERSON;
		this.sysmlElement = f.createInstanceSpecificationInstance();
	}
}
