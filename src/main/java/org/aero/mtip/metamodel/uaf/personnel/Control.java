package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.ItemFlow;

public class Control extends ItemFlow {
	
	public Control(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.CONTROL;
		this.xmlConstant = XmlTagConstants.CONTROL;
	}
}
