package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class SysMLProfile extends org.aero.mtip.profiles.Profile {
	private static boolean isInitialized = false;
	
	public static Profile SYSML_PROFILE;
	
	private static final String SYSML_PROFILE_NAME = "Sysml";
//	private static final String ASSOCIATION_BLOCK_NAME = ""
	private static final String BINDING_CONNECTOR_NAME = "BindingConnector";
	private static final String BLOCK_NAME = "Block";
	private static final String BOUND_REFERENCE_NAME = "BoundReference";
	private static final String CLASSIFIER_BEHAVIOR_NAME = "ClassifierBehavior";
	private static final String CONSTRAINT_BLOCK_NAME = "ConstraintBlock";
	private static final String CONSTRAINT_PARAMETER_NAME = "ConstraintParameter";
	private static final String CONSTRAINT_PROPERTY_NAME = "ConstraintProperty";
	private static final String COPY_NAME = "Copy";
	private static final String DERIVE_REQUIREMENT_NAME = "DeriveReqt";
	private static final String DESIGN_CONSTRAINT_NAME = "designConstraint";
	private static final String DOMAIN_NAME = "Domain";
	private static final String EXTENDED_REQUIREMENT_NAME = "extendedRequirement";
	private static final String EXTERNAL_NAME = "External";
	private static final String FLOW_PORT_NAME = "FlowPort";
	private static final String FLOW_PROPERTY_NAME = "FlowProperty";
	private static final String FLOW_SPECIFICATION_NAME = "FlowSpecification";
	private static final String FULL_PORT_NAME = "FullPort";
	private static final String FUNCTIONAL_REQUIREMENT_NAME = "functionalRequirement";
	private static final String INTERFACE_BLOCK_NAME = "InterfaceBlock";
	private static final String INTERFACE_REQUIREMENT_NAME = "interfaceRequirement";
	private static final String ITEM_FLOW_NAME = "ItemFlow";
	private static final String PARTICIPANT_PROPERTY_NAME = "ParticipantProperty";
	private static final String PERFORMANCE_REQUIREMENT_NAME = "performanceRequirement";
	private static final String PHYSICAL_REQUIREMENT_NAME = "physicalRequirement";
	private static final String PROXY_PORT_NAME = "ProxyPort";
	private static final String REFINE_NAME = "RefineName";
	private static final String REQUIREMENT_NAME = "Requirement";
	private static final String SATISFY_NAME = "Satisfy";
	private static final String STAKEHOLDER_NAME = "Stakeholder";
	private static final String SUBSYSTEM_NAME = "Subsystem";
	private static final String SYSTEM_NAME = "System";
	private static final String SYSTEM_CONTEXT_NAME = "System context";
	private static final String TRACE_NAME = "Trace";
	private static final String VALUE_TYPE_NAME = "ValueType";
	private static final String VERIFY_NAME = "Verify";
	private static final String VIEW_NAME = "View";
	private static final String VIEWPOINT_NAME = "Viewpoint";
	
	public static Stereotype BINDING_CONNECTOR_STEREOTYPE;
	public static Stereotype BLOCK_STEREOTYPE;
	public static Stereotype BOUND_REFERENCE_STEREOTYPE;
	public static Stereotype CLASSIFIER_BEHAVIOR_STEREOTYPE;
	public static Stereotype CONSTRAINT_BLOCK_STEREOTYPE;
	public static Stereotype CONSTRAINT_PARAMETER_STEREOTYPE;
	public static Stereotype CONSTRAINT_PROPERTY_STEREOTYPE;
	public static Stereotype COPY_STEREOTYPE;
	public static Stereotype DERIVE_REQUIREMENT_STEREOTYPE;
	public static Stereotype DESIGN_CONSTRAINT_STEREOTYPE;
	public static Stereotype DOMAIN_STEREOTYPE;
	public static Stereotype EXTENDED_REQUIREMENT_STEREOTYPE;
	public static Stereotype EXTERNAL_STEREOTYPE;
	public static Stereotype FLOW_PORT_STEREOTYPE;
	public static Stereotype FLOW_PROPERTY_STEREOTYPE;
	public static Stereotype FLOW_SPECIFICATION_STEREOTYPE;
	public static Stereotype FULL_PORT_STEREOTYPE;
	public static Stereotype FUNCTIONAL_REQUIREMENT_STEREOTYPE;
	public static Stereotype INTERFACE_BLOCK_STEREOTYPE;
	public static Stereotype INTERFACE_REQUIREMENT_STEREOTYPE;
	public static Stereotype ITEM_FLOW_STEREOTYPE;
	public static Stereotype PARTICIPANT_PROPERTY_STEREOTYPE;
	public static Stereotype PERFORMANCE_REQUIREMENT_STEREOTYPE;
	public static Stereotype PHYSICAL_REQUIREMENT_STEREOTYPE;
	public static Stereotype PROXY_PORT_STEREOTYPE;
	public static Stereotype REFINE_STEREOTYPE;
	public static Stereotype REQUIREMENT_STEREOTYPE;
	public static Stereotype SATISFY_STEREOTYPE;
	public static Stereotype STAKEHOLDER_STEREOTYPE;
	public static Stereotype SUBSYSTEM_STEREOTYPE;
	public static Stereotype SYSTEM_STEREOTYPE;
	public static Stereotype SYSTEM_CONTEXT_STEREOTYPE;
	public static Stereotype TRACE_STEREOTYPE;
	public static Stereotype VALUE_TYPE_STEREOTYPE;
	public static Stereotype VERIFY_STEREOTYPE;
	public static Stereotype VIEW_STEREOTYPE;
	public static Stereotype VIEWPOINT_STEREOTYPE;
	
	
//	public static Stereotype ASSOCIATION_BLOCK = 
	
	public static String FLOWPROPERTY_DIRECTION_PROPERTY = "direction";
	public static String SYSML_REQUIREMENTS_DIAGRAM = "Requirement Diagram";
	
	public static void initializeStereotypes() {
		if(isInitialized) {
			return;
		}
		
		Project project = Application.getInstance().getProject();
		SYSML_PROFILE = StereotypesHelper.getProfile(project, SYSML_PROFILE_NAME);
		
		BINDING_CONNECTOR_STEREOTYPE = StereotypesHelper.getStereotype(project, BINDING_CONNECTOR_NAME, SYSML_PROFILE);
		BLOCK_STEREOTYPE = StereotypesHelper.getStereotype(project, BLOCK_NAME, SYSML_PROFILE);
		BOUND_REFERENCE_STEREOTYPE = StereotypesHelper.getStereotype(project, BOUND_REFERENCE_NAME, SYSML_PROFILE);
		CLASSIFIER_BEHAVIOR_STEREOTYPE = StereotypesHelper.getStereotype(project, CLASSIFIER_BEHAVIOR_NAME, SYSML_PROFILE);
		CONSTRAINT_BLOCK_STEREOTYPE = StereotypesHelper.getStereotype(project, CONSTRAINT_BLOCK_NAME, SYSML_PROFILE);
		CONSTRAINT_PARAMETER_STEREOTYPE = StereotypesHelper.getStereotype(project, CONSTRAINT_PARAMETER_NAME, SYSML_PROFILE);
		CONSTRAINT_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, CONSTRAINT_PROPERTY_NAME, SYSML_PROFILE);
		COPY_STEREOTYPE = StereotypesHelper.getStereotype(project, COPY_NAME, SYSML_PROFILE);
		DERIVE_REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, DERIVE_REQUIREMENT_NAME, SYSML_PROFILE);
		DESIGN_CONSTRAINT_STEREOTYPE = StereotypesHelper.getStereotype(project, DESIGN_CONSTRAINT_NAME, SYSML_PROFILE);
		DOMAIN_STEREOTYPE = StereotypesHelper.getStereotype(project, DOMAIN_NAME, SYSML_PROFILE);
		EXTENDED_REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, EXTENDED_REQUIREMENT_NAME, SYSML_PROFILE);
		EXTERNAL_STEREOTYPE = StereotypesHelper.getStereotype(project, EXTERNAL_NAME, SYSML_PROFILE);
		FLOW_PORT_STEREOTYPE = StereotypesHelper.getStereotype(project, FLOW_PORT_NAME, SYSML_PROFILE);
		FLOW_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, FLOW_PROPERTY_NAME, SYSML_PROFILE);
		FLOW_SPECIFICATION_STEREOTYPE = StereotypesHelper.getStereotype(project, FLOW_SPECIFICATION_NAME, SYSML_PROFILE);
		FULL_PORT_STEREOTYPE = StereotypesHelper.getStereotype(project, FULL_PORT_NAME, SYSML_PROFILE);
		FUNCTIONAL_REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, FUNCTIONAL_REQUIREMENT_NAME, SYSML_PROFILE);
		INTERFACE_BLOCK_STEREOTYPE = StereotypesHelper.getStereotype(project, INTERFACE_BLOCK_NAME, SYSML_PROFILE);
		INTERFACE_REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, INTERFACE_REQUIREMENT_NAME, SYSML_PROFILE);
		ITEM_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, ITEM_FLOW_NAME, SYSML_PROFILE);
		PARTICIPANT_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, PARTICIPANT_PROPERTY_NAME, SYSML_PROFILE);
		PERFORMANCE_REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, PERFORMANCE_REQUIREMENT_NAME, SYSML_PROFILE);
		PHYSICAL_REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, PHYSICAL_REQUIREMENT_NAME, SYSML_PROFILE);
		PROXY_PORT_STEREOTYPE = StereotypesHelper.getStereotype(project, PROXY_PORT_NAME, SYSML_PROFILE);
		REFINE_STEREOTYPE = StereotypesHelper.getStereotype(project, REFINE_NAME, SYSML_PROFILE);
		REQUIREMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, REQUIREMENT_NAME, SYSML_PROFILE);
		SATISFY_STEREOTYPE = StereotypesHelper.getStereotype(project, SATISFY_NAME, SYSML_PROFILE);
		STAKEHOLDER_STEREOTYPE = StereotypesHelper.getStereotype(project, STAKEHOLDER_NAME, SYSML_PROFILE);
		SUBSYSTEM_STEREOTYPE = StereotypesHelper.getStereotype(project, SUBSYSTEM_NAME, SYSML_PROFILE);
		SYSTEM_STEREOTYPE = StereotypesHelper.getStereotype(project, SYSTEM_NAME, SYSML_PROFILE);
		SYSTEM_CONTEXT_STEREOTYPE = StereotypesHelper.getStereotype(project, SYSTEM_CONTEXT_NAME, SYSML_PROFILE);
		TRACE_STEREOTYPE = StereotypesHelper.getStereotype(project, TRACE_NAME, SYSML_PROFILE);
		VALUE_TYPE_STEREOTYPE = StereotypesHelper.getStereotype(project, VALUE_TYPE_NAME, SYSML_PROFILE);
		VERIFY_STEREOTYPE = StereotypesHelper.getStereotype(project, VERIFY_NAME, SYSML_PROFILE);
		VIEW_STEREOTYPE = StereotypesHelper.getStereotype(project, VIEW_NAME, SYSML_PROFILE);
		VIEWPOINT_STEREOTYPE = StereotypesHelper.getStereotype(project, VIEWPOINT_NAME, SYSML_PROFILE);
		
		isInitialized = true;
	}
	
	public static boolean isAssociationBlock(Element element) {
		if(element instanceof AssociationClass && isBlock(element)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isBindingConnector(Element element) {
		return hasStereotype(element, BINDING_CONNECTOR_STEREOTYPE);
	}
	
	public static boolean isBlock(Element element) {
		// Add additional checks for association block and other elements with block stereotype
		return hasStereotype(element, BLOCK_STEREOTYPE);
	}
	
	public static boolean isBoundReference(Element element) {
		return hasStereotype(element, BOUND_REFERENCE_STEREOTYPE);
	}
	
	public static boolean isClassifierBehavior(Element element) {
		return hasStereotype(element, CLASSIFIER_BEHAVIOR_STEREOTYPE);
	}
	
	public static boolean isConstraintBlock(Element element) {
		return hasStereotype(element, CONSTRAINT_BLOCK_STEREOTYPE);
	}
	
	public static boolean isConstraintParameter(Element element) {
		return hasStereotype(element, CONSTRAINT_PARAMETER_STEREOTYPE);
	}
	
	public static boolean isConstraintProperty(Element element) {
		return hasStereotype(element, CONSTRAINT_PROPERTY_STEREOTYPE);
	}
	
	public static boolean isCopy(Element element) {
		return hasStereotype(element, COPY_STEREOTYPE);
	}
	
	public static boolean isDeriveRequirement(Element element) {
		return hasStereotype(element, DERIVE_REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isDesignConstraint(Element element) {
		return hasStereotype(element, DESIGN_CONSTRAINT_STEREOTYPE);
	}
	
	public static boolean isDomain(Element element) {
		return hasStereotype(element, DOMAIN_STEREOTYPE);
	}
	
	public static boolean isExtendedRequirement(Element element) {
		return hasStereotype(element, EXTENDED_REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isExternal(Element element) {
		return hasStereotype(element, EXTERNAL_STEREOTYPE);
	}
	
	public static boolean isFlowPort(Element element) {
		return hasStereotype(element, FLOW_PORT_STEREOTYPE);
	}
	
	public static boolean isFlowProperty(Element element) {
		return hasStereotype(element, FLOW_PROPERTY_STEREOTYPE);
	}
	
	public static boolean isFlowSpecification(Element element) {
		return hasStereotype(element, FLOW_SPECIFICATION_STEREOTYPE);
	}
	
	public static boolean isFullPort(Element element) {
		return hasStereotype(element, FULL_PORT_STEREOTYPE);
	}
	
	public static boolean isFunctionalRequirement(Element element) {
		return hasStereotype(element, FUNCTIONAL_REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isInterfaceBlock(Element element) {
		return hasStereotype(element, INTERFACE_BLOCK_STEREOTYPE);
	}
	
	public static boolean isInterfaceRequirement(Element element) {
		return hasStereotype(element, INTERFACE_REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isItemFlow(Element element) {
		return hasStereotype(element, ITEM_FLOW_STEREOTYPE);
	}
	
	public static boolean isParticipantProperty(Element element) {
		return hasStereotype(element, PARTICIPANT_PROPERTY_STEREOTYPE);
	}
	
	public static boolean isPerformanceRequirement(Element element) {
		return hasStereotype(element, PERFORMANCE_REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isPhysicalRequirement(Element element) {
		return hasStereotype(element, PHYSICAL_REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isProxyPort(Element element) {
		return hasStereotype(element, PROXY_PORT_STEREOTYPE);
	}
	
	public static boolean isRefine(Element element) {
		return hasStereotype(element, REFINE_STEREOTYPE);
	}
	
	public static boolean isRequirement(Element element) {
		return hasStereotype(element, REQUIREMENT_STEREOTYPE);
	}
	
	public static boolean isSatisfy(Element element) {
		return hasStereotype(element, SATISFY_STEREOTYPE);
	}
	
	public static boolean isStakeholder(Element element) {
		return hasStereotype(element, STAKEHOLDER_STEREOTYPE);
	}
	
	public static boolean isSubsystem(Element element) {
		return hasStereotype(element, SUBSYSTEM_STEREOTYPE);
	}
	
	public static boolean isSystem(Element element) {
		return hasStereotype(element, SYSTEM_STEREOTYPE);
	}
	
	public static boolean isSystemContext(Element element) {
		return hasStereotype(element, SYSTEM_CONTEXT_STEREOTYPE);
	}
	
	public static boolean isTrace(Element element) {
		return hasStereotype(element, TRACE_STEREOTYPE);
	}
	
	public static boolean isValueType(Element element) {
		return hasStereotype(element, VALUE_TYPE_STEREOTYPE);
	}
	
	public static boolean isVerify(Element element) {
		return hasStereotype(element, VERIFY_STEREOTYPE);
	}
	
	public static boolean isView(Element element) {
		return hasStereotype(element, VIEW_STEREOTYPE);
	}
	
	public static boolean isViewpoint(Element element) {
		return hasStereotype(element, VIEWPOINT_STEREOTYPE);
	}
}
