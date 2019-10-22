package org.aero.huddle.util;

import java.util.Collection;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.impl.ElementsFactory;

public class CameoUtils {
	public static void logGUI(String text) {
		Application.getInstance().getGUILog().log(text);
	}
	
	public static Element findNearestPackage(Project project, Element element) {
		Element topPackage = project.getPrimaryModel();
		Element owner = element.getOwner();
		if(owner.equals(topPackage)) {
			return topPackage;
		}
		if(owner.getHumanType().equals("Package")) {
			return owner;
		} else {
			return findNearestPackage(project, owner);
		}
		
	}
	
	public static Element findNearestRegion(Project project, Element owner) {
		Region region = null;
		Collection<Region> regions = null;
		CameoUtils.logGUI("Searching for state machine with current element " + owner.getHumanType() + " and id " + owner.getLocalID());
		if(owner instanceof StateMachine) {
			StateMachine sm = (StateMachine)owner;
			regions = sm.getRegion();
			if(regions != null) {
				region = regions.iterator().next();
				return region;
			} else {
				ElementsFactory f = project.getElementsFactory();
				region = f.createRegionInstance();
				region.setOwner(sm);
				return region;
			}
		} else {
			Element nextOwner = owner.getOwner();
			return findNearestRegion(project, nextOwner);
		}
	}
	
	public static Stereotype getStereotype(String stereotypeName) {
		Project project = Application.getInstance().getProject();
		switch(stereotypeName) {
		case "auxiliary":
			Profile umlProfile = StereotypesHelper.getProfile(project, "UML Standard Profile"); 
			Stereotype auxiliaryStereotype = StereotypesHelper.getStereotype(project, "auxiliaryResource", umlProfile);
			return auxiliaryStereotype;
		}
		return null;
	}
	
	public static Stereotype getModelLibraryStereotype() {
		
		return null;
	}
	
	public static boolean isAssociationBlock(Element element, Project project) {
		//Add additional check for block stereotype
		if(element instanceof AssociationClass && isBlock(element, project)) {
			return true;
		}
		return false;
	}
	
	public static boolean isSysmlStereotypedElement(Element element, Project project, String stereotype) {
		Profile sysml = StereotypesHelper.getProfile(project,  "SysML");
		Collection<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		Stereotype blockStereotype = StereotypesHelper.getStereotype(project, stereotype, sysml);
		if(stereotypes.contains(blockStereotype)) { 
			return true;
		}
		return false;
	}
	
	public static boolean isCopy(Element element, Project project) {
		String stereotype = SysMLProfile.COPY_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isDeriveRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.DERIVEREQT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isRefine(Element element, Project project) {
		String stereotype = SysMLProfile.REFINE_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isSatisfy(Element element, Project project) {
		String stereotype = SysMLProfile.SATISFY_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isTrace(Element element, Project project) {
		String stereotype = SysMLProfile.TRACE_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isVerify(Element element, Project project) {
		String stereotype = SysMLProfile.VERIFY_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isBlock(Element element, Project project) {
		String stereotype = SysMLProfile.BLOCK_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isDesignConstraint(Element element, Project project) {
		String stereotype = SysMLProfile.DESIGNCONSTRAINT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isExtendedRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.EXTENDEDREQUIREMENT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isFunctionalRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.FUNCTIONALREQUIREMENT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isInterfaceBlock(Element element, Project project) {
		String stereotype = SysMLProfile.INTERFACEBLOCK_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isInterfaceRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.INTERFACEREQUIREMENT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isPerformanceRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.PERFORMANCEREQUIREMENT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isPhysicalRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.PHYSICALREQUIREMENT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isRequirement(Element element, Project project) {
		String stereotype = SysMLProfile.REQUIREMENT_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isPartProperty(Element element, Project project) {
		String stereotype = MDCustomizationForSysMLProfile.PARTPROPERTY_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isValueProperty(Element element, Project project) {
		String stereotype = MDCustomizationForSysMLProfile.VALUEPROPERTY_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
	
	public static boolean isValueType(Element element, Project project) {
		String stereotype = SysMLProfile.VALUETYPE_STEREOTYPE;
		return isSysmlStereotypedElement(element, project, stereotype);
	}
}
