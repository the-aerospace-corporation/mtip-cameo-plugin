package org.aero.huddle.ModelElements.Matrix;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class AllocationMatrix extends AbstractMatrix {
	public AllocationMatrix(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.ALLOCATION_MATRIX;
		this.xmlConstant = XmlTagConstants.ALLOCATION_MATRIX;
		this.cameoConstant = SysmlConstants.CAMEO_ALLOCATION_MATRIX;
	}
		
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		return super.createElement(project, owner, xmlElement);
	}
	
	@Override
	public String getSysmlConstant() {
		return "Allocation Matrix";
	}

	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}
}
