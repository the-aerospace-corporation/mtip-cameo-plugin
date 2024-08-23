package org.aero.mtip.metamodel.dodaf.stdv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.table.AbstractTable;

public class StdV1 extends AbstractTable {

	public StdV1(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.STDV1;
		this.xmlConstant = XmlTagConstants.STDV1;
		this.cameoConstant = "StdV-1 Standards Profile";
	}
}
