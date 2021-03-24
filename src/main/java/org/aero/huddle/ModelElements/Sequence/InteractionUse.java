package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InteractionUse extends CommonElement {

	public InteractionUse(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INTERACTIONUSE;
		this.xmlConstant = XmlTagConstants.INTERACTIONUSE;
		this.sysmlElement = f.createInteractionUseInstance();
	}
}
