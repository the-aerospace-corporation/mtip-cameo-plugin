# Validation Requirements for Importing Models
Cameo enforces the SysML and UML standard for namespaces very strictly while EA does not. This leads to many errors when assigning the parent/owner of an element on import. The cameo import will attempt to assign the parent as outlined in the XML. If that is an invalid parent-child relationship in Cameo, the import will attempt to place the element under the main model package. If that is still an invalid parent-child relationship the element will not be imported.

The following rules should be followed by EA models to allow for a successful import into Cameo.

1. Cameo enforces designated model elements as default namespaces. The following diagrams must be a child of an element of the respective element types.
  1.1 Activtiy Diagram - activity
  1.2 Block Definition Diagram - block, package, constraint block ,or activity
  1.3 Internal Block Diagram - block or constraint block
  1.4 Package Diagram - package, model, modelLibrary, or profile
  1.5 Parametric Diagram - block or constraint block
  1.6 Requirement Diagram - package, requirement, modelLibrary, or model
  1.7 Sequence Diagram - interaction
  1.8 State Machine Diagram - state machine
  1.9 Use Case Diagram - package, block, model, or modelLibrary 
2. Elements which appear on the UML behavior diagrams (Activity, Sequence, and State Machine) must be children elements (ownedElements) of the element who provides the namespace for the diagram on which these elements appear. Below lists the parent, namespace element and the elements which are required to be children of that element when importing into Cameo.
  2.1 Activity Diagrams - All activity nodes and activity edges must have an activity as its parent
    2.1.1 Activity Nodes: AcceptEventAction, Action, ActivityFinalNode, ActivityParameterNode, ActivityPartition, CallBehaviorAction, CallOperationAction, CentralBufferNode, ConditionalNode, CreateObjectAction, DataStoreNode, DeicisionNode, DestroyObjectACtion, FlowFinalNode, ForkNode, InitialNode, InputPin, JoinNode, LoopNode, MergeNode, OpaqueAction, SendSignalAction, StructuredActivityNode
    2.1.2 Activity Edges: Control Flow, Object Flow
  2.2 Sequence Diagrams - Any element that is a subclass of InteractionFragment or Interaction per the UML Specification
  2.3 State Machine Diagrams - Any element that has state as a superclass and states themselves must be a child of the state machine whose diagram on which they appear.
3. Internal Block Diagrams in Cameo have strict rules including:
  3.1 Connectors must have a parent element (be a child of) the block (or constraint block) which the IBD represents. 
  3.2 Connectors must connect to ports of blocks. Cameo does not allow connectors between ports defined on blocks.
