/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ChangeEvent extends CommonElement {
	public ChangeEvent(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.CHANGE_EVENT;
		this.xmlConstant = XmlTagConstants.CHANGE_EVENT;
		this.element = f.createChangeEventInstance();
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
	  org.w3c.dom.Element data = super.writeToXML(element);        
      org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
      
      writeChangeExpression(attributes);
      
      return data;
	}
	
	public void writeChangeExpression(org.w3c.dom.Element attributes) {
	    // TODO
	}
}
