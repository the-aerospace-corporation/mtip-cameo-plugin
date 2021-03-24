package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class TimeConstraint extends CommonElement {

	public TimeConstraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.TIMECONSTRAINT;
		this.xmlConstant = XmlTagConstants.TIMECONSTRAINT;
		this.sysmlElement = f.createTimeConstraintInstance();
	}

}
