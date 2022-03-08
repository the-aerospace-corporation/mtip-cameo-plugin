/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class DeepHistory extends PseudoState {

	public DeepHistory(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.DEEPHISTORY;		
		this.sysmlConstant = SysmlConstants.DEEPHISTORY;
		this.xmlConstant = XmlTagConstants.DEEPHISTORY;
	}

}
