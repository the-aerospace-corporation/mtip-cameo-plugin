package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class Command extends Abstraction {

	public Command(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.COMMAND;
		this.xmlConstant = XmlTagConstants.COMMAND;
	}
}
