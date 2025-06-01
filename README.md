# Modeling Tool Integration Plugin for Cameo Systems Modeler (MTIP-Cameo)

## Description

This project is the source code for a Java plugin written for MagicDraw (MD), Cameo Systems Modeler (CSM), and Cameo Enterprise Architect (CEA), a Dassault Systemes software. This plugin imports and exports SysML models formatted as HUDS XML. The current XML Metadata Interchange (XMI) specification is based on UML and is insufficient for transferring SysML models between commercial tools. This plugin allows exporting and importing of entire models or a portion of a model to a HUDS XML format. A partner plugin developed for Sparx System's Enterprise Architect can import and export this same format allowing for round-trip transferring of models.

## Users

### Available Versions

Development is currently active on the 2022x version. Please see below for available versions and status

| Version | Open Source Available | In Development | Latest Release |
| -------- | ------- | -------- | -------- |
| 19.0 | Yes | No | [19.0 v1.0.9](https://github.com/Open-MBEE/mtip-cameo/releases/tag/v1.0.9)|
| 2021x | Yes | No | [2021x v1.0.0]()|
| 2022x | Yes | Yes | [2022x v1.0.0]()|
| 2024x | No | No | |

### Installation
 
To install the plugin, please follow MagicDraw's documentation for installing plugins for the respective versions of the tool: 
* [(2022x and Earlier) Installing Plugins](https://docs.nomagic.com/spaces/IL2022xR2/pages/127979398/Installing+plugins)
* [(2024x) Installing plugins](https://docs.nomagic.com/spaces/IL2024x/pages/136726193/Installing+plugins)

### Using MTIP-Cameo

#### Exporting a Model

* Open MD/CSM/CEA and the model you'd like to export
* Select export from the drop down under the MTIP toolbar
* Select a file location and name to save (e.g. <directory>\<file_name>.xml)
* The plugin will show a dialog stating export has completed.

#### Importing a Model

* Open the model file you would like to import your model into. If you would like a new model, open a new project.
* Select import from the drop down under the Huddle toolbar
* Select the HUDS XML file to import
* The plugin will open a dialog stating import has completed.

### Log Files

On import and export, a log of any errors that occur will be written to /Documents/MTIP-Cameo Logs. This captures elements that were not imported or exported as well as other errors that may occur.

## Developers

### Dependencies

* Dassault's OpenAPI distributed with MagicDraw (MD), Cameo Systems Modeler (CSM), or Cameo Enterprise Architect (CEA)

### Setting up Environment for Development

It is recommended to follow Dassault's guide for setting up integration with IDEs: 
  * [(2022x) Developing Plugins Using IDE](https://docs.nomagic.com/spaces/MD2022xR2/pages/122990701/Developing+plugins+using+IDE)
  * [(2024x) Developing Plugins Using IDE](https://docs.nomagic.com/spaces/MD2024x/pages/136715084/Developing+plugins+using+IDE)

You may also need to manually add references to the following JAR files in addition to the steps completed in Dassault's guide:
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

## Authors

Trent Severson, Aerospace Corporation  
Joel Thomas, Aerospace Corporation  
Joshua Sasaki, Aerospace Corporation

## Contact

oss@aero.org

## Version History

Please see the `CHANGELOG.md`.

## License

This project is licensed under the Apache Commons License 2.0 - see the LICENSE.html file for details
