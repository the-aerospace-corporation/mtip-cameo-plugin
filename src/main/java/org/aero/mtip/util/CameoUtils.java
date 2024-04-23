/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.CheckForNull;
import javax.swing.JOptionPane;

import org.aero.mtip.ModelElements.EnumerationLiteral;
import org.aero.mtip.constants.SysmlConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKind;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.impl.ElementsFactory;

public class CameoUtils {
	@SuppressWarnings("serial")
	public static HashMap<String, String> primitiveValueTypes = new HashMap<String, String>() {{
		put(SysmlConstants.BOOLEAN, "16_5_1_12c903cb_1245415335546_39033_4086");
		put(SysmlConstants.INTEGER, "_16_5_1_12c903cb_1245415335546_8641_4088");
		put(SysmlConstants.REAL, "_11_5EAPbeta_be00301_1147431819399_50461_1671");
	    put(SysmlConstants.STRING, "_16_5_1_12c903cb_1245415335546_479030_4092");
	}};
	
	@SuppressWarnings("serial")
	public static HashMap<String, String> primitiveValueTypesByID = new HashMap<String, String>() {{
		put("16_5_1_12c903cb_1245415335546_39033_4086", SysmlConstants.BOOLEAN);
		put("_16_5_1_12c903cb_1245415335546_8641_4088", SysmlConstants.INTEGER);
		put("_11_5EAPbeta_be00301_1147431819399_50461_1671", SysmlConstants.REAL);
	    put("_16_5_1_12c903cb_1245415335546_479030_4092", SysmlConstants.STRING);
	    put("_9_0_2_91a0295_1110274713995_297054_0", SysmlConstants.STRING); 		// UML Metamodel String representation
	}};
	
	public static void logGui(String text) {
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
	
	@CheckForNull
	public static Element findNearestRegion(Project project, Element owner) {
		if (owner == null) {
			return null;
		}
		
		Region region = null;
		Collection<Region> regions = null;
		
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
	
	public static Element findNearestActivity(Element owner) {
		if(owner != null) {
			if(owner instanceof Activity) {
				return owner;
			} else {
				Element nextOwner = owner.getOwner();
				if(nextOwner == null) {
					return null;
				}
				return findNearestActivity(nextOwner);
			}
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
	
	public static boolean isModel(Element element) {
		if(element.equals(Application.getInstance().getProject().getPrimaryModel())) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isProfile(Element element) {
		if(element instanceof Profile) {
			return true;
		}
		
		return false;
	}
	
	public static String getPseudoState(Element element) {
		try {
			Pseudostate pseudoState = (Pseudostate)element;
			PseudostateKind psKind = pseudoState.getKind();
			if(psKind.equals(PseudostateKindEnum.CHOICE)) {
				return SysmlConstants.CHOICE_PSEUDO_STATE;
			} else if(psKind.equals(PseudostateKindEnum.DEEPHISTORY)) {
				return SysmlConstants.DEEP_HISTORY;
			} else if(psKind.equals(PseudostateKindEnum.ENTRYPOINT)) {
				return SysmlConstants.ENTRY_POINT;
			} else if(psKind.equals(PseudostateKindEnum.EXITPOINT)) {
				return SysmlConstants.EXIT_POINT;
			} else if(psKind.equals(PseudostateKindEnum.FORK)) {
				return SysmlConstants.FORK;
			} else if(psKind.equals(PseudostateKindEnum.INITIAL)) {
				return SysmlConstants.INITIAL_PSEUDO_STATE;
			} else if(psKind.equals(PseudostateKindEnum.JOIN)) {
				return SysmlConstants.JOIN;
			} else if(psKind.equals(PseudostateKindEnum.SHALLOWHISTORY)) {
				return SysmlConstants.SHALLOW_HISTORY;
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
	
	public static boolean isMetaclass(Element element) {
		NamedElement owner = (NamedElement)element.getOwner();
		if(owner.getName().contentEquals("UML2 Metamodel")) {
			return true;
		}
		return false;
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
		if(primitiveValueTypes.keySet().contains(valueTypeString)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isPrimitiveValueType(Element element) {
		if(primitiveValueTypesByID.keySet().contains(element.getLocalID())) {
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
	
	public static boolean isSupportedInstanceSpecification(Element element) {
		if (!(element instanceof InstanceSpecification)) {
			return true;
		}
		
		if(element.getHumanName().contentEquals(element.getHumanType())
				|| Arrays.asList(SysmlConstants.RESERVE_INSTANCE_SPECIFICATION).contains(element.getHumanName())) {
			return false;
		}
		
		return true;
	}
	
	public static void logExceptionToGui(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		
		logGui(sw.toString());
	}
	
	public static String getElementName(Element element) {
		if(element instanceof NamedElement) {
			return ((NamedElement)element).getName();
		}
		
		return element.getHumanName();
	}
	
	@SuppressWarnings("deprecation")
	public static Element getPrimitiveValueType(String valueTypeEnum) {
		String primitiveValuePath = "SysML::Libraries::PrimitiveValueTypes::" + valueTypeEnum;
		return ModelHelper.findElementWithPath(primitiveValuePath);
	}
	
	@CheckForNull
	public static String getValueSpecificationValueAsString(ValueSpecification vs) {
		String strVal = null;
		
		if(vs instanceof LiteralString) {
			LiteralString ls = (LiteralString)vs;
			strVal = ls.getValue();
		} else if(vs instanceof LiteralReal) {
			LiteralReal lr = (LiteralReal)vs;
			double value = lr.getValue();
			strVal = String.valueOf(value);
		} else if(vs instanceof LiteralInteger) {
			LiteralInteger lr = (LiteralInteger)vs;
			int value = lr.getValue();
			strVal = String.valueOf(value);
		} else if(vs instanceof LiteralBoolean) {
			LiteralBoolean lr = (LiteralBoolean)vs;
			boolean value = lr.isValue();
			strVal = String.valueOf(value);
		} else if(vs instanceof ElementValue) {
			ElementValue ev = (ElementValue)vs;
			strVal = ev.getElement().getID();
		} else if(vs instanceof OpaqueExpression) {
			OpaqueExpression oe = (OpaqueExpression)vs;
			List<String> bodies = oe.getBody();
			Iterator<String> bodyIter = bodies.iterator();
			if(bodyIter.hasNext()) {
				strVal = bodyIter.next();
			}
		} else if(vs instanceof EnumerationLiteral) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral literal = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral)vs;
			strVal = literal.getName();
		} else if(vs instanceof InstanceValue ) {
			strVal = ModelHelper.getValueString(vs);
//			EnumerationLiteral  litVal = (EnumerationLiteral)ValueSpecificationHelper.getValueSpecficationValue(vs);
//			InstanceValue iv = (InstanceValue)vs;
//			InstanceSpecification is = iv.getInstance();
//			ValueSpecification vs2 = is.getSpecification();
//			strVal = getSlotValueAsString(vs2);
		} else {
			Logger.log(String.format("Value specification with id %s was not string, real, int, bool, or opaque expression.", vs.getID()));
		}
		return strVal;
	}
	
	public static void popUpMessage(String message) {
		JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), message);
	}
	
	public static void createSession(Project project, String sessionName) {
		if (SessionManager.getInstance().isSessionCreated(project)) {
			return;
		}
		
		SessionManager.getInstance().createSession(project, sessionName);
	}
	
	public static void closeSession(Project project) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			return;
		}
		
		SessionManager.getInstance().closeSession(project);
	}
}
