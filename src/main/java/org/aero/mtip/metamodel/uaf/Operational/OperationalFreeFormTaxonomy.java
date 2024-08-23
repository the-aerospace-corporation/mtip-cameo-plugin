package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OperationalFreeFormTaxonomy extends AbstractDiagram{
	
	public OperationalFreeFormTaxonomy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_FREE_FORM_TAXONOMY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_FREE_FORM_TAXONOMY;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_FREE_FORM_TAXONOMY;
	}
}
