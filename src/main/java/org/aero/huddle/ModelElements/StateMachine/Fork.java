package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class Fork extends PseudoState {

	public Fork(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.FORK;
		this.sysmlConstant = SysmlConstants.FORK;
		this.xmlConstant = XmlTagConstants.FORK;
	}
}