package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActualEnvironment extends InstanceSpecification {

	public ActualEnvironment(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.ACTUAL_ENVIRONMENT;
		this.xmlConstant = XmlTagConstants.ACTUAL_ENVIRONMENT;
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ACTUAL_ENVIRONMENT_STEREOTYPE);
		
		return sysmlElement;
	}

}
