package org.aero.mtip.metamodel.uaf.standards;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class StandardsStructure extends AbstractDiagram{
	
	public StandardsStructure(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STANDARDS_STRUCTURE_DIAGRAM;
		this.xmlConstant = XmlTagConstants.STANDARDS_STRUCTURE_DIAGRAM;
		this.cameoDiagramConstant = CameoDiagramConstants.STANDARDS_STRUCTURE;
	}
}
