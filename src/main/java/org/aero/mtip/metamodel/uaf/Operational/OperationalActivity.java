package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.Activity;

public class OperationalActivity extends Activity {

	public OperationalActivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_ACTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ACTIVITY;
	}
}
