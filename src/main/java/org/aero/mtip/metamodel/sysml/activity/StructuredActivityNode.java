package org.aero.mtip.metamodel.sysml.activity;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StructuredActivityNode extends ActivityNode {

  public StructuredActivityNode(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
    this.metamodelConstant = SysmlConstants.STRUCTURED_ACTIVITY_NODE;
    this.xmlConstant = XmlTagConstants.STRUCTURED_ACTIVITY_NODE;
    this.element = f.createStructuredActivityNodeInstance();
  }
}
