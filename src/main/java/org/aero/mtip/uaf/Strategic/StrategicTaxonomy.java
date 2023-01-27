package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.AbstractDiagram;

public class StrategicTaxonomy extends AbstractDiagram {

	public StrategicTaxonomy(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public String getSysmlConstant() {
		return "";
	}

	@Override
	public String getDiagramType() {
		return "";
	}

}
