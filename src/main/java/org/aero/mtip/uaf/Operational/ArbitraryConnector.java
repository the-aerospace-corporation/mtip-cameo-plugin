package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ArbitraryConnector extends Dependency {

	public ArbitraryConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ARBITRARY_CONNECTOR;
		this.xmlConstant = XmlTagConstants.ARBITRARY_CONNECTOR;
	}
}
