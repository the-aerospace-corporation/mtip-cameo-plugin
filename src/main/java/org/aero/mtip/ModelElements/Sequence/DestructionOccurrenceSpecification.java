/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;

public class DestructionOccurrenceSpecification extends CommonElement {

	public DestructionOccurrenceSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.DESTRUCTION_OCCURRENCE_SPECIFICATION;
		this.xmlConstant = XmlTagConstants.DESTRUCTION_OCCURRENCE_SPECIFICATION;
		this.element = f.createDestructionOccurrenceSpecificationInstance();
		
	}
	
	@Override
	public void setOwner(Element owner) {
		super.setOwner(owner);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageEnd me = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageEnd)element;
		if(owner instanceof Message) {
			me.set_messageOfReceiveEvent((Message) owner);
		}
	}
}
