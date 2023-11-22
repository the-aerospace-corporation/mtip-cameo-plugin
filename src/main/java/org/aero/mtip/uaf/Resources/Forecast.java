package org.aero.mtip.uaf.Resources;


import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Forecast extends Dependency {

	public Forecast(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FORECAST;
		this.xmlConstant = XmlTagConstants.FORECAST;
	}
}
