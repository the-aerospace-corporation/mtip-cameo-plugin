package org.aero.mtip.profiles;

import org.aero.mtip.util.ExportLog;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class SysML {
	private static SysML instance;
	
	private Project project;
	private Profile profile;
	
	public static final String NAME = "SysML";
	
//	private static final String ASSOCIATION_BLOCK_NAME = ""
	private static final String BINDING_CONNECTOR_NAME = "BindingConnector";
	private static final String BLOCK_NAME = "Block";
	private static final String BOUND_REFERENCE_NAME = "BoundReference";
	private static final String BUSINESS_REQUIREMENT_NAME = "businessRequirement";
	private static final String CLASSIFIER_BEHAVIOR_NAME = "ClassifierBehaviorProperty";
	private static final String CONSTRAINT_BLOCK_NAME = "ConstraintBlock";
	private static final String CONSTRAINT_PARAMETER_NAME = "ConstraintParameter";
	private static final String CONSTRAINT_PROPERTY_NAME = "ConstraintProperty";
	private static final String COPY_NAME = "Copy";
	private static final String DERIVE_REQUIREMENT_NAME = "DeriveReqt";
	private static final String DESIGN_CONSTRAINT_NAME = "designConstraint";
	private static final String DIRECTED_FEATURE_NAME = "directedFeature";
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
	private static final String REFINE_NAME = "Refine";
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
	
	public static Stereotype getBlockStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, BLOCK_NAME, getInstance().profile);
	}
	
	public static Stereotype getConstraintBlockStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, CONSTRAINT_BLOCK_NAME, getInstance().profile);
	}
	
	public static Stereotype getDeriveRequirementStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, DERIVE_REQUIREMENT_NAME, getInstance().profile);
	}
	
	public static Stereotype getDirectedFeatureStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, DIRECTED_FEATURE_NAME, getInstance().profile);
	}
	
	public static Stereotype getDomainStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, DOMAIN_NAME, getInstance().profile);
	}
	
	public static Stereotype getFlowPropertyStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, FLOW_PROPERTY_NAME, getInstance().profile);
	}
	
	public static Stereotype getExternalStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, EXTERNAL_NAME, getInstance().profile);
	}
	
	public static Stereotype getFlowSpecificationStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, EXTERNAL_NAME, getInstance().profile);
	}
	
	public static Stereotype getFunctionalRequirementStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, FUNCTIONAL_REQUIREMENT_NAME, getInstance().profile);
	}
	
	public static Stereotype getProxyPortStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, PROXY_PORT_NAME, getInstance().profile);
	}
	
	public static Stereotype getRequirementStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, REQUIREMENT_NAME, getInstance().profile);
	}
	
	public static Stereotype getSubsystemStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, SUBSYSTEM_NAME, getInstance().profile);
	}
	
	public static Stereotype getSystemStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, SYSTEM_NAME, getInstance().profile);
	}
	
	public static Stereotype getSystemContextStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, SYSTEM_CONTEXT_NAME, getInstance().profile);
	}
	
	public static Stereotype getValueTypeStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, VALUE_TYPE_NAME, getInstance().profile);
	}
	
	public static boolean isAssociationBlock(Element element) {
		if(element instanceof AssociationClass && isBlock(element)) {
			return true;
		}
		
		return false;
	}
	
	protected boolean hasStereotype(Element element, String stereotypeName) {
		if (profile == null) {
			ExportLog.log(String.format("Profile not initialized when looking for stereotype name %s", stereotypeName));
			return false;
		}
		
		Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
		
		if (stereotype == null) {
			ExportLog.log(String.format("Stereotype %s not found in profile %s", stereotypeName, profile.getHumanName()));
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