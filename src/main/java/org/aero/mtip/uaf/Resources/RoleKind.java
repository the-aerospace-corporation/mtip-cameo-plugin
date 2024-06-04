package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class RoleKind extends Enumeration{
	public RoleKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.ROLE_KIND;
		this.xmlConstant = XmlTagConstants.ROLE_KIND;
	}
}