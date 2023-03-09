/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

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
		this.sysmlConstant = SysmlConstants.METACLASS;
		this.xmlConstant = XmlTagConstants.METACLASS;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		sysmlElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::" + this.name);
		// Add checks for other metaclass profiles (i.e. SysML)
		// StereotypesHelper.getMetaClassByName(project, "Class");
		if (!(sysmlElement == null)) {
			CameoUtils.logGUI("Creating metaclass with name: " + this.name);
			CameoUtils.logGUI(sysmlElement.getID());
			
		} else {
			sysmlElement = f.createClassInstance();
			Profile standardProfile = StereotypesHelper.getProfile(project,  "StandardProfile");
			Stereotype metaclassStereotype = StereotypesHelper.getStereotype(project, "Metaclass", standardProfile);
			StereotypesHelper.addStereotype(sysmlElement, metaclassStereotype);
			((NamedElement)sysmlElement).setName(name);
		}
		return sysmlElement;
	}
	
}
