package org.aero.mtip.uaf.personnellower;

import java.util.Arrays;

import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Control extends ItemFlow {
	
	public Control(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.CONTROL;
		this.xmlConstant = XmlTagConstants.CONTROL;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.CONTROL_STEREOTYPE);
	}
}
