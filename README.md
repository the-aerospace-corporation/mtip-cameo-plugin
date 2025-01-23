# Modeling Tool Integration Plugin for Cameo Systems Modeler (MTIP-Cameo)

## Description

This project is the source code for a Java plug-in written for Cameo Systems Modeler (CSM)and MagicDraw (MD), a Dassault Systemes software. This plug-in imports and exports SysML models formatted as HUDS XML. The current XML Metadata Interchange (XMI) specification is based on UML and is insufficient for transferring SysML models between commercial tools. This plug-in allows exporting and importing of entire models or a portion of a model to a HUDS XML format. A partner plug-in developed for Sparx System's Enterprise Architect can import and export this same format allowing for round-trip transferring of models.

## Getting Started

### Dependencies

* MagicDraw's OpenAPI distributed with MD or CSM
* Cameo Systems Modeler v19.0 is required to use the built plug-in. **The current version of this plugin is incompatible with 2021x.**

#### Setting up Classpath for Development
Please follow the directions provided by NoMagic at https://docs.nomagic.com/display/MD190/Development+in+Eclipse to set the classpath for MagicDraw's OpenAPI bundled with your version of MD or CSM.

You may also need to manually add references to the following JAR files:
* <cameo install path>\plugins\com.nomagic.requirements\text.jar
* <cameo install path>\plugins\com.nomagic.requirements\requirements_api.jar
* <cameo install path>\plugins\com.nomagic.requirements\requirements.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.sysml\sysml.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.sysml\sysml_api.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.glossary\glossary.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.glossary\glossary_api.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.diagramtable\diagramtable_api.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.diagramtable\diagramtable.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.dependencymatrix\dependencymatrix_api.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.dependencymatrix\dependencymatrix.jar

* <cameo install path>\plugins\com.nomagic.magicdraw.relationshipmap\relationshipmap.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.relationshipmap\relationshipmap_api.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.migration\migration.jar
* <cameo install path>\plugins\com.nomagic.magicdraw.migration\migration_api.jar

### Installing

* It is recommended to follow MagicDraw's guide for setting up integration with IDEs located here: https://docs.nomagic.com/display/MD190/Developing+plugins+using+IDE 
* To install the built plug-in, please follow MagicDraw's documentation for installing plug-ins found here: https://docs.nomagic.com/display/NMDOC/Installing+plugins

### Executing program

To export a model
*  Open MD/CSM and the model you'd like to export
* Select export from the drop down under the Huddle toolbar
* Select a file location and name to save (e.g. <your_path>\my_projet.xml)
* Plug-in will open a dialog stating export has completed.

To import a model
* Open the model file you would like to import your model into. If you would like a new model, open a new project.
* Select import from the drop down under the Huddle toolbar
* Plug-in will open a dialog stating import has completed.

### Log Files
On import and export, a log of any errors that occur will be written to /Documents/Cameo Logs. This captures elements that were not imported or exported correctly as well as other errors that may occur.

## Authors

Trent Severson, Aerospace Corporation
oss@aero.org

## Version History

* 1.0.6
    * Added Cameo global id on export. Import defaults to using this id when available.
* 1.0.5
    * Fixed support for views, viewpoints, and stakeholders when exchanging models with Sparx EA
    * Added support for PackageImport relationships while filtering out underlying libraries
    * Added clientMultiplicity and supplierMultiplicity attrbiutes to associations
    * Addressed placement of part properties on diagrams
    * Various fixes for NPEs in rare cases
* 1.0.4
    * Updates to fix NPEs
* 1.0.3
    * Requirement text bug fix and basic html filtering.
* 1.0.2
    * Additional error checking for common issues with EA imports.
* 1.0.1
    * Added checks for null pointer exception for extension class when failing to created elements due to invalid parent-child relationships coming from original EA model.
* 1.0.0
    * Initial Release

## License

This project is licensed under the Apache Commons License 2.0 - see the LICENSE.html file for details
