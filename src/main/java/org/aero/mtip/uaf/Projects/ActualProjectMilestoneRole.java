package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.Slot;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActualProjectMilestoneRole extends Slot{
	public ActualProjectMilestoneRole(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_PROJECT_MILESTONE_ROLE;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT_MILESTONE_ROLE;
		this.sysmlElement = f.createSlotInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ACTUAL_PROJECT_MILESTONE_ROLE_STEREOTYPE);
		
		return sysmlElement;
	}
}
