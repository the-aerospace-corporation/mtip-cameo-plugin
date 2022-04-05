# HUDS XML Format for SysML Models

## Description

For SysML models, each model element, relationship, and diagram are represented as a <data> tag in the XML. The XML is a flat file with the entirety of these data tags at the same level. 

## Overall XML Structure
```
<?xml version="1.0" encoding="UTF-8"?>
<packet>
    <data>...</data>
    <data>...</data>
    ...
</packet>
```
Each data tag is required to have the following child tags:
```
<id>
<type>
<relationships>
<attributes>
```
### id Tag
The id tag should have the attribute _dtype="dict" and have at least one child tag naming the tool of origin with the string identifier as the value of the tag. If creating your own IDs it is recommended to use a universally unique identifier.

### type Tag
For SysML models, the type tag have the attribute _dtype="str" and use one of the supported SysML types outlined on this page (ADD LINK). See below for examples of an element, relationship, and diagram.

### relationship Tag
Every element, relationship, and diagram's <relationships> tag should have a <hasParent> child tag. This tag contains the unique identifier (from the id tag) of the parent element. This hasParent relationship is used to build the Containment Tree (Cameo)/Project Browser(EA) tree structure of the model. If a data tag does NOT have a hasParent tag, the SysML tools will attempt to place the element as a child of the top level Model element that is created in every SysML model. Some elements must have certain elements as their parent element so this is not recommended. 

### attributes Tag
Captures any element data not captured in the previous first-level tags. This includes: element name, stereotypes, and any other data associated with that element.

## Example Data Tags

### Example Element
```
<data>
    <relationships _dtype="dict">
        <hasParent _dtype="dict">
            <type _dtype="str">PackageImpl</type>
            <id _dtype="str">_19_0_2_c04024e_1570821496721_507278_42563</id>
            <relationship_metadata _dtype="dict"/>
        </hasParent>
    </relationships>
    <id _dtype="dict">
        <cameo _dtype="str">_19_0_2_c04024e_1570821520787_211093_42600</cameo>
    </id>
    <attributes>
        <attribute _dtype="dict" key="name">
            <attribute _dtype="str"/>Block</attribute>
        </attribute>
        <attribute _dtype="dict" key="stereotype">
            <attribute _dtype="dict" key="stereotypeName">
                <attribute _dtype="str"/>Block</attribute>
            </attribute>
            <attribute _dtype="dict" key="stereotypeId">
                <attribute _dtype="str"/>_11_5EAPbeta_be00301_1147424179914_458922_958</attribute>
            </attribute>
            <attribute _dtype="dict" key="profileName">
                <attribute _dtype="str"/>SysML</attribute>
            </attribute>
            <attribute _dtype="dict" key="profileId">
                <attribute _dtype="str"/>_11_5EAPbeta_be00301_1147434586638_637562_1900</attribute>
            </attribute>
        </attribute>
    </attributes>
    <type _dtype="str">sysml.Block</type>
</data>
```

### Example Relationship
```
<data>
    <relationships _dtype="dict">
        <hasParent _dtype="dict">
            <id _dtype="str">_19_0_2_c04024e_1570821496721_507278_42563</id>
        </hasParent>
        <supplier _dtype="dict">
            <id _dtype="str">_19_0_2_c04024e_1570821551686_752788_42848</id>
        </supplier>
        <client _dtype="dict">
            <id _dtype="str">_19_0_2_c04024e_1570821556643_401869_42893</id>
        </client>
    </relationships>
    <id _dtype="dict">
        <cameo _dtype="str">_19_0_2_c04024e_1570821749809_472236_43347</cameo>
    </id>
    <attributes>
        <attribute _dtype="str" key="name">Directed Aggregation</attribute>
    </attributes>
    <type _dtype="str">sysml.Aggregation</type>
</data>
```

### Example Diagram
```
<data>
     <relationships _dtype="dict">
         <hasParent _dtype="dict">
             <type _dtype="str">PackageImpl</type>
             <id _dtype="str">_19_0_2_c04024e_1570821496721_507278_42563</id>
             <relationship_metadata _dtype="dict"/>
         </hasParent>
         <element _dtype="list">
             <element _dtype="list" key="0">
                 <id _dtype="str">_19_0_2_c04024e_1570821520787_211093_42600</id>
                 <type _dtype="str">sysml.Block</type>
                 <relationship_metadata _dtype="dict">
                     <top _dtype="int">56</top>
                     <bottom _dtype="int">103</bottom>
                     <left _dtype="int">49</left>
                     <right _dtype="int">129</right>
                 </relationship_metadata>
             </element>
             ...
         </element>
         <diagramConnector _dtype="list">
             <diagramConnector _dtype="list" key="0">
                 <relationship_metadata _dtype="dict"/>
                 <id _dtype="str">_19_0_2_c04024e_1570821749809_472236_43347</id>
                 <type _dtype="str">Association</type>
             </diagramConnector>
             ...
         </diagramConnector>
     </relationships>
     <id _dtype="dict">
         <cameo _dtype="str">_19_0_2_c04024e_1570821504542_497549_42564</cameo>
     </id>
     <attributes>
         <attribute _dtype="dict" key="name">
             <attribute _dtype="str"/>Block Definition Diagram</attribute>
         <attribute _dtype="dict" key="stereotype">
             <attribute _dtype="dict"/>
             <attribute _dtype="dict" key="stereotypeName">
                 <attribute _dtype="str"/>DiagramInfo</attribute>
             <attribute _dtype="dict" key="stereotypeId">
                 <attribute _dtype="str"/>_9_0_be00301_1108044380615_150487_0</attribute>
             <attribute _dtype="dict" key="profileName">
                 <attribute _dtype="str"/>MagicDraw Profile</attribute>
             <attribute _dtype="dict" key="profileId">
                 <attribute _dtype="str"/>_be00301_1073394351331_445580_2</attribute>
         </attribute>
         <attribute _dtype="dict" key="displayAs">
             <attribute _dtype="str"/>Diagram</attribute>
     </attributes>
     <type _dtype="str">sysml.BlockDefinitionDiagram</type>
 </data>
```