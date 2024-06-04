package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class AnyReceiveEvent extends CommonElement {

  public AnyReceiveEvent(String name, String EAID) {
      super(name, EAID);
      this.creationType = XmlTagConstants.ELEMENTSFACTORY;
      this.metamodelConstant = SysmlConstants.ANY_RECEIVE_EVENT;
      this.xmlConstant = XmlTagConstants.ANY_RECEIVE_EVENT;
      this.element = f.createChangeEventInstance();
  }
}
