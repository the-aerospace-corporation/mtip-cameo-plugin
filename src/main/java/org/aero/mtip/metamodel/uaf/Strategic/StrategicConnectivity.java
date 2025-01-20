package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class StrategicConnectivity extends BlockDefinitionDiagram {

	public StrategicConnectivity(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.STRATEGIC_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STRATEGIC_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.STRATEGIC_CONNECTIVITY;
	}
}
