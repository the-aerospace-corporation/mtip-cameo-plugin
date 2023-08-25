package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StrategicStates extends BlockDefinitionDiagram {

	public StrategicStates(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.STRATEGIC_STATES_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STRATEGIC_STATES_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.STRATEGIC_STATES;
		 this.allowableElements = UAFConstants.ST_ST_TYPES;
	}
}
