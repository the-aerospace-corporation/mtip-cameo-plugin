# Modeling Tool Integration Plugin for Cameo Systems Modeler (MTIP-Cameo)

## Description

This project is the source code for a Java plug-in written for Cameo Systems Modeler (CSM)and MagicDraw (MD), a Dassault Systemes software. This plug-in imports and exports SysML models formatted as HUDS XML. The current XML Metadata Interchange (XMI) specification is based on UML and is insufficient for transferring SysML models between commercial tools. This plug-in allows exporting and importing of entire models or a portion of a model to a HUDS XML format. A partner plug-in developed for Sparx System's Enterprise Architect can import and export this same format allowing for round-trip transferring of models.

## Getting Started

### Dependencies

* org.apache.commons
* MagicDraw's  OpenAPI distributed with MD or CSM
* Cameo Systems Modeler v19.0 is required to use the built plug-in.

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

## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributors names and contact info

Trent Severson, Aerospace Corporation

## Version History
* 1.0
    * Initial Release

## License

This project is licensed under the Apache Commons License 2.0 - see the LICENSE.html file for details

## Acknowledgments
