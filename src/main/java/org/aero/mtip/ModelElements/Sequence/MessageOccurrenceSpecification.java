/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class MessageOccurrenceSpecification extends CommonElement {

	public MessageOccurrenceSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.MESSAGEOCCURRENCESPECIFICATION;
		this.xmlConstant = XmlTagConstants.MESSAGEOCCURRENCESPECIFICATION;
		this.sysmlElement = f.createMessageOccurrenceSpecificationInstance();
	}
	
//	@Override
//	public void setOwner(Project project, Element owner) {
//		
//	}
}
