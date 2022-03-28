/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Extension extends CommonRelationship {

	public Extension(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.EXTENSION;
		this.xmlConstant = XmlTagConstants.EXTENSION;
		this.sysmlElement = f.createExtensionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		
		com.nomagic.uml2.ext.magicdraw.mdprofiles.Extension extension = (com.nomagic.uml2.ext.magicdraw.mdprofiles.Extension)sysmlElement;
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(extension);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(extension);

		firstMemberEnd.setAggregation(AggregationKindEnum.NONE);
		ModelHelper.setNavigable(firstMemberEnd, true);
		firstMemberEnd.setOwner(client);
		
		secondMemberEnd.setAggregation(AggregationKindEnum.COMPOSITE);
		ModelHelper.setNavigable(secondMemberEnd, true);
		secondMemberEnd.setOwner(extension);

		return extension;
	}
}
