/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.metamodel.sysml.block;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;
import org.aero.mtip.profiles.MDCustomizationForSysML;

public class ReferenceProperty extends Property {
  public ReferenceProperty(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
    this.metamodelConstant = SysmlConstants.REFERENCE_PROPERTY;
    this.xmlConstant = XmlTagConstants.REFERENCE_PROPERTY;
    this.element = f.createPropertyInstance();
    this.creationStereotype = MDCustomizationForSysML.getReferencePropertyStereotype();
  }
}
