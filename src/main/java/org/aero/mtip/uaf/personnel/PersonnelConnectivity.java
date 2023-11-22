package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelConnectivity extends BlockDefinitionDiagram {

	public PersonnelConnectivity(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_CONNECTIVITY;
		 this.allowableElements = UAFConstants.PR_CN_TYPES;
	}
}
