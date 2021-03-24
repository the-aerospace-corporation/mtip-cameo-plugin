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

public class SystemContext extends CommonElement {

	public SystemContext(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.SYSTEMCONTEXT;
		this.xmlConstant = XmlTagConstants.BLOCK;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype domainStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.SYSTEM_CONTEXT_STEREOTYPE, sysmlProfile);
		if (domainStereotype != null) {
			return createClassWithStereotype(project, name, domainStereotype, owner);
		}
		return null;
	}
}
