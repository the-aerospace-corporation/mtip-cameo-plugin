package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.InternalBlock.InternalBlockDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityConnectivity extends InternalBlockDiagram {

	public SecurityConnectivity(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = UAFConstants.SECURITY_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_CONNECTIVITY;
		 this.allowableElements = UAFConstants.SC_CN_TYPES;
	}

}
