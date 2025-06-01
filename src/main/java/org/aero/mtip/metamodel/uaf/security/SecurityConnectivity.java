package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.InternalBlockDiagram;

public class SecurityConnectivity extends InternalBlockDiagram {

	public SecurityConnectivity(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = UAFConstants.SECURITY_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_CONNECTIVITY;
	}

}
