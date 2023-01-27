/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.CheckForNull;

import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKind;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.impl.ElementsFactory;

public class CameoUtils {
	public static HashMap<String, String> primitiveValueTypes = new HashMap<String, String>() {{
		put(SysmlConstants.BOOLEAN, "16_5_1_12c903cb_1245415335546_39033_4086");
		put(SysmlConstants.INTEGER, "_16_5_1_12c903cb_1245415335546_8641_4088");
		put(SysmlConstants.REAL, "_11_5EAPbeta_be00301_1147431819399_50461_1671");
	    put(SysmlConstants.STRING, "_16_5_1_12c903cb_1245415335546_479030_4092");
	}};
	
	public static HashMap<String, String> primitiveValueTypesByID = new HashMap<String, String>() {{
		put("16_5_1_12c903cb_1245415335546_39033_4086", SysmlConstants.BOOLEAN);
		put("_16_5_1_12c903cb_1245415335546_8641_4088", SysmlConstants.INTEGER);
		put("_11_5EAPbeta_be00301_1147431819399_50461_1671", SysmlConstants.REAL);
	    put("_16_5_1_12c903cb_1245415335546_479030_4092", SysmlConstants.STRING);
	}};
	
	public static void logGUI(String text) {
		Application.getInstance().getGUILog().log(text);
	}
	
	public static Element findNearestPackage(Project project, Element element) {
		Element topPackage = project.getPrimaryModel();
		Element owner = element.getOwner();
		if(owner == null) {
			return null;
		}
		if(owner.equals(topPackage)) {
			return topPackage;
		}
		if(owner instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package || owner instanceof Profile) {
			return owner;
		} else {
			return findNearestPackage(project, owner);
		}
	}
	
	public static Element findNearestProfile(Project project, Element element) {
		Element topPackage = project.getPrimaryModel();
		Element owner = element.getOwner();
		if(owner.equals(topPackage)) {
			return topPackage;
		}
		if(owner instanceof Profile) {
			return owner;
		} else {
			return findNearestProfile(project, owner);
		}
	}
	
