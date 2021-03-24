package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class System extends CommonElement {

	public System(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.SYSTEM;
		this.xmlConstant = XmlTagConstants.BLOCK;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype systemStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.SYSTEM_STEREOTYPE, sysmlProfile);
		if (systemStereotype != null) {
			return createClassWithStereotype(project, name, systemStereotype, owner);
		}
		return null;
	}
}
