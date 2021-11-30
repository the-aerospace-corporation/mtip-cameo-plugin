package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class ClassDiagram extends AbstractDiagram {

	public ClassDiagram(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.CLASSDIAGRAM;
		 this.xmlConstant = XmlTagConstants.CLASSDIAGRAM;
		 this.allowableElements = SysmlConstants.CLASSDIAGRAM_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return 	DiagramTypeConstants.UML_PROFILE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.PROFILEDIAGRAM;
	}

}
