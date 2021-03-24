package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ProxyPort extends Port {

	public ProxyPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PROXYPORT;
		this.xmlConstant = XmlTagConstants.PROXYPORT;
		this.sysmlElement = f.createPortInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype fullPortStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.PROXYPORT_STEREOTYPE, sysmlProfile);
		StereotypesHelper.addStereotype(sysmlElement, fullPortStereotype);
		return sysmlElement;
	}
}
