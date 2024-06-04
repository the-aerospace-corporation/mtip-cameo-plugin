package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Control extends ItemFlow {
	
	public Control(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.CONTROL;
		this.xmlConstant = XmlTagConstants.CONTROL;
	}
}
