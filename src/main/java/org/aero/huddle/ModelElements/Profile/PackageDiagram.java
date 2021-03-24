package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;
//import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class PackageDiagram  extends AbstractDiagram{

	public PackageDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_PACKAGE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PACKAGEDIAGRAM;
	}
	
	@Override
	public String getSysmlConstant() {
		return 	DiagramTypeConstants.UML_PACKAGE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.PACKAGEDIAGRAM;
	}
}
