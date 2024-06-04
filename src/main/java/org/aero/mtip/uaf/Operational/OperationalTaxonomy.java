package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalTaxonomy extends AbstractDiagram{
	
	public OperationalTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_TAXONOMY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_TAXONOMY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_TAXONOMY;
	}
}
