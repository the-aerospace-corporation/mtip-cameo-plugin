/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Matrix;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class DeriveRequirementMatrix extends AbstractMatrix {
	public DeriveRequirementMatrix(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = SysmlConstants.DERIVE_REQUIREMENT_MATRIX;
		this.xmlConstant = XmlTagConstants.DERIVE_REQUIREMENT_MATRIX;
		this.cameoConstant = SysmlConstants.CAMEO_DERIVE_REQUIREMENT_MATRIX;
	}
		
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		return super.createElement(project, owner, xmlElement);
	}
}
