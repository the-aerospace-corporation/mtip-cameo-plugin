package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class ChoicePseudoState extends PseudoState {

	public ChoicePseudoState(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.CHOICE;		
		this.sysmlConstant = SysmlConstants.CHOICEPSEUDOSTATE;
		this.xmlConstant = XmlTagConstants.CHOICEPSEUDOSTATE;
	}
}
