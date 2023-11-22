package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualService extends InstanceSpecification {

	public ActualService(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_SERVICE;
		this.xmlConstant = XmlTagConstants.ACTUAL_SERVICE;
	}
}
