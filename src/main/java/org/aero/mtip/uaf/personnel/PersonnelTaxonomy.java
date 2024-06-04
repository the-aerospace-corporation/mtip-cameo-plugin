package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelTaxonomy extends BlockDefinitionDiagram {

	public PersonnelTaxonomy(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_TAXONOMY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_TAXONOMY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_TAXONOMY;
	}
}
