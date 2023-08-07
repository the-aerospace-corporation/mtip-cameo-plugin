package org.aero.mtip.uaf.personnel;

import java.util.Arrays;

import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Command extends ItemFlow {

	public Command(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.COMMAND;
		this.xmlConstant = XmlTagConstants.COMMAND;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.COMMAND_STEREOTYPE);
	}
}
