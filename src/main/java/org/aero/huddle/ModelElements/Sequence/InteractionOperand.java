package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class InteractionOperand extends CommonElement {

	public InteractionOperand(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INTERACTIONOPERAND;
		this.xmlConstant = XmlTagConstants.INTERACTIONOPERAND;
		this.sysmlElement = f.createInteractionOperandInstance();
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		super.setOwner(project, owner);
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand io = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand)sysmlElement;
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment cf = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)owner;
		io.set_combinedFragmentOfOperand(cf);
	}
}
