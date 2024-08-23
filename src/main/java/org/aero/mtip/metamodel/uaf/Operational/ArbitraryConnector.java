package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class ArbitraryConnector extends Dependency {

	public ArbitraryConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ARBITRARY_CONNECTOR;
		this.xmlConstant = XmlTagConstants.ARBITRARY_CONNECTOR;
	}
}
