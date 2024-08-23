package org.aero.mtip.metamodel.sysml.statemachine;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class AnyReceiveEvent extends CommonElement {

  public AnyReceiveEvent(String name, String EAID) {
      super(name, EAID);
      this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
      this.metamodelConstant = SysmlConstants.ANY_RECEIVE_EVENT;
      this.xmlConstant = XmlTagConstants.ANY_RECEIVE_EVENT;
      this.element = f.createChangeEventInstance();
  }
}
