package org.aero.mtip.metamodel.dodaf.cv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.matrix.DependencyMatrix;

public class CV6 extends DependencyMatrix {

	public CV6(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV6;
		this.xmlConstant = XmlTagConstants.CV6;
		this.cameoDiagramConstant = "CV-6 Capability to Operational Activities Mapping";
	}
	
	@Override
	public String getCameoDiagramConstant() {
		//Going to need to find what Cameo calls DoDAF Diagrams
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}
}

