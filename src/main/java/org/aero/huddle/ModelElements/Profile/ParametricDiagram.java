package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class ParametricDiagram extends AbstractDiagram {

	public ParametricDiagram(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_PARAMETERIC_DIAGRAM;
	}

	@Override
	public String getDiagramType() {
		return XmlTagConstants.PARAMETRICDIAGRAM;
	}
}
