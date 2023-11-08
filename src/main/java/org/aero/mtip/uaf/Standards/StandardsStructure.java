package org.aero.mtip.uaf.Standards;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StandardsStructure extends AbstractDiagram{
	
	public StandardsStructure(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.STANDARDS_STRUCTURE_DIAGRAM;
		this.xmlConstant = XmlTagConstants.STANDARDS_STRUCTURE_DIAGRAM;
		this.cameoDiagramConstant = "Standards Structure";
		this.allowableElements = UAFConstants.STANDARDS_STRUCTURE_TYPES;
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
