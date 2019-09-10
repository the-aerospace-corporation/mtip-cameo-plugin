package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class InterfaceBlock extends CommonElement {
	public InterfaceBlock(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype blockStereotype = StereotypesHelper.getStereotype(project, "InterfaceBlock", sysmlProfile);
		if (blockStereotype != null) {
			return createClassWithStereotype(project, name, blockStereotype, owner);
		}
		return null;
	}
}
