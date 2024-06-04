package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StrategicConstraints extends BlockDefinitionDiagram {

	public StrategicConstraints(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.STRATEGIC_CONSTRAINTS_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STRATEGIC_CONSTRAINTS_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.STRATEGIC_CONSTRAINTS;
	}
}
