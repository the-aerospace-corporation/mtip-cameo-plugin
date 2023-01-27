package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ResourceAction extends CallBehaviorAction {
	public ResourceAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.RESOURCE_ACTION;
		this.xmlConstant = XmlTagConstants.RESOURCE_ACTION;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.RESOURCE_ACTION_STEREOTYPE);
		
		return sysmlElement;
	}
}
