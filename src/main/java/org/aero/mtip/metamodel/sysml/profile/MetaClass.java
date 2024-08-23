/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.profile;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class MetaClass extends CommonElement {

	public MetaClass(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = SysmlConstants.METACLASS;
		this.xmlConstant = XmlTagConstants.METACLASS;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		element = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::" + this.name);
		// Add checks for other metaclass profiles (i.e. SysML)
		// StereotypesHelper.getMetaClassByName(project, "Class");
		if (!(element == null)) {
			CameoUtils.logGui("Creating metaclass with name: " + this.name);
			CameoUtils.logGui(MtipUtils.getId(element));
			
		} else {
			element = f.createClassInstance();
			Profile standardProfile = StereotypesHelper.getProfile(project,  "StandardProfile");
			Stereotype metaclassStereotype = StereotypesHelper.getStereotype(project, "Metaclass", standardProfile);
			StereotypesHelper.addStereotype(element, metaclassStereotype);
			((NamedElement)element).setName(name);
		}
		return element;
	}
	
}
