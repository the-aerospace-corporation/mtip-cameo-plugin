/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonRelationship;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class InterfaceRealization extends CommonRelationship {

	public InterfaceRealization(String name, String EAID) {
		super(name, EAID);
	}
	
	@Override 
	public org.w3c.dom.Element writeToXML(Element element) {
		return null;
	}
}
