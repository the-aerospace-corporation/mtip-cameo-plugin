package org.aero.mtip.metamodel.uaf;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class IsCapableToPerform extends Abstraction {

	public IsCapableToPerform(String name, String EAID) {
		super(name, EAID);
		metamodelConstant = UAFConstants.IS_CAPABLE_TO_PERFORM;
		xmlConstant = XmlTagConstants.IS_CAPABLE_TO_PERFORM;
	}
	
}