	public static Element findNearestRegion(Project project, Element owner) {
		Region region = null;
		Collection<Region> regions = null;
		CameoUtils.logGUI("Searching for state machine with current element " + owner.getHumanType() + " and id " + owner.getID());
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
			if(nextOwner == null) {
				return null;
			}
			return findNearestRegion(project, nextOwner);
		}
	}
	public static Element findNearestBlock(Project project, Element owner) {
		if(owner != null) {
			CameoUtils.logGUI("Searching for block for current element with id " + owner.getID());
			if(SysMLProfile.isBlock(owner)) {
				return owner;
			} else {
				Element nextOwner = owner.getOwner();
				if(nextOwner == null) {
					return null;
				}
				return findNearestBlock(project, nextOwner);
			}
		}
		return null;
	}
	
	public static Element findNearestActivity(Project project, Element owner) {
		if(owner != null) {
			CameoUtils.logGUI("Searching for activity with current element " + owner.getHumanType() + " and id " + owner.getID());
			if(owner instanceof Activity) {
				return owner;
			} else {
				Element nextOwner = owner.getOwner();
				if(nextOwner == null) {
					return null;
				}
				return findNearestActivity(project, nextOwner);
			}
		}
		return null;
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
	
	public static boolean isCustomization(Project project, Element element) {
		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, "Customization", umlStandardprofile);
		
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		if(stereotypes.contains(customizationStereotype)) {
			return true;
		}
		return false;
	}
	
	public static String oclKindValidationString(String profileName, String stereotypeName) {
		return "self.oclIsKindOf(" + "::" + stereotypeName + ")"; 
	}
	
	public static String oclSupplierDependency(String profileName, String relationshipStereotypeName, String classStereotypeName) {
		return oclKindValidationString(profileName, relationshipStereotypeName) + " implies self.supplierDependency->exists (d| d.client->exists(e|e.oclIsKindOf(" + classStereotypeName + ")))";
	}
	
	public static Stereotype getValidationSuiteStereotype(Project project) {
		Profile profile = StereotypesHelper.getProfile(project, "UML Standard Profile");
		Stereotype validationSuite = StereotypesHelper.getStereotype(project, "validationSuite", profile);
		return validationSuite;
	}
	public static Stereotype getModelLibraryStereotype() {
		
		return null;
	}
	
	public static boolean isModel(Element element, Project project) {
		if(element.equals(project.getPrimaryModel())) {
			return true;
		}
		return false;
	}
	
	public static boolean isProfile(Element element, Project project) {
		if(element instanceof Profile) {
			return true;
		}
		return false;
	}
	
	public static boolean isProperty(Element element, Project project) {
		if(element instanceof Property) {
			if(MDCustomizationForSysMLProfile.isPartProperty(element) || MDCustomizationForSysMLProfile.isReferenceProperty(element) ||MDCustomizationForSysMLProfile.isValueProperty(element)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static String getPseudoState(Element element) {
		try {
			Pseudostate pseudoState = (Pseudostate)element;
			PseudostateKind psKind = pseudoState.getKind();
			if(psKind.equals(PseudostateKindEnum.CHOICE)) {
				return SysmlConstants.CHOICEPSEUDOSTATE;
			} else if(psKind.equals(PseudostateKindEnum.DEEPHISTORY)) {
				return SysmlConstants.DEEPHISTORY;
			} else if(psKind.equals(PseudostateKindEnum.ENTRYPOINT)) {
				return SysmlConstants.ENTRYPOINT;
			} else if(psKind.equals(PseudostateKindEnum.EXITPOINT)) {
				return SysmlConstants.EXITPOINT;
			} else if(psKind.equals(PseudostateKindEnum.FORK)) {
				return SysmlConstants.FORK;
			} else if(psKind.equals(PseudostateKindEnum.INITIAL)) {
				return SysmlConstants.INITIALPSEUDOSTATE;
			} else if(psKind.equals(PseudostateKindEnum.JOIN)) {
				return SysmlConstants.JOIN;
			} else if(psKind.equals(PseudostateKindEnum.SHALLOWHISTORY)) {
				return SysmlConstants.SHALLOWHISTORY;
			} else if(psKind.equals(PseudostateKindEnum.TERMINATE)) {
				return SysmlConstants.TERMINATE;
			} else {
				return null;
			}
		} catch(ClassCastException cce) {
			return null;
		}
	}
	
	public static boolean isChoicePseudoState(Element element, Project project) {
		try {
			Pseudostate pseudoState = (Pseudostate)element;
			PseudostateKind psKind = pseudoState.getKind();
			if(psKind.equals(PseudostateKindEnum.CHOICE)) {
				return true;
			} else {
				return false;
			}
		} catch(ClassCastException cce) {
			return false;
		}
	}
	
	@CheckForNull
	public static org.w3c.dom.Element getDirectChild(org.w3c.dom.Element parent, String name) {
	    for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
	        if(child instanceof Element && name.equals(child.getNodeName())) return (org.w3c.dom.Element) child;
	    }
	    return null;
	}
	
	@CheckForNull
	public static Node getDirectChild(Node parent, String name) {
		NodeList childNodes = parent.getChildNodes();
		for(int k = 0; k < childNodes.getLength(); k++) {
			Node childNode = childNodes.item(k);
			if(childNode.getNodeType() == Node.ELEMENT_NODE) {
				if(childNode.getNodeName().contentEquals(name)) {
					return childNode;
				}
			}
		}
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
	
	public static boolean isMetaclass(Element element) {
		NamedElement owner = (NamedElement)element.getOwner();
		if(owner.getName().contentEquals("UML2 Metamodel")) {
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
	
	public static boolean containsIgnoreCase(List<String> list, String soughtFor) {
		for (String current : list) {
			if (current.equalsIgnoreCase(soughtFor)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPrimitiveValueType(String valueTypeString) {
		if(Arrays.asList(SysmlConstants.primitiveValueTypes).contains(valueTypeString)) {
			return true;
		}
		return false;
	}
	
	public static boolean isPrimitiveValueType(Element element) {
		if(Arrays.asList(SysmlConstants.primitiveValueTypeIDs).contains(element.getLocalID())) {
			return true;
		}
		return false;
	}
	
	public static boolean isCameoID(String id) {
		if(id.startsWith("_")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotExplicitlySupported(Element element) {
		if (element instanceof ElementValue ||
			    element instanceof LiteralReal ||
			    element instanceof LiteralBoolean ||
			    element instanceof LiteralInteger ||
			    element instanceof LiteralString ||
			    element instanceof LiteralUnlimitedNatural ||
			    element instanceof InstanceValue ||
			    element instanceof ConnectorEnd) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static Element getPrimitiveValueType(String valueTypeEnum) {
		String primitiveValuePath = "SysML::Libraries::PrimitiveValueTypes::" + valueTypeEnum;
		return ModelHelper.findElementWithPath(primitiveValuePath);
	}
	
	public static String determineMetamodel(Project project) {
		Profile uafProfile = StereotypesHelper.getProfile(project, UAFConstants.UAF_PROFILE_NAME);
		if(uafProfile != null) {
			new UAFProfile(project);
			return UAFConstants.UAF;
		} else {
			return  SysmlConstants.SYSML;
		}
	}
}
