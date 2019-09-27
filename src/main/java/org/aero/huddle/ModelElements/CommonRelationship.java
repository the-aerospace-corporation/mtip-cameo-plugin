package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class CommonRelationship {
	protected String name;
	protected String EAID;
	
	public CommonRelationship(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
	}
	
	public abstract Element createElement(Project project, Element owner, Element client, Element supplier);
	
	public abstract Element createElement(Project project, Element owner, Element client, Element supplier, com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype stereotype);
}
