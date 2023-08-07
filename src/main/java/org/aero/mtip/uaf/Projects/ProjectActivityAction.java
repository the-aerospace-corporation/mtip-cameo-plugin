package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ProjectActivityAction extends CallBehaviorAction{
	public ProjectActivityAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.PROJECT_ACTIVITY_ACTION;
		this.xmlConstant = XmlTagConstants.PROJECT_ACTIVITY_ACTION;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.PROJECT_ACTIVITY_ACTION_STEREOTYPE);
		
		return sysmlElement;
	}
}
