package org.aero.mtip.metamodel.uaf.Operational;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class OperationalInterface extends CommonElement implements UAFElement {
	
	public OperationalInterface(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.OPERATIONAL_INTERFACE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_INTERFACE;
	}
}