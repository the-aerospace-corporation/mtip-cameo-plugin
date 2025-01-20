package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class PersonnelStructure extends BlockDefinitionDiagram {

	public PersonnelStructure(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_STRUCTURE_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_STRUCTURE;
	}
}
