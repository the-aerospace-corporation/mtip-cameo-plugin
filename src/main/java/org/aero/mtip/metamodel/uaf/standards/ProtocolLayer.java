package org.aero.mtip.metamodel.uaf.standards;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ProtocolLayer extends Property {
	
	public ProtocolLayer(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.PROTOCOL_LAYER;
		this.xmlConstant = XmlTagConstants.PROTOCOL_LAYER;
	}
}
