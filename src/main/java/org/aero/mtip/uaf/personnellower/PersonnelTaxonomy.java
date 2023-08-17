package org.aero.mtip.uaf.personnellower;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelTaxonomy extends BlockDefinitionDiagram {

	public PersonnelTaxonomy(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.PERSONNEL_TAXONOMY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_TAXONOMY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_TAXONOMY;
		 this.allowableElements = UAFConstants.PR_TX_TYPES;
	}
}
