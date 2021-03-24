package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class BlockDefinitionDiagram  extends AbstractDiagram {

	public BlockDefinitionDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_BLOCK_DEFINITION_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.BLOCKDEFINITIONDIAGRAM;
	}
	
	@Override
	public  String getSysmlConstant() {
		return SysMLConstants.SYSML_BLOCK_DEFINITION_DIAGRAM;
	}
	
	@Override
	public  String getDiagramType() {
		return XmlTagConstants.BLOCKDEFINITIONDIAGRAM;
	}

}
