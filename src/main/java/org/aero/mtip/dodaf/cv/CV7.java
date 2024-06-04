package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.Matrix.DependencyMatrix;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CV7 extends DependencyMatrix {

	public CV7(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV7;
		this.xmlConstant = XmlTagConstants.CV7;
		this.cameoDiagramConstant = "CV-7 Capability to Services Mapping";
	}
	
	@Override
	public String getSysmlConstant() {
		//Going to need to find what Cameo calls DoDAF Diagrams
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}
}