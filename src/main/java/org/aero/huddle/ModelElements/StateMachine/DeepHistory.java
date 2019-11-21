package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class DeepHistory extends PseudoState {

	public DeepHistory(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.DEEPHISTORY;
		this.xmlTag = XmlTagConstants.DEEPHISTORY;
	}

}
