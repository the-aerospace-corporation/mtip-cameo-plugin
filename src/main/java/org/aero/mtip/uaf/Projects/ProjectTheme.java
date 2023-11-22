package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectTheme extends Property {
	public ProjectTheme(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_THEME;
		this.xmlConstant = XmlTagConstants.PROJECT_THEME;
	}
}
