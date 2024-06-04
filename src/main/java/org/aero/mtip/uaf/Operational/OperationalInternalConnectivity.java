package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalInternalConnectivity extends AbstractDiagram{
	
	public OperationalInternalConnectivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_INTERNAL_CONNECTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_INTERNAL_CONNECTIVITY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_INTERNAL_CONNECTIVITY;
	}
}

