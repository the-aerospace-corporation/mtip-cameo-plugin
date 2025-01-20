package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class OwnsProcess extends Abstraction {

	public OwnsProcess(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.OWNS_PROCESS;
		this.metamodelConstant = UAFConstants.OWNS_PROCESS;
	}
}