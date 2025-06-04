/*
 * The Aerospace Corporation Huddle_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.util;

import java.text.DateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import javax.annotation.CheckForNull;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.UmlConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.core.CommonElementsFactory;
import org.aero.mtip.profiles.DslCustomization;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.profiles.UAF;
import com.nomagic.ci.persistence.IAttachedProject;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.core.ProjectUtilities;
import com.nomagic.magicdraw.uml.DiagramType;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.ActionClass;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.InputPin;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OpaqueAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OutputPin;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdintermediateactions.CreateObjectAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdintermediateactions.DestroyObjectAction;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityFinalNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityParameterNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.InitialNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow;
import com.nomagic.uml2.ext.magicdraw.activities.mdcompleteactivities.DataStoreNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdcompleteactivities.InterruptibleActivityRegion;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.CentralBufferNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.FlowFinalNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ForkNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.JoinNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.MergeNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdstructuredactivities.ConditionalNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdstructuredactivities.LoopNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdstructuredactivities.StructuredActivityNode;
import com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdinformationflows.InformationFlow;
import com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdinformationflows.InformationItem;
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency;
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Usage;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.Interface;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.InterfaceRealization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.PackageImport;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.ChangeEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationInterval;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationObservation;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeConstraint;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeObservation;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdcollaborations.Collaboration;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.deployments.mdartifacts.Artifact;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.DestructionOccurrenceSpecification;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Interaction;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageOccurrenceSpecification;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.StateInvariant;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionUse;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Extension;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Actor;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Extend;
import com.nomagic.uml2.ext.magicdraw.mdusecases.ExtensionPoint;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Include;
import com.nomagic.uml2.ext.magicdraw.mdusecases.UseCase;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.ConnectionPointReference;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.FinalState;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition;

public class MtipUtils {
  static TimeZone tz = null;
  static DateFormat df = null;
  
  public static boolean isSupported(Element element) {
    if (getEntityType(element) == null) {
      return false;
    }

    return true;
  }

  public static boolean isSupportedElement(String commonElementType) {
    if (!UmlConstants.UML_ELEMENTS.contains(commonElementType)
          && !SysmlConstants.SYSML_ELEMENTS.contains(commonElementType)
          && !UAFConstants.UAF_ELEMENTS.contains(commonElementType)) {
      return false;
    }

    return true;
  }

  public static boolean isSupportedRelationship(String commonRelationshipType) {
    if (!SysmlConstants.SYSML_RELATIONSHIPS.contains(commonRelationshipType)
        && !UAFConstants.UAF_RELATIONSHIPS.contains(commonRelationshipType)) {
      return false;
    }

    return true;
  }

  public static boolean isSupportedDiagram(String commonElementType) {
    if (!SysmlConstants.SYSML_DIAGRAMS.contains(commonElementType)
        && !UAFConstants.UAF_DIAGRAMS.contains(commonElementType)
        && !DoDAFConstants.DODAF_DIAGRAMS.contains(commonElementType)) {
      return false;
    }

    return true;
  }

  public static String getMetamodelPrefix(String commonElementType) {
    if (isSysmlEntity(commonElementType)) {
      return SysmlConstants.METAMODEL_PREFIX;
    }

    if (isUafEntity(commonElementType)) {
      return UAFConstants.METAMODEL_PREFIX;
    }
    
    if (isUmlEntity(commonElementType)) {
      return UmlConstants.METAMODEL_PREFIX;
    }

    return "null";
  }

  public static boolean isSysmlEntity(String commonElementType) {
    if (SysmlConstants.SYSML_ELEMENTS.contains(commonElementType)
        || SysmlConstants.SYSML_RELATIONSHIPS.contains(commonElementType)
        || SysmlConstants.SYSML_DIAGRAMS.contains(commonElementType)) {
      return true;
    }

    return false;
  }

  public static boolean isUafEntity(String commonElementType) {
    if (UAFConstants.UAF_ELEMENTS.contains(commonElementType)
        || UAFConstants.UAF_RELATIONSHIPS.contains(commonElementType)
        || UAFConstants.UAF_DIAGRAMS.contains(commonElementType)) {
      return true;
    }

    return false;
  }
  
  public static boolean isUmlEntity(String commonElementType) {
    if (UmlConstants.UML_ELEMENTS.contains(commonElementType)
        || UmlConstants.UML_RELATIONSHIPS.contains(commonElementType)
        || UmlConstants.UML_DIAGRAMS.contains(commonElementType)) {
      return true;
    }

    return false;
  }

  public static Package createMTIPProfile(Project project) {
    CommonElementsFactory cef = new CommonElementsFactory();
    CommonElement profileClass = cef.createElement(SysmlConstants.PROFILE, "MTIP Stereotypes", "");
    Profile mtipProfile =
        (Profile) profileClass.createElement(project, project.getPrimaryModel(), null);

    addMTIPImportedStereotype(project, mtipProfile);

    return mtipProfile;
  }

  private static Element addMTIPImportedStereotype(Project project, Profile huddleProfile) {
    CommonElementsFactory cef = new CommonElementsFactory();
    CommonElement stereotypeClass =
        cef.createElement(SysmlConstants.STEREOTYPE, "MTIP Imported", "");
    Element stereotype = stereotypeClass.createElement(project, huddleProfile, null);

    return stereotype;
  }

  @CheckForNull
  public static String getEntityType(Element element) {
    if (element == null) {
      return null;
    }
    
    if (element instanceof Diagram) {
      return getDiagramType(element);
    }

    if (isRelationship(element)) {
      return getRelationshipType(element);
    }

    return getElementType(element);
  }

  @CheckForNull
  public static String getElementType(Element element) {
    if (isUafModel()) {
      String elementType = getUafElementType(element);
      
      if (elementType != null) {
        return elementType;
      }
    }

    return getSysmlElementType(element);
  }

  public static String getSysmlElementType(Element element) {
    if (element instanceof AcceptEventAction) {
      return SysmlConstants.ACCEPT_EVENT_ACTION;
    } else if (element instanceof Activity) {
      return SysmlConstants.ACTIVITY;
    } else if (element instanceof ActivityFinalNode) {
      return SysmlConstants.ACTIVITY_FINAL_NODE;
    } else if (element instanceof ActivityParameterNode) {
      return SysmlConstants.ACTIVITY_PARAMETER_NODE;
    } else if (element instanceof ActivityPartition) {
      return SysmlConstants.ACTIVITY_PARTITION;
    } else if (element instanceof Actor) {
      return SysmlConstants.ACTOR;
    } else if (element instanceof Artifact) {
      return UmlConstants.ARTIFACT;
    } else if (SysML.isBoundReference(element)) {
      return SysmlConstants.BOUND_REFERENCE;
    } else if (SysML.isClassifierBehavior(element)) {
      return SysmlConstants.CLASSIFIER_BEHAVIOR_PROPERTY;
    } else if (element instanceof CallBehaviorAction) {
      return SysmlConstants.CALL_BEHAVIOR_ACTION;
    } else if (element instanceof CallOperationAction) {
      return SysmlConstants.CALL_OPERATION_ACTION;
    } else if (element instanceof ChangeEvent) {
      return SysmlConstants.CHANGE_EVENT;
    } else if (element instanceof Collaboration) {
      return SysmlConstants.COLLABORATION;
    } else if (element instanceof CombinedFragment) {
      return SysmlConstants.COMBINED_FRAGMENT;
//    } else if (element instanceof Comment) {
//      return SysmlConstants.COMMENT;
    } else if (element instanceof ConditionalNode) {
      return SysmlConstants.CONDITIONAL_NODE;
    } else if (element instanceof ConnectionPointReference) {
      return SysmlConstants.CONNECTION_POINT_REFERENCE;
    } else if (element instanceof Connector) {
      return SysmlConstants.CONNECTOR;
    } else if (element instanceof Constraint) {
      return SysmlConstants.CONSTRAINT;
    } else if (SysML.isConstraintBlock(element)) {
      return SysmlConstants.CONSTRAINT_BLOCK;
    } else if (MDCustomizationForSysML.isConstraintParameter(element)) {
      return SysmlConstants.CONSTRAINT_PARAMETER;
    } else if (MDCustomizationForSysML.isConstraintProperty(element)) {
      return SysmlConstants.CONSTRAINT_PROPERTY;
    } else if (element instanceof CreateObjectAction) {
      return SysmlConstants.CREATE_OBJECT_ACTION;
    } else if (DslCustomization.isCustomization(element)) {
      return SysmlConstants.CUSTOMIZATION;
    } else if (element instanceof DataStoreNode) {
      return SysmlConstants.DATA_STORE_NODE;
    } else if (element instanceof DecisionNode) {
      return SysmlConstants.DECISION_NODE;
    } else if (SysML.isDeriveRequirement(element)) {
      return SysmlConstants.DERIVE_REQUIREMENT;
    } else if (SysML.isDesignConstraint(element)) {
      return SysmlConstants.DESIGN_CONSTRAINT;
    } else if (element instanceof DestroyObjectAction) {
      return SysmlConstants.DESTROY_OBJECT_ACTION;
    } else if (element instanceof DestructionOccurrenceSpecification) {
      return SysmlConstants.DESTRUCTION_OCCURRENCE_SPECIFICATION;
    } else if (SysML.isDomain(element)) {
      return SysmlConstants.DOMAIN;
    } else if (element instanceof DurationConstraint) {
      return SysmlConstants.DURATION_CONSTRAINT;
    } else if (element instanceof DurationInterval) {
      return SysmlConstants.DURATION_INTERVAL;
    } else if (element instanceof DurationObservation) {
      return SysmlConstants.DURATION_OBSERVATION;
    } else if (element instanceof Enumeration) {
      return SysmlConstants.ENUMERATION;
    } else if (element instanceof EnumerationLiteral) {
      return SysmlConstants.ENUMERATION_LITERAL;
    } else if (SysML.isExtendedRequirement(element)) {
      return SysmlConstants.EXTENDED_REQUIREMENT;
    } else if (element instanceof ExtensionPoint) {
      return SysmlConstants.EXTENSION_POINT;
    } else if (SysML.isExternal(element)) {
      return SysmlConstants.EXTERNAL;
    } else if (element instanceof FinalState) {
      return SysmlConstants.FINAL_STATE;
    } else if (element instanceof FlowFinalNode) {
      return SysmlConstants.FLOW_FINAL_NODE;
    } else if (SysML.isFlowPort(element)) {
      return SysmlConstants.FLOW_PORT;
    } else if (SysML.isFlowProperty(element)) {
      return SysmlConstants.FLOW_PROPERTY;
    } else if (SysML.isFlowSpecification(element)) {
      return SysmlConstants.FLOW_SPECFICATION;
    } else if (element instanceof ForkNode) {
      return SysmlConstants.FORK_NODE;
    } else if (SysML.isFullPort(element)) {
      return SysmlConstants.FULL_PORT;
    } else if (SysML.isFunctionalRequirement(element)) {
      return SysmlConstants.FUNCTIONAL_REQUIREMENT;
    } else if (element instanceof FunctionBehavior) {
      return SysmlConstants.FUNCTION_BEHAVIOR;
    } else if (element instanceof InformationItem) {
      return SysmlConstants.INFORMATION_ITEM;
    } else if (element instanceof Interaction) {
      return SysmlConstants.INTERACTION;
    } else if (element instanceof InteractionOperand) {
      return SysmlConstants.INTERACTION_OPERAND;
    } else if (element instanceof InteractionUse) {
      return SysmlConstants.INTERACTION_USE;
    } else if (element instanceof InitialNode) {
      return SysmlConstants.INITIAL_NODE;
    } else if (element instanceof Pseudostate) {
      return CameoUtils.getPseudoState(element);
    } else if (element instanceof InputPin) {
      return SysmlConstants.INPUT_PIN;
    } else if (element instanceof Interface) {
      return SysmlConstants.INTERFACE;
    } else if (SysML.isInterfaceBlock(element)) {
      return SysmlConstants.INTERFACE_BLOCK;
    } else if (SysML.isInterfaceRequirement(element)) {
      return SysmlConstants.INTERFACE_REQUIREMENT;
    } else if (element instanceof InterruptibleActivityRegion) {
      return SysmlConstants.INTERRUPTIBLE_ACTIVITY_REGION;
    } else if (element instanceof JoinNode) {
      return SysmlConstants.JOIN_NODE;
    } else if (element instanceof Lifeline) {
      return SysmlConstants.LIFELINE;
    } else if (element instanceof LoopNode) {
      return SysmlConstants.LOOP_NODE;
    } else if (element instanceof MergeNode) {
      return SysmlConstants.MERGE_NODE;
    } else if (element instanceof MessageOccurrenceSpecification) {
      return SysmlConstants.MESSAGE_OCCURRENCE_SPECIFICATION;
    } else if (CameoUtils.isModel(element)) {
      return SysmlConstants.MODEL;
    } else if (element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
      return SysmlConstants.OPAQUE_EXPRESSION;
    } else if (element instanceof OpaqueAction) {
      return SysmlConstants.OPAQUE_ACTION;
    } else if (element instanceof OpaqueBehavior) {
      return SysmlConstants.OPAQUE_BEHAVIOR;
    } else if (element instanceof Operation) {
      return SysmlConstants.OPERATION;
    } else if (SysML.isParticipantProperty(element)) {
      return SysmlConstants.PARTICIPANT_PROPERTY;
    } else if (MDCustomizationForSysML.isPartProperty(element)) {
      return SysmlConstants.PART_PROPERTY;
    } else if (element instanceof Profile) {
      return SysmlConstants.PROFILE;
    } else if (element instanceof OutputPin) {
      return SysmlConstants.OUTPUT_PIN;
    } else if (element instanceof Package) {
      return SysmlConstants.PACKAGE;
    } else if (SysML.isPerformanceRequirement(element)) {
      return SysmlConstants.PERFORMANCE_REQUIREMENT;
    } else if (SysML.isPhysicalRequirement(element)) {
      return SysmlConstants.PHYSICAL_REQUIREMENT;
      // Proxy Port must come before Port as all specialized ports are instanceof Port
    } else if (SysML.isProxyPort(element)) {
      return SysmlConstants.PROXY_PORT;
    } else if (element instanceof Port) {
      return SysmlConstants.PORT;
    } else if (MDCustomizationForSysML.isQuantityKind(element)) {
      return SysmlConstants.QUANTITY_KIND;
    } else if (MDCustomizationForSysML.isReferenceProperty(element)) {
      return SysmlConstants.REFERENCE_PROPERTY;
    } else if (element instanceof Region) {
      return SysmlConstants.REGION;
    } else if (SysML.isRefine(element)) {
      return SysmlConstants.REFINE;
    } else if (SysML.isRequirement(element)) {
      return SysmlConstants.REQUIREMENT;
    } else if (element instanceof SendSignalAction) {
      return SysmlConstants.SEND_SIGNAL_ACTION;
    } else if (element instanceof Signal) {
      return SysmlConstants.SIGNAL;
    } else if (element instanceof SignalEvent) {
      return SysmlConstants.SIGNAL_EVENT;
    } else if (element instanceof Slot) {
      return SysmlConstants.SLOT;
    } else if (element instanceof State) {
      return SysmlConstants.STATE;
    } else if (element instanceof StateInvariant) {
      return SysmlConstants.STATE_INVARIANT;
    } else if (element instanceof StateMachine) {
      return SysmlConstants.STATE_MACHINE;
    } else if (element instanceof Stereotype) {
      return SysmlConstants.STEREOTYPE;
    } else if (element instanceof StructuredActivityNode) {
      return SysmlConstants.STRUCTURED_ACTIVITY_NODE;
    } else if (SysML.isSubsystem(element)) {
      return SysmlConstants.SUBSYSTEM;
    } else if (SysML.isSystemContext(element)) {
      return SysmlConstants.SYSTEM_CONTEXT;
    } else if (SysML.isSystem(element)) {
      return SysmlConstants.SYSTEM;
    } else if (element instanceof TimeConstraint) {
      return SysmlConstants.TIME_CONSTRAINT;
    } else if (element instanceof TimeEvent) {
      return SysmlConstants.TIME_EVENT;
    } else if (element instanceof TimeExpression) {
      return SysmlConstants.TIME_EXPRESSION;
    } else if (element instanceof TimeObservation) {
      return SysmlConstants.TIME_OBSERVATION;
    } else if (element instanceof Trigger) {
      return SysmlConstants.TRIGGER;
    } else if (MDCustomizationForSysML.isUnit(element)) {
      return SysmlConstants.UNIT;
    } else if (element instanceof UseCase) {
      return SysmlConstants.USE_CASE;
    } else if (MDCustomizationForSysML.isValueProperty(element)) {
      return SysmlConstants.VALUE_PROPERTY;
    } else if (SysML.isValueType(element)) {
      return SysmlConstants.VALUE_TYPE;

      // Super classes listed below as to not to override their children
    } else if (element instanceof Constraint) {
      return SysmlConstants.CONSTRAINT;
    } else if (MDCustomizationForSysML.isProperty(element)) {
      return SysmlConstants.PROPERTY;
    } else if (element instanceof InstanceSpecification
        && CameoUtils.isSupportedInstanceSpecification(element)) {
      return SysmlConstants.INSTANCE_SPECIFICATION;
      // Check ActionClass last as any child action class will be an instance of ActionClass
    } else if (element instanceof ActionClass) {
      return SysmlConstants.ACTION;
    } else if (element instanceof CentralBufferNode) {
      return SysmlConstants.CENTRAL_BUFFER_NODE;
    } else if (SysML.isBlock(element)) {
      return SysmlConstants.BLOCK;
    } else if (element instanceof Parameter) {
      return SysmlConstants.PARAMETER;
    } else if (element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class) {
      // Check if Metaclass
      if (CameoUtils.isMetaclass(element)) {
        return SysmlConstants.METACLASS;
      } else {
        return SysmlConstants.CLASS;
      }
    }

    return null;
  }

  @CheckForNull
  public static String getRelationshipType(Element element) {
    if (MtipUtils.isUafModel()) {
      String elementType = getUafElementType(element);
      
      if (elementType != null) {
        return elementType;
      }
    }

    return getSysmlRelationshipType(element);
  }

  @CheckForNull
  public static String getSysmlRelationshipType(Element element) {
    if (SysML.isAssociationBlock(element)) {
      return SysmlConstants.ASSOCIATION_BLOCK;
    } else if (SysML.isBindingConnector(element)) {
      return SysmlConstants.BINDING_CONNECTOR;
    } else if (SysML.isCopy(element)) {
      return SysmlConstants.COPY;
    } else if (element instanceof Connector) {
      return SysmlConstants.CONNECTOR;
    } else if (element instanceof ControlFlow) {
      return SysmlConstants.CONTROL_FLOW;
    } else if (element instanceof Extend) {
      return SysmlConstants.EXTEND;
    } else if (element instanceof Extension) {
      return SysmlConstants.EXTENSION;
    } else if (element instanceof Generalization) {
      return SysmlConstants.GENERALIZATION;
    } else if (element instanceof Include) {
      return SysmlConstants.INCLUDE;
    } else if (element instanceof InterfaceRealization) {
      return SysmlConstants.INTERFACE_REALIZATION;
    } else if (element instanceof InformationFlow) {
      return SysmlConstants.INFORMATION_FLOW;
    } else if (SysML.isItemFlow(element)) {
      return SysmlConstants.ITEM_FLOW;
    } else if (element instanceof Message) {
      return SysmlConstants.MESSAGE;
    } else if (element instanceof ObjectFlow) {
      return SysmlConstants.OBJECT_FLOW;
    } else if (element instanceof PackageImport) {
      return SysmlConstants.PACKAGE_IMPORT;
    } else if (SysML.isSatisfy(element)) {
      return SysmlConstants.SATISFY;
    } else if (SysML.isTrace(element)) {
      return SysmlConstants.TRACE;
    } else if (element instanceof Transition) {
      return SysmlConstants.TRANSITION;
    } else if (element instanceof Usage) {
      return SysmlConstants.USAGE;
    } else if (SysML.isVerify(element)) {
      return SysmlConstants.VERIFY;

      // Elements that are supertype must be checked after their subtypes
    } else if (element instanceof Association) {
      com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd =
          ModelHelper.getFirstMemberEnd((Association) element);
      com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd =
          ModelHelper.getSecondMemberEnd((Association) element);
      if (firstMemberEnd.getAggregation() == AggregationKindEnum.SHARED
          || secondMemberEnd.getAggregation() == AggregationKindEnum.SHARED) {
        return SysmlConstants.AGGREGATION;
      } else if (firstMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE
          || secondMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE) {
        return SysmlConstants.COMPOSITION;
      } else {
        return SysmlConstants.ASSOCIATION;
      }
    } else if (element instanceof Dependency) {
      return SysmlConstants.DEPENDENCY;
    }

    return null;
  }

  @CheckForNull
  public static String getDiagramType(Element element) {
    if (!(element instanceof Diagram)) {
      return null;
    }

    DiagramPresentationElement presentationDiagram =
        Application.getInstance().getProject().getDiagram((Diagram) element);

    if (presentationDiagram == null) {
      Logger.log(String.format(
          "Diagram type not found. Could not find DiagramPresentationElement for diagram %s.",
          MtipUtils.getId(element)));
      return null;
    }

    DiagramType diagramType = presentationDiagram.getDiagramType();

    if (diagramType == null) {
      return SysmlConstants.CUSTOM_DIAGRAM;
    }

    return AbstractDiagram.getMetamodelConstant(diagramType.getType());
  }

  public static String getCameoElementType(Element element) {
    if (element instanceof Diagram) {
      DiagramPresentationElement presentationDiagram =
          Application.getInstance().getProject().getDiagram((Diagram) element);

      if (presentationDiagram == null) {
        return element.getHumanType();
      }

      return presentationDiagram.getDiagramType().getType();
    }

    return element.getHumanType();
  }

  public static String getUafElementType(Element element) {
    List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element).stream()
        .filter(x -> UAF.isUafProfile(StereotypesHelper.getProfileForStereotype(x)))
        .collect(Collectors.toList());
    
    if (stereotypes.size() == 0) {
      return null;
    }

    if (stereotypes.size() == 1) {
      return CameoUtils.getElementName(stereotypes.get(0));
    }

    Logger.logMultipleUafStereotypes(stereotypes, element);
    Logger.log(String.format(
        "UAF type nout found. Ambiguous type for element with id %s. Multiple UAF stereotypes found",
        MtipUtils.getId(element)));
    return null;
  }

  public static String getPackageType(Element pkg) {
    if (CameoUtils.isModel(pkg)) {
      return SysmlConstants.MODEL;
    }

    if (CameoUtils.isProfile(pkg)) {
      return SysmlConstants.PROFILE;
    }

    return SysmlConstants.PACKAGE;
  }

  public static boolean isRelationship(Element element) {
    if (element instanceof ActivityEdge 
        || element instanceof InformationFlow
        || element instanceof Message 
        || element instanceof Relationship
        || element instanceof Transition) {
      return true;
    }

    return false;
  }

  public static boolean isUafModel() {
    return ProjectUtilities.getAllAttachedProjects(Application.getInstance().getProject())
        .stream()
        .map(IAttachedProject::getName)
        .collect(Collectors.toSet())
        .contains(UAF.getProfileModelName());
  }
  
  public static String getId(Element element) {
    if (Config.isLocalId()) {
      return element.getLocalID();
    }
    
    return element.getID();
  }
 
  public static String utcNow() {
    return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
  }
}
