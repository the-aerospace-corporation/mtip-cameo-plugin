package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActualEnduringTask extends InstanceSpecification {

	public ActualEnduringTask(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.ACTUAL_ENDURING_TASK;
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ACTUAL_ENDURING_TASK_STEREOTYPE);
		
		return sysmlElement;
	}
}
