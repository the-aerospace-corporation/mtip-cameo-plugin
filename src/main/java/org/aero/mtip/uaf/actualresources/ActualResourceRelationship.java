package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualResourceRelationship extends ItemFlow {

	public ActualResourceRelationship(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP;
		this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCE_RELATIONSHIP;
	}
}
