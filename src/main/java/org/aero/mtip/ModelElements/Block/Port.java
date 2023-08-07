/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.ImportLog;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;

public class Port extends CommonElement {
	public Port(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PORT;
		this.xmlConstant = XmlTagConstants.PORT;
		this.sysmlElement = f.createPortInstance();
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		try {
			if (owner != null) {
				Element newOwner = null;
				String ownerType = owner.getHumanType();
				if(ownerType.equals("Port")) {
					// Check if Type property is set if it is, get the block already typing the port and set as owner of new port
					if(isTyped(owner)) {
						TypedElement ownerTyped = (TypedElement)owner;
						Type type = ownerTyped.getType();
						newOwner = (Element)type;
						sysmlElement.setOwner(newOwner);
					} else {
						newOwner = createNestedPorts(project, owner);
						TypedElement ownerTyped = (TypedElement)owner;
						ownerTyped.setType((Type)newOwner);
						sysmlElement.setOwner(newOwner);
					}
				} else {
					sysmlElement.setOwner(owner);
				}
			} else {
				sysmlElement.setOwner(project.getPrimaryModel());
			}
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". Element could not be placed in model.";
			ImportLog.log(logMessage);
			sysmlElement.dispose();
		}
	}
}
