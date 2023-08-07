package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class OrganizationInEnterprise extends Abstraction{
	public OrganizationInEnterprise(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.ORGANIZATION_IN_ENTERPRISE;
		this.sysmlConstant = UAFConstants.ORGANIZATION_IN_ENTERPRISE;
		this.sysmlElement = f.createAbstractionInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ORGANIZATION_IN_ENTERPRISE_STEREOTYPE);
		
		return sysmlElement;
	}

}
