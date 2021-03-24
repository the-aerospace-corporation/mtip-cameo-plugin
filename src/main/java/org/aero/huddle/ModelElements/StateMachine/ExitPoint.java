package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class ExitPoint extends PseudoState {

	public ExitPoint(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.EXITPOINT;		
		this.sysmlConstant = SysmlConstants.EXITPOINT;
		this.xmlConstant = XmlTagConstants.EXITPOINT;
	}
}