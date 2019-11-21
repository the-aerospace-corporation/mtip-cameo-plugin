package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class ShallowHistory extends PseudoState {

	public ShallowHistory(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.SHALLOWHISTORY;
		this.xmlTag = XmlTagConstants.SHALLOWHISTORY;
	}

}
