package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StandardOperationalActivity extends Activity {

	public StandardOperationalActivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STANDARD_OPERATIONAL_ACTIVITY;
		this.xmlConstant = XmlTagConstants.STANDARD_OPERATIONAL_ACTIVITY;
	}
}
