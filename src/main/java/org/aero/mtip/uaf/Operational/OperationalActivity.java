package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalActivity extends Activity {

	public OperationalActivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_ACTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ACTIVITY;
	}
}
