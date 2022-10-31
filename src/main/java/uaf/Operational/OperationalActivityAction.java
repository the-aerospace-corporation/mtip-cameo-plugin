package uaf.Operational;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFConstants;
import uaf.UAFProfile;

public class OperationalActivityAction extends CallBehaviorAction {

	public OperationalActivityAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_ACTIVITY_ACTION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ACTIVITY_ACTION;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_ACTIVITY_ACTION_STEREOTYPE);
		
		return sysmlElement;
	}
}
