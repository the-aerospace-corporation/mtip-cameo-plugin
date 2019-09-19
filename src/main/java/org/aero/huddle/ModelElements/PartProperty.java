package org.aero.huddle.ModelElements;

import org.aero.huddle.util.CameoUtils;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class PartProperty extends CommonElement {
	public PartProperty(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner) {		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype partPropertyStereotype = StereotypesHelper.getStereotype(project, "PartProperty", mdCustomizationProfile);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Property Element");
		}
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property prop = project.getElementsFactory().createPropertyInstance();
		prop.setName(name);
		
		//Add verification that property is allowed to be a child of owner
		
		if (owner != null) {
			String ownerType = owner.getHumanType();
			if(ownerType.equals("PartProperty")) {
				Element newOwner = createNestedPartProperties(project, owner);
				TypedElement ownerTyped = (TypedElement)owner;
				ownerTyped.setType((Type)newOwner);
			}
			prop.setOwner(owner);
		} else {
			prop.setOwner(project.getPrimaryModel());
		}
		
		StereotypesHelper.addStereotype(prop, partPropertyStereotype);
		
		SessionManager.getInstance().closeSession(project);
		return prop;
	}
	
	public Element createNestedPartProperties(Project project, Element owner) {
		Element sysmlPackage = CameoUtils.findNearestPackage(project, owner);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype blockStereotype = StereotypesHelper.getStereotype(project, "Block", sysmlProfile);
		
		Element block = createClassWithStereotype(project, name, blockStereotype, sysmlPackage);
		return block;
	}
}