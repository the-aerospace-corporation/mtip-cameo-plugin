/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.metamodel.sysml.requirements;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonDirectedRelationship;
import org.aero.mtip.profiles.SysML;

public class Copy extends CommonDirectedRelationship {

  public Copy(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
    this.metamodelConstant = SysmlConstants.COPY;
    this.xmlConstant = XmlTagConstants.COPY;
    this.element = f.createAbstractionInstance();
    this.creationStereotype = SysML.getCopyStereotype();
  }
}
