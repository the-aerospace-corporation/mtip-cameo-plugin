/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;

public class DestructionOccurrenceSpecification extends CommonElement {

	public DestructionOccurrenceSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DESTRUCTIONOCCURRENCESPECIFICATION;
		this.xmlConstant = XmlTagConstants.DESTRUCTIONOCCURRENCESPECIFICATION;
		this.sysmlElement = f.createDestructionOccurrenceSpecificationInstance();
		
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		super.setOwner(project, owner);
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageEnd me = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageEnd)sysmlElement;
		if(owner instanceof Message) {
			me.set_messageOfReceiveEvent((Message) owner);
		}
	}
}
