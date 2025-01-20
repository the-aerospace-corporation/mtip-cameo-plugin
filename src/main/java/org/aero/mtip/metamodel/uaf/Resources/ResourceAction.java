package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.CallBehaviorAction;

public class ResourceAction extends CallBehaviorAction {
	
	public ResourceAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_ACTION;
		this.xmlConstant = XmlTagConstants.RESOURCE_ACTION;
	}
}
