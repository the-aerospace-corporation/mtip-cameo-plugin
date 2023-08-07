package org.aero.mtip.dodaf.stdv;

import org.aero.mtip.ModelElements.Table.AbstractTable;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StdV2 extends AbstractTable {

	public StdV2(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.STDV2;
		this.xmlConstant = XmlTagConstants.STDV2;
		this.cameoConstant = "StdV-1 Standards Profile";
	}
}
