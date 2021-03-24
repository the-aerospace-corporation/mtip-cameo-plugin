package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class TimeObservation extends CommonElement {

	public TimeObservation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.TIMEOBSERVATION;
		this.xmlConstant = XmlTagConstants.TIMEOBSERVATION;
		this.sysmlElement = f.createTimeObservationInstance();
	}

}
