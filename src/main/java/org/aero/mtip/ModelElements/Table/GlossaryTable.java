/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Table;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class GlossaryTable extends AbstractTable {

	public GlossaryTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.GLOSSARY_TABLE;
		this.xmlConstant = XmlTagConstants.GLOSSARY_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_GLOSSARY_TABLE;
	}
}
