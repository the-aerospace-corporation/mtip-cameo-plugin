package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ProjectTheme extends Property {
	public ProjectTheme(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_THEME;
		this.xmlConstant = XmlTagConstants.PROJECT_THEME;
	}
}
