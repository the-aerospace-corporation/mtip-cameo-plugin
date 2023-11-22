package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OwnsProcess extends Abstraction {

	public OwnsProcess(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.OWNS_PROCESS;
		this.metamodelConstant = UAFConstants.OWNS_PROCESS;
	}
}
