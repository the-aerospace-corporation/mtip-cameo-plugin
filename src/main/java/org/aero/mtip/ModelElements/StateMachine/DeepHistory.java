/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class DeepHistory extends PseudoState {

	public DeepHistory(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.DEEPHISTORY;
		this.metamodelConstant = SysmlConstants.DEEP_HISTORY;
		this.xmlConstant = XmlTagConstants.DEEP_HISTORY;
	}

}
