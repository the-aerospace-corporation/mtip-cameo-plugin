package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Transition extends CommonRelationship {
	public Transition(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element source, Element target) {
		return null;
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, Stereotype stereotype) {
		return null;
	}
}
