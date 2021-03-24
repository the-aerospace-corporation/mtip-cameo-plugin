package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Dependency extends CommonDirectedRelationship {

	public Dependency(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DEPENDENCY;
		this.xmlConstant = XmlTagConstants.DEPENDENCY;
		this.sysmlElement = f.createDependencyInstance();
	}
}
