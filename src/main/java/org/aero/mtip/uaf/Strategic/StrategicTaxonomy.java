package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StrategicTaxonomy extends BlockDefinitionDiagram {

	public StrategicTaxonomy(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = UAFConstants.STRATEGIC_TAXONOMY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STRATEGIC_TAXONOMY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.STRATEGIC_TAXONOMY;
		 this.allowableElements = UAFConstants.ST_TX_TYPES;
	}
}
