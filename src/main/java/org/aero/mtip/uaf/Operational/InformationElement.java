package org.aero.mtip.uaf.Operational;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class InformationElement extends CommonElement implements UAFElement {
	
	public InformationElement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.INFORMATION_ELEMENT;
		this.xmlConstant = XmlTagConstants.INFORMATION_ELEMENT;
	}
}