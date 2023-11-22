package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalHighLevelTaxonomy extends AbstractDiagram{
	
	public OperationalHighLevelTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY;
		this.allowableElements = UAFConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY_TYPES;
	}
}
