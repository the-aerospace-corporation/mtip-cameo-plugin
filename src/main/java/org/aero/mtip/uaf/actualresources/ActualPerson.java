package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualPerson extends InstanceSpecification {
	
	public ActualPerson(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PERSON;
		this.xmlConstant = XmlTagConstants.ACTUAL_PERSON;
	}
}
