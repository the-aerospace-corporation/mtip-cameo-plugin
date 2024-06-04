package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Function extends Activity {
	public Function(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FUNCTION;
		this.xmlConstant = XmlTagConstants.FUNCTION;
	}
}
