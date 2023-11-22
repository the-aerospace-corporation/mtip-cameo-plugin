package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Achiever extends InstanceSpecification {

	public Achiever(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACHIEVER;
		this.xmlConstant = XmlTagConstants.ACHIEVER;
	}
}
