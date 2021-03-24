package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class Join extends PseudoState {

	public Join(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.JOIN;
		this.sysmlConstant = SysmlConstants.JOIN;
		this.xmlConstant = XmlTagConstants.JOIN;
	}
}
