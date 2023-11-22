package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Command extends Abstraction {

	public Command(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.COMMAND;
		this.xmlConstant = XmlTagConstants.COMMAND;
	}
}
