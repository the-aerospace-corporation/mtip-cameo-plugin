package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalFreeFormTaxonomy extends AbstractDiagram{
	
	public OperationalFreeFormTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_FREE_FORM_TAXONOMY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_FREE_FORM_TAXONOMY;
		this.cameoDiagramConstant = "Operational Free Form Taxonomy";
		this.allowableElements = UAFConstants.OPERATIONAL_FREE_FORM_TAXONOMY_TYPES;
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
