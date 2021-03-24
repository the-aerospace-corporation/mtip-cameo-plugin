package org.aero.huddle.ModelElements.UseCase;

import org.aero.huddle.ModelElements.CommonDirectedRelationship;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Include extends CommonDirectedRelationship {

	public Include(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INCLUDE;
		this.xmlConstant = XmlTagConstants.INCLUDE;
		this.sysmlElement = f.createIncludeInstance();
	}
}
