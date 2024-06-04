package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Sequence.Message;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceMessage extends Message {

	public ServiceMessage(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_MESSAGE;
		this.xmlConstant = XmlTagConstants.SERVICE_MESSAGE;
	}
}
