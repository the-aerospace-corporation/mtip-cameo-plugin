## MTIP-Cameo Changelog

### 2022x v1.0.0
* Fixed bug where UseCase type not identified.
* Fixed bug where Parameter type not identified

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

### 2021x

### 19.0 v1.0.6
* Added Cameo global id on export. Import defaults to using this id when available.

### 19.0 v1.0.5
* Fixed support for views, viewpoints, and stakeholders when exchanging models with Sparx EA
* Added support for PackageImport relationships while filtering out underlying libraries
* Added clientMultiplicity and supplierMultiplicity attrbiutes to associations
* Addressed placement of part properties on diagrams
* Various fixes for NPEs in rare cases

### 19.0 v1.0.4
* Updates to fix NPEs

### 19.0 v1.0.3
* Requirement text bug fix and basic html filtering.

### 19.0 v1.0.2
* Additional error checking for common issues with EA imports.

### 19.0 v1.0.1
* Added checks for null pointer exception for extension class when failing to created elements due to invalid parent-child relationships coming from original EA model.

### 19.0 v1.0.0
* Initial Release