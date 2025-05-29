## MTIP-Cameo Changelog

### 2022x v1.0.0.b004
* Added UML metamodel package, constants, etc
* Added support for Artifact elements

### 2022x v1.0.0.b003
* Added <metadata> tag containing MTIP Version, Cameo Version, and timestamp of export
* Added explicit support for ReferenceProperties
* Added support for references of associations by Property and subclasses
* Added support for activity relationship in all ActivityNodes

### 2022x v1.0.0.b002
* Fixed NPE on import when referenced elements from underlying Cameo profiles and libraries not included in XML
* Fixed Metaclass import logging
* Fixed exception on import of Profile Diagrams
* Removed superfluous tagged value logging on import

### 2022x v1.0.0.b001
* Initial version of MTIP-Cameo 2022x with all 2021x improvements