package org.aero.huddle.ModelElements.Matrix;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class SatisfyRequirementMatrix extends AbstractMatrix {
	public SatisfyRequirementMatrix(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.SATISFY_REQUIREMENT_MATRIX;
		this.xmlConstant = XmlTagConstants.SATISFY_REQUIREMENT_MATRIX;
		this.cameoConstant = SysmlConstants.CAMEO_SATISFY_REQUIREMENT_MATRIX;
	}
		
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		return super.createElement(project, owner, xmlElement);
	}
}
