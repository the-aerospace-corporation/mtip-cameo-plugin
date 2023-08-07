package org.aero.mtip.dodaf.stdv;

import org.aero.mtip.ModelElements.Table.AbstractTable;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StdV1 extends AbstractTable {

	public StdV1(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.STDV1;
		this.xmlConstant = XmlTagConstants.STDV1;
		this.cameoConstant = "StdV-1 Standards Profile";
	}
}
