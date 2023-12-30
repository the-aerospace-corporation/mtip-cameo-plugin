package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class SysML extends Profile {
	private static final String SYSML_PROFILE_NAME = "SysML";
	
//	private static final String ASSOCIATION_BLOCK_NAME = ""
	private static final String BINDING_CONNECTOR_NAME = "BindingConnector";
	private static final String BLOCK_NAME = "Block";
	private static final String BOUND_REFERENCE_NAME = "BoundReference";
	private static final String BUSINESS_REQUIREMENT_NAME = "businessRequirement";
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
	private static final String USABILITY_REQUIREMENT_NAME = "usabilityRequirement";
	private static final String VALUE_TYPE_NAME = "ValueType";
	private static final String VERIFY_NAME = "Verify";
	private static final String VIEW_NAME = "View";
	private static final String VIEWPOINT_NAME = "Viewpoint";
	
	public static final String TEXT_PROPERTY_NAME = "Text";
	public static final String ID_PROPERTY_NAME = "Id";
	
//	public static Stereotype ASSOCIATION_BLOCK = 
	
	public static String FLOWPROPERTY_DIRECTION_PROPERTY = "direction";
	public static String SYSML_REQUIREMENTS_DIAGRAM = "Requirement Diagram";
	
	public SysML(Project project) {
		this.project = project;
		this.PROFILE = StereotypesHelper.getProfile(project, SYSML_PROFILE_NAME);
	}
	
	public static void initialize(Project project) {
		if (instance != null && instance.project == project) {
			return;
		}
		
		instance = new SysML(project);
	}
	
	public static Stereotype getFlowPropertyStereotype() {
		return StereotypesHelper.getStereotype(instance.project, FLOW_PROPERTY_NAME, instance.PROFILE);
	}
	
	public static Stereotype getRequirementStereotype() {
		return StereotypesHelper.getStereotype(instance.project, REQUIREMENT_NAME, instance.PROFILE);
	}
	
	public static boolean isAssociationBlock(Element element) {
		if(element instanceof AssociationClass && isBlock(element)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isBindingConnector(Element element) {
		return hasStereotype(element, BINDING_CONNECTOR_NAME);
	}
	
	public static boolean isBlock(Element element) {
		// Add additional checks for association block and other elements with block stereotype
		return hasStereotype(element, BLOCK_NAME);
	}
	
	public static boolean isBoundReference(Element element) {
		return hasStereotype(element, BOUND_REFERENCE_NAME);
	}
	
	public static boolean isBusinessRequirement(Element element) {
		return hasStereotype(element, BUSINESS_REQUIREMENT_NAME);
	}
	
	public static boolean isClassifierBehavior(Element element) {
		return hasStereotype(element, CLASSIFIER_BEHAVIOR_NAME);
	}
	
	public static boolean isConstraintBlock(Element element) {
		return hasStereotype(element, CONSTRAINT_BLOCK_NAME);
	}
	
	public static boolean isConstraintParameter(Element element) {
		return hasStereotype(element, CONSTRAINT_PARAMETER_NAME);
	}
	
	public static boolean isConstraintProperty(Element element) {
		return hasStereotype(element, CONSTRAINT_PROPERTY_NAME);
	}
	
	public static boolean isCopy(Element element) {
		return hasStereotype(element, COPY_NAME);
	}
	
	public static boolean isDeriveRequirement(Element element) {
		return hasStereotype(element, DERIVE_REQUIREMENT_NAME);
	}
	
	public static boolean isDesignConstraint(Element element) {
		return hasStereotype(element, DESIGN_CONSTRAINT_NAME);
	}
	
	public static boolean isDomain(Element element) {
		return hasStereotype(element, DOMAIN_NAME);
	}
	
	public static boolean isExtendedRequirement(Element element) {
		return hasStereotype(element, EXTENDED_REQUIREMENT_NAME);
	}
	
	public static boolean isExternal(Element element) {
		return hasStereotype(element, EXTERNAL_NAME);
	}
	
	public static boolean isFlowPort(Element element) {
		return hasStereotype(element, FLOW_PORT_NAME);
	}
	
	public static boolean isFlowProperty(Element element) {
		return hasStereotype(element, FLOW_PROPERTY_NAME);
	}
	
	public static boolean isFlowSpecification(Element element) {
		return hasStereotype(element, FLOW_SPECIFICATION_NAME);
	}
	
	public static boolean isFullPort(Element element) {
		return hasStereotype(element, FULL_PORT_NAME);
	}
	
	public static boolean isFunctionalRequirement(Element element) {
		return hasStereotype(element, FUNCTIONAL_REQUIREMENT_NAME);
	}
	
	public static boolean isInterfaceBlock(Element element) {
		return hasStereotype(element, INTERFACE_BLOCK_NAME);
	}
	
	public static boolean isInterfaceRequirement(Element element) {
		return hasStereotype(element, INTERFACE_REQUIREMENT_NAME);
	}
	
	public static boolean isItemFlow(Element element) {
		return hasStereotype(element, ITEM_FLOW_NAME);
	}
	
	public static boolean isParticipantProperty(Element element) {
		return hasStereotype(element, PARTICIPANT_PROPERTY_NAME);
	}
	
	public static boolean isPerformanceRequirement(Element element) {
		return hasStereotype(element, PERFORMANCE_REQUIREMENT_NAME);
	}
	
	public static boolean isPhysicalRequirement(Element element) {
		return hasStereotype(element, PHYSICAL_REQUIREMENT_NAME);
	}
	
	public static boolean isProxyPort(Element element) {
		return hasStereotype(element, PROXY_PORT_NAME);
	}
	
	public static boolean isRefine(Element element) {
		return hasStereotype(element, REFINE_NAME);
	}
	
	public static boolean isRequirement(Element element) {
		return hasStereotype(element, REQUIREMENT_NAME);
	}
	
	public static boolean isSatisfy(Element element) {
		return hasStereotype(element, SATISFY_NAME);
	}
	
	public static boolean isStakeholder(Element element) {
		return hasStereotype(element, STAKEHOLDER_NAME);
	}
	
	public static boolean isSubsystem(Element element) {
		return hasStereotype(element, SUBSYSTEM_NAME);
	}
	
	public static boolean isSystem(Element element) {
		return hasStereotype(element, SYSTEM_NAME);
	}
	
	public static boolean isSystemContext(Element element) {
		return hasStereotype(element, SYSTEM_CONTEXT_NAME);
	}
	
	public static boolean isTrace(Element element) {
		return hasStereotype(element, TRACE_NAME);
	}
	
	public static boolean isUsabilityRequirement(Element element) {
		return hasStereotype(element, USABILITY_REQUIREMENT_NAME);
	}
	
	public static boolean isValueType(Element element) {
		return hasStereotype(element, VALUE_TYPE_NAME);
	}
	
	public static boolean isVerify(Element element) {
		return hasStereotype(element, VERIFY_NAME);
	}
	
	public static boolean isView(Element element) {
		return hasStereotype(element, VIEW_NAME);
	}
	
	public static boolean isViewpoint(Element element) {
		return hasStereotype(element, VIEWPOINT_NAME);
	}
}