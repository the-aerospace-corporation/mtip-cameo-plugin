package org.aero.mtip.metamodel.dodaf.stdv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.table.AbstractTable;

public class StdV2 extends AbstractTable {

	public StdV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.STDV2;
		this.xmlConstant = XmlTagConstants.STDV2;
		this.cameoConstant = "StdV-1 Standards Profile";
	}
}
