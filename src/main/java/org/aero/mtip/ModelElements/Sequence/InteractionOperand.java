/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class InteractionOperand extends CommonElement {

	public InteractionOperand(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.INTERACTION_OPERAND;
		this.xmlConstant = XmlTagConstants.INTERACTION_OPERAND;
		this.element = f.createInteractionOperandInstance();
	}
	
	@Override
	public void setOwner(Element owner) {
		super.setOwner(owner);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand io = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand)element;
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment cf = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)owner;
		io.set_combinedFragmentOfOperand(cf);
	}
}
