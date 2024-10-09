package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OperationalTaxonomy extends AbstractDiagram {
	
	public OperationalTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_TAXONOMY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_TAXONOMY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_TAXONOMY;
	}
}
