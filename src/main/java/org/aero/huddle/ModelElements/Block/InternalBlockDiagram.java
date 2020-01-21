package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class InternalBlockDiagram  extends AbstractDiagram{

	public InternalBlockDiagram(String name, String EAID) {
		 super(name, EAID);
	}

	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_INTERNAL_BLOCK_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.INTERNALBLOCKDIAGRAM;
	}
}
