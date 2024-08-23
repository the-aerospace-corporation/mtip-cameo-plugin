package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class StrategicStates extends BlockDefinitionDiagram {

	public StrategicStates(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.STRATEGIC_STATES_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STRATEGIC_STATES_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.STRATEGIC_STATES;
	}
}
