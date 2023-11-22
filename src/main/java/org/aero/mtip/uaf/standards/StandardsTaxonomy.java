package org.aero.mtip.uaf.standards;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StandardsTaxonomy extends AbstractDiagram{
	
	public StandardsTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STANDARDS_TAXONOMY_DIAGRAM;
		this.xmlConstant = XmlTagConstants.STANDARDS_TAXONOMY_DIAGRAM;
		this.cameoDiagramConstant = CameoDiagramConstants.STANDARDS_TAXONOMY;
		this.allowableElements = UAFConstants.STANDARDS_TAXONOMY_TYPES;
	}
}
