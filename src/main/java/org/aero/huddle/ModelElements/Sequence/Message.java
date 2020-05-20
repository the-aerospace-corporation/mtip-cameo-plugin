package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Message extends CommonElement {

	public Message(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.MESSAGE;
		this.xmlConstant = XmlTagConstants.MESSAGE;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);

		//Set Message Sort
		//Set mEssage Kind

		return sysmlElement;
	}
}
