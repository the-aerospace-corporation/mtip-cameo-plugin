package org.aero.mtip.uaf.standards;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProtocolLayer extends Property {
	
	public ProtocolLayer(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.PROTOCOL_LAYER;
		this.xmlConstant = XmlTagConstants.PROTOCOL_LAYER;
	}
}
