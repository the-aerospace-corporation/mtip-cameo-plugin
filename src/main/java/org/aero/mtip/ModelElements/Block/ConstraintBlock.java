/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.XmlTagConstants;

public class ConstraintBlock extends CommonElement {
  public ConstraintBlock(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
    this.metamodelConstant = SysmlConstants.CONSTRAINT_BLOCK;
    this.xmlConstant = XmlTagConstants.CONSTRAINT_BLOCK;
    this.creationStereotype = SysML.getConstraintBlockStereotype();
  }
}
