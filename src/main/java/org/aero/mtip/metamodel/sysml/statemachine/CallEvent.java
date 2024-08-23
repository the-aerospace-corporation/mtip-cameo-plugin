package org.aero.mtip.metamodel.sysml.statemachine;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class CallEvent extends CommonElement {

  public CallEvent(String name, String EAID) {
      super(name, EAID);
      this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
      this.metamodelConstant = SysmlConstants.CALL_EVENT;
      this.xmlConstant = XmlTagConstants.CALL_EVENT;
      this.element = f.createChangeEventInstance();
  }
}
