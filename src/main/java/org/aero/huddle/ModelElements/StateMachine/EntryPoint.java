package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;

public class EntryPoint extends PseudoState {

	public EntryPoint(String name, String EAID) {
		super(name, EAID);
		this.psKind = PseudostateKindEnum.ENTRYPOINT;		
		this.sysmlConstant = SysmlConstants.ENTRYPOINT;
		this.xmlConstant = XmlTagConstants.ENTRYPOINT;
	}
}
