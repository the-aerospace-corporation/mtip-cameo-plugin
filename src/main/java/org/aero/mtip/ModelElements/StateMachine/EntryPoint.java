/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class EntryPoint extends PseudoState {

	public EntryPoint(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.ENTRYPOINT;		
		this.sysmlConstant = SysmlConstants.ENTRYPOINT;
		this.xmlConstant = XmlTagConstants.ENTRYPOINT;
	}
}
