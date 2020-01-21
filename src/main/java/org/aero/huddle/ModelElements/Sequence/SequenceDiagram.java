package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class SequenceDiagram  extends AbstractDiagram{

	public SequenceDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.SEQUENCEDIAGRAM;
	}
}
