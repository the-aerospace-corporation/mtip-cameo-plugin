package org.aero.mtip.metamodel.uaf.Resources;


import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class Forecast extends Dependency {

	public Forecast(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FORECAST;
		this.xmlConstant = XmlTagConstants.FORECAST;
	}
}
