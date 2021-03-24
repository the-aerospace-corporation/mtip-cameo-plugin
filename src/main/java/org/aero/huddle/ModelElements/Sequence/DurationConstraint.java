package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class DurationConstraint extends CommonElement {

	public DurationConstraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DURATIONCONSTRAINT;
		this.xmlConstant = XmlTagConstants.DURATIONCONSTRAINT;
		this.sysmlElement = f.createDurationConstraintInstance();
	}

}
