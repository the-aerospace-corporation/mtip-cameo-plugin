package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;
//import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class ProfileDiagram  extends AbstractDiagram{

	public ProfileDiagram(String name, String EAID) {
		 super(name, EAID);
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
