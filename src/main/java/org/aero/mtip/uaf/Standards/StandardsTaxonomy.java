package org.aero.mtip.uaf.Standards;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class StandardsTaxonomy extends AbstractDiagram{
	
	public StandardsTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.STANDARDS_TAXONOMY_DIAGRAM;
		this.xmlConstant = XmlTagConstants.STANDARDS_TAXONOMY_DIAGRAM;
		this.cameoDiagramConstant = "Standards Taxonomy";
		this.allowableElements = UAFConstants.STANDARDS_TAXONOMY_TYPES;
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
