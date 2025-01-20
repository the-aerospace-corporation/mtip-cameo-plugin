package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.Activity;

public class StandardOperationalActivity extends Activity {

	public StandardOperationalActivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STANDARD_OPERATIONAL_ACTIVITY;
		this.xmlConstant = XmlTagConstants.STANDARD_OPERATIONAL_ACTIVITY;
	}
}
