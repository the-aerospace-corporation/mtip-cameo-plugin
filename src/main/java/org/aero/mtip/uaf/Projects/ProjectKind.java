package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectKind extends Enumeration{
	
	public ProjectKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.PROJECT_KIND;
		this.xmlConstant = XmlTagConstants.PROJECT_KIND;
	}
}