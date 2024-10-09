package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OperationalConnectivity extends AbstractDiagram {
	public OperationalConnectivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONNECTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONNECTIVITY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_CONNECTIVITY;
	}
}