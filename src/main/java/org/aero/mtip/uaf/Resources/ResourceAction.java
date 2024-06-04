package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceAction extends CallBehaviorAction {
	
	public ResourceAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_ACTION;
		this.xmlConstant = XmlTagConstants.RESOURCE_ACTION;
	}
}
