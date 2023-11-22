package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalConnectivity extends AbstractDiagram {
	public OperationalConnectivity(String name, String EAID)
	{
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONNECTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONNECTIVITY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_CONNECTIVITY;
		this.allowableElements = UAFConstants.OPERATIONAL_CONNECTIVITY_TYPES;
	}
}