package uaf.Projects;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import uaf.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class ActualProjectMilestone extends InstanceSpecification {

	public ActualProjectMilestone(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.ACTUAL_PROJECT_MILESTONE;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT_MILESTONE;
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ACTUAL_PROJECT_MILESTONE_STEREOTYPE);
		
		return sysmlElement;
	}

}
