package org.aero.mtip.profiles;

import org.aero.mtip.util.Logger;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class SysML {
	static SysML instance;
	
	Project project;
	Profile profile;
	
	public static final String NAME = "SysML";
	
//	static final String ASSOCIATION_BLOCK_NAME = ""
	static final String BINDING_CONNECTOR_NAME = "BindingConnector";
	static final String BLOCK_NAME = "Block";
	static final String BOUND_REFERENCE_NAME = "BoundReference";
	static final String BUSINESS_REQUIREMENT_NAME = "businessRequirement";
	static final String CLASSIFIER_BEHAVIOR_NAME = "ClassifierBehaviorProperty";
	static final String CONSTRAINT_BLOCK_NAME = "ConstraintBlock";
	static final String CONSTRAINT_PARAMETER_NAME = "ConstraintParameter";
	static final String CONSTRAINT_PROPERTY_NAME = "ConstraintProperty";
	static final String COPY_NAME = "Copy";
	static final String DERIVE_REQUIREMENT_NAME = "DeriveReqt";
	static final String DESIGN_CONSTRAINT_NAME = "designConstraint";
	static final String DIRECTED_FEATURE_NAME = "DirectedFeature";
	static final String DOMAIN_NAME = "Domain";
	static final String EXTENDED_REQUIREMENT_NAME = "extendedRequirement";
	static final String EXTERNAL_NAME = "External";
	static final String FLOW_PORT_NAME = "FlowPort";
	static final String FLOW_PROPERTY_NAME = "FlowProperty";
	static final String FLOW_SPECIFICATION_NAME = "FlowSpecification";
	static final String FULL_PORT_NAME = "FullPort";
	static final String FUNCTIONAL_REQUIREMENT_NAME = "functionalRequirement";
	static final String INTERFACE_BLOCK_NAME = "InterfaceBlock";
	static final String INTERFACE_REQUIREMENT_NAME = "interfaceRequirement";
	static final String ITEM_FLOW_NAME = "ItemFlow";
	static final String PARTICIPANT_PROPERTY_NAME = "ParticipantProperty";
	static final String PERFORMANCE_REQUIREMENT_NAME = "performanceRequirement";
	static final String PHYSICAL_REQUIREMENT_NAME = "physicalRequirement";
	static final String PROXY_PORT_NAME = "ProxyPort";
	static final String REFINE_NAME = "Refine";
	static final String REQUIREMENT_NAME = "Requirement";
	static final String SATISFY_NAME = "Satisfy";
	static final String STAKEHOLDER_NAME = "Stakeholder";
	static final String SUBSYSTEM_NAME = "Subsystem";
	static final String SYSTEM_NAME = "System";
	static final String SYSTEM_CONTEXT_NAME = "System context";
	static final String TRACE_NAME = "Trace";
	static final String USABILITY_REQUIREMENT_NAME = "usabilityRequirement";
	static final String VALUE_TYPE_NAME = "ValueType";
	static final String VERIFY_NAME = "Verify";
	static final String VIEW_NAME = "View";
	static final String VIEWPOINT_NAME = "Viewpoint";
	
	public static final String TEXT_PROPERTY_NAME = "Text";
	public static final String ID_PROPERTY_NAME = "Id";
	
//	public static Stereotype ASSOCIATION_BLOCK = 
	
	public static String FLOWPROPERTY_DIRECTION_PROPERTY = "direction";
	public static String SYSML_REQUIREMENTS_DIAGRAM = "Requirement Diagram";
	
	private SysML() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
	}
	
	public static SysML getInstance() {
		if (instance == null || instance.project.getPrimaryModel().getID() != Application.getInstance().getProject().getPrimaryModel().getID()) {
			instance = new SysML();
		}
		
		return instance;
	}
	
	Stereotype getStereotype(String stereotypeName) {
		return StereotypesHelper.getStereotype(project, stereotypeName, profile);
	}
	
	public static Stereotype getBlockStereotype() {
		return getInstance().getStereotype(BLOCK_NAME);
	}
	
	public static Stereotype getDeriveRequirementStereotype() {
		return getInstance().getStereotype(DERIVE_REQUIREMENT_NAME);
	}
	
	public static Stereotype getDirectedFeatureStereotype() {
		return getInstance().getStereotype(DIRECTED_FEATURE_NAME);
	}
	
	public static Stereotype getDesignConstraintStereotype() {
		return getInstance().getStereotype(DESIGN_CONSTRAINT_NAME);
	}
	
	public static Stereotype getDomainStereotype() {
		return getInstance().getStereotype(DOMAIN_NAME);
	}
	
	public static Stereotype getExtendedRequirementStereotype() {
		return getInstance().getStereotype(EXTENDED_REQUIREMENT_NAME);
	}
	
	public static Stereotype getExternalStereotype() {
		return getInstance().getStereotype(EXTERNAL_NAME);
	}
	
	public static Stereotype getFlowPortStereotype() {
		return getInstance().getStereotype(FLOW_PORT_NAME);
	}
	
	public static Stereotype getFlowPropertyStereotype() {
		return getInstance().getStereotype(FLOW_PROPERTY_NAME);
	}
	
	public static Stereotype getFullPortStereotype() {
		return getInstance().getStereotype(FULL_PORT_NAME);
	}
	
	public static Stereotype getFunctionalRequirementStereotype() {
		return getInstance().getStereotype(FUNCTIONAL_REQUIREMENT_NAME);
	}
	
	public static Stereotype getInterfaceBlockStereotype() {
		return getInstance().getStereotype(INTERFACE_BLOCK_NAME);
	}
	
	public static Stereotype getInterfaceRequirementStereotype() {
		return getInstance().getStereotype(INTERFACE_REQUIREMENT_NAME);
	}
	
	public static Stereotype getPerformanceRequirementStereotype() {
		return getInstance().getStereotype(PERFORMANCE_REQUIREMENT_NAME);
	}
	
	public static Stereotype getPhysicalRequirementStereotype() {
		return getInstance().getStereotype(PHYSICAL_REQUIREMENT_NAME);
	}
	
	public static Stereotype getRequirementStereotype() {
		return getInstance().getStereotype(REQUIREMENT_NAME);
	}
	
	public static Stereotype getSubsystemStereotype() {
		return getInstance().getStereotype(SUBSYSTEM_NAME);
	}
	
	public static Stereotype getSystemStereotype() {
		return getInstance().getStereotype(SYSTEM_NAME);
	}
	
	public static Stereotype getSystemContextStereotype() {
		return getInstance().getStereotype(SYSTEM_CONTEXT_NAME);
	}
	
	public static Stereotype getViewStereotype() {
		return getInstance().getStereotype(VIEW_NAME);
	}
	
	public static Stereotype getViewpointStereotype() {
		return getInstance().getStereotype(VIEWPOINT_NAME);
	}
	
	public static boolean isAssociationBlock(Element element) {
		if(element instanceof AssociationClass && isBlock(element)) {
			return true;
		}
		
		return false;
	}
	
	boolean hasStereotype(Element element, String stereotypeName) {
		if (profile == null) {
			Logger.log(String.format("Profile not initialized when looking for stereotype name %s", stereotypeName));
			return false;
		}
		
		Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
		
		if (stereotype == null) {
			Logger.log(String.format("Stereotype %s not found in profile %s", stereotypeName, profile.getHumanName()));
			return false;
		}
		
		if (!StereotypesHelper.hasStereotype(element, stereotype)) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isBindingConnector(Element element) {
		return getInstance().hasStereotype(element, BINDING_CONNECTOR_NAME);
	}
	
	public static boolean isBlock(Element element) {
		// Add additional checks for association block and other elements with block stereotype
		return getInstance().hasStereotype(element, BLOCK_NAME);
	}
	
	public static boolean isBoundReference(Element element) {
		return getInstance().hasStereotype(element, BOUND_REFERENCE_NAME);
	}
	
	public static boolean isBusinessRequirement(Element element) {
		return getInstance().hasStereotype(element, BUSINESS_REQUIREMENT_NAME);
	}
	
	public static boolean isClassifierBehavior(Element element) {
		return getInstance().hasStereotype(element, CLASSIFIER_BEHAVIOR_NAME);
	}
	
	public static boolean isConstraintBlock(Element element) {
		return getInstance().hasStereotype(element, CONSTRAINT_BLOCK_NAME);
	}
	
	public static boolean isConstraintParameter(Element element) {
		return getInstance().hasStereotype(element, CONSTRAINT_PARAMETER_NAME);
	}
	
	public static boolean isConstraintProperty(Element element) {
		return getInstance().hasStereotype(element, CONSTRAINT_PROPERTY_NAME);
	}
	
	public static boolean isCopy(Element element) {
		return getInstance().hasStereotype(element, COPY_NAME);
	}
	
	public static boolean isDeriveRequirement(Element element) {
		return getInstance().hasStereotype(element, DERIVE_REQUIREMENT_NAME);
	}
	
	public static boolean isDesignConstraint(Element element) {
		return getInstance().hasStereotype(element, DESIGN_CONSTRAINT_NAME);
	}
	
	public static boolean isDomain(Element element) {
		return getInstance().hasStereotype(element, DOMAIN_NAME);
	}
	
	public static boolean isExtendedRequirement(Element element) {
		return getInstance().hasStereotype(element, EXTENDED_REQUIREMENT_NAME);
	}
	
	public static boolean isExternal(Element element) {
		return getInstance().hasStereotype(element, EXTERNAL_NAME);
	}
	
	public static boolean isFlowPort(Element element) {
		return getInstance().hasStereotype(element, FLOW_PORT_NAME);
	}
	
	public static boolean isFlowProperty(Element element) {
		return getInstance().hasStereotype(element, FLOW_PROPERTY_NAME);
	}
	
	public static boolean isFlowSpecification(Element element) {
		return getInstance().hasStereotype(element, FLOW_SPECIFICATION_NAME);
	}
	
	public static boolean isFullPort(Element element) {
		return getInstance().hasStereotype(element, FULL_PORT_NAME);
	}
	
	public static boolean isFunctionalRequirement(Element element) {
		return getInstance().hasStereotype(element, FUNCTIONAL_REQUIREMENT_NAME);
	}
	
	public static boolean isInterfaceBlock(Element element) {
		return getInstance().hasStereotype(element, INTERFACE_BLOCK_NAME);
	}
	
	public static boolean isInterfaceRequirement(Element element) {
		return getInstance().hasStereotype(element, INTERFACE_REQUIREMENT_NAME);
	}
	
	public static boolean isItemFlow(Element element) {
		return getInstance().hasStereotype(element, ITEM_FLOW_NAME);
	}
	
	public static boolean isParticipantProperty(Element element) {
		return getInstance().hasStereotype(element, PARTICIPANT_PROPERTY_NAME);
	}
	
	public static boolean isPerformanceRequirement(Element element) {
		return getInstance().hasStereotype(element, PERFORMANCE_REQUIREMENT_NAME);
	}
	
	public static boolean isPhysicalRequirement(Element element) {
		return getInstance().hasStereotype(element, PHYSICAL_REQUIREMENT_NAME);
	}
	
	public static boolean isProxyPort(Element element) {
		return getInstance().hasStereotype(element, PROXY_PORT_NAME);
	}
	
	public static boolean isRefine(Element element) {
		return getInstance().hasStereotype(element, REFINE_NAME);
	}
	
	public static boolean isRequirement(Element element) {
		return getInstance().hasStereotype(element, REQUIREMENT_NAME);
	}
	
	public static boolean isSatisfy(Element element) {
		return getInstance().hasStereotype(element, SATISFY_NAME);
	}
	
	public static boolean isStakeholder(Element element) {
		return getInstance().hasStereotype(element, STAKEHOLDER_NAME);
	}
	
	public static boolean isSubsystem(Element element) {
		return getInstance().hasStereotype(element, SUBSYSTEM_NAME);
	}
	
	public static boolean isSystem(Element element) {
		return getInstance().hasStereotype(element, SYSTEM_NAME);
	}
	
	public static boolean isSystemContext(Element element) {
		return getInstance().hasStereotype(element, SYSTEM_CONTEXT_NAME);
	}
	
	public static boolean isTrace(Element element) {
		return getInstance().hasStereotype(element, TRACE_NAME);
	}
	
	public static boolean isUsabilityRequirement(Element element) {
		return getInstance().hasStereotype(element, USABILITY_REQUIREMENT_NAME);
	}
	
	public static boolean isValueType(Element element) {
		return getInstance().hasStereotype(element, VALUE_TYPE_NAME);
	}
	
	public static boolean isVerify(Element element) {
		return getInstance().hasStereotype(element, VERIFY_NAME);
	}
	
	public static boolean isView(Element element) {
		return getInstance().hasStereotype(element, VIEW_NAME);
	}
	
	public static boolean isViewpoint(Element element) {
		return getInstance().hasStereotype(element, VIEWPOINT_NAME);
	}
}