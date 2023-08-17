package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.SysmlConstants;

import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class EnvironmentProperty extends Property {

	public EnvironmentProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ENVIRONMENT_PROPERTY;
		this.xmlConstant = XmlTagConstants.ENVIRONMENT_PROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ENVIRONMENT_PROPERTY_STEREOTYPE);
		
		return sysmlElement;
	}
}
