package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualPerson extends InstanceSpecification {
	
	public ActualPerson(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PERSON;
		this.xmlConstant = XmlTagConstants.ACTUAL_PERSON;
	}
}
