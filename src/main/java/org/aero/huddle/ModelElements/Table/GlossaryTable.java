package org.aero.huddle.ModelElements.Table;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class GlossaryTable extends AbstractTable {

	public GlossaryTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.GLOSSARY_TABLE;
		this.xmlConstant = XmlTagConstants.GLOSSARY_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_GLOSSARY_TABLE;
	}
}
