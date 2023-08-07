package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ActualResourceRelationship extends ItemFlow {

	public ActualResourceRelationship(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP;
		this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCE_RELATIONSHIP;
		this.sysmlElement = f.createInformationFlowInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.ACTUAL_RESOURCE_RELATIONSHIP_STEREOTYPE);
	}
}
