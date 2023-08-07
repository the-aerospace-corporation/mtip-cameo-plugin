package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ResourceSignalProperty extends Property {

	public ResourceSignalProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.RESOURCE_SIGNAL_PROPERTY;
		this.xmlConstant = XmlTagConstants.RESOURCE_SIGNAL_PROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.RESOURCE_SIGNAL_PROPERTY_STEREOTYPE);
		
		return sysmlElement;
	}
}
