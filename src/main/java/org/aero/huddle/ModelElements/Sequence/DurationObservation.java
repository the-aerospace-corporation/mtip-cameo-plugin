package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class DurationObservation extends CommonElement {

	public DurationObservation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DURATIONOBSERVATION;
		this.xmlConstant = XmlTagConstants.DURATIONOBSERVATION;
		this.sysmlElement = f.createDurationObservationInstance();
	}

}
