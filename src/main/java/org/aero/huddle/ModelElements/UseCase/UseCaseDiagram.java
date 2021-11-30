package org.aero.huddle.ModelElements.UseCase;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class UseCaseDiagram  extends AbstractDiagram{

	public UseCaseDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_USE_CASE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.USECASEDIAGRAM;
		 this.allowableElements = SysmlConstants.UC_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_USE_CASE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.USECASEDIAGRAM;
	}
	
}
