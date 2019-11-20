package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class Terminate extends PseudoState {

	public Terminate(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.TERMINATE;
		this.xmlTag = XmlTagConstants.TERMINATE;
	}

}
