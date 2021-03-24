package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Composition extends CommonRelationship{
	public Composition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.COMPOSITION;
		this.xmlConstant = XmlTagConstants.COMPOSITION;
		this.sysmlElement = f.createAssociationInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		Association association = (Association) super.createElement(project, owner, client, supplier, xmlElement);
		
		//To create composition, the property of the end of the association relationship must be set to the composite aggregation enumeration
		if(association != null) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(association);
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(association);
			ModelHelper.setNavigable(firstMemberEnd, true);
			ModelHelper.setNavigable(secondMemberEnd, false);
			firstMemberEnd.setAggregation(AggregationKindEnum.COMPOSITE);
		}
		

		return association;
	}
}
