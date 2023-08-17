package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelStructure extends BlockDefinitionDiagram {

	public PersonnelStructure(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.PERSONNEL_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_STRUCTURE_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_STRUCTURE;
		 this.allowableElements = UAFConstants.PR_SR_TYPES;
	}
}
