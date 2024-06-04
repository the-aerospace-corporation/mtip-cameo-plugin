package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualPost extends InstanceSpecification {

	public ActualPost(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_POST;
		this.xmlConstant = XmlTagConstants.ACTUAL_POST;
	}
}
