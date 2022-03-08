/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ExportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.Duration;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationInterval;

public class DurationConstraint extends CommonElement {

	public DurationConstraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DURATIONCONSTRAINT;
		this.xmlConstant = XmlTagConstants.DURATIONCONSTRAINT;
		this.sysmlElement = f.createDurationConstraintInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		

		return sysmlElement;
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		DurationInterval di = dc.getSpecification();
		if(di != null) {
			Duration minDuration = di.getMin();
			if(minDuration != null) {
				LiteralString min =	(LiteralString) minDuration.getExpr();
				if(min != null) {
					org.w3c.dom.Element minTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_MIN, min.getValue());
					attributes.appendChild(minTag);
					CameoUtils.logGUI("Min found: " + min);
					ExportLog.log("Min found: " + min);
				}
			}
			Duration maxDuration = di.getMax();
			if(maxDuration != null) {
				LiteralString max = (LiteralString) maxDuration.getExpr();
				if(max != null) {
					org.w3c.dom.Element maxTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_MAX, max.getValue());
					attributes.appendChild(maxTag);
					CameoUtils.logGUI("Max found: " + max);
					ExportLog.log("Max found: " + max);
				}
			}
		}
		return data;
	}
}
