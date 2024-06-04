package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class CallEvent extends CommonElement {

  public CallEvent(String name, String EAID) {
      super(name, EAID);
      this.creationType = XmlTagConstants.ELEMENTSFACTORY;
      this.metamodelConstant = SysmlConstants.CALL_EVENT;
      this.xmlConstant = XmlTagConstants.CALL_EVENT;
      this.element = f.createChangeEventInstance();
  }
}
