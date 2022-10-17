package uaf.Projects;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class ProjectActivity extends Activity{
	public ProjectActivity(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.PROJECT_ACTIVITY;
		this.xmlConstant = XmlTagConstants.PROJECT_ACTIVITY;
		this.sysmlElement = f.createActivityInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.PROJECT_ACTIVITY_STEREOTYPE);
		
		return sysmlElement;
	}
}
