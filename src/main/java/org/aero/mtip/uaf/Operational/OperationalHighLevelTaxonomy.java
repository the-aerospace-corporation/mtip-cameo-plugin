package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalHighLevelTaxonomy extends AbstractDiagram{
	
	public OperationalHighLevelTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY;
		this.cameoDiagramConstant = "Operational High Level Taxonomy";
		this.allowableElements = UAFConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}
