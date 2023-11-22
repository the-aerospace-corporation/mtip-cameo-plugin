/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class Join extends PseudoState {

	public Join(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.JOIN;
		this.metamodelConstant = SysmlConstants.JOIN;
		this.xmlConstant = XmlTagConstants.JOIN;
	}
}
