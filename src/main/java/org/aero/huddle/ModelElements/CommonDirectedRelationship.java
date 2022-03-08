/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements;

import java.util.Collection;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DirectedRelationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class CommonDirectedRelationship extends CommonRelationship {

	public CommonDirectedRelationship(String name, String EAID) {
		super(name, EAID);
	}
	
	public void getSupplier(Element element) {
		Collection<Element> sources = ((DirectedRelationship)element).getSource();
		this.supplier = sources.iterator().next();
	}
	
	public void getClient(Element element) {
		Collection<Element> targets = ((DirectedRelationship)element).getTarget();
		this.client = targets.iterator().next();
	}

}
