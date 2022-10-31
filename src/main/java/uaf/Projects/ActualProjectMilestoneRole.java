package uaf.Projects;

import org.aero.mtip.ModelElements.Block.Slot;
import uaf.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

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
