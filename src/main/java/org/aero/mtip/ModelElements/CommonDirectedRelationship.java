/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.util.Collection;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DirectedRelationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class CommonDirectedRelationship extends CommonRelationship {

	public CommonDirectedRelationship(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element getSupplier(Element element) {
		Collection<Element> sources = ((DirectedRelationship)element).getSource();
		return sources.iterator().next();
	}
	
	public Element getClient(Element element) {
		Collection<Element> targets = ((DirectedRelationship)element).getTarget();
		return targets.iterator().next();
	}
	
	@Override
	public void setSupplier(Element element) {
		DirectedRelationship directedRelationship = (DirectedRelationship)element;
		if (directedRelationship.getSource().iterator().hasNext()) {
			this.supplier = directedRelationship.getSource().iterator().next();
		}
	}
	
	@Override
	public void setClient(Element element) {
		DirectedRelationship directedRelationship = (DirectedRelationship)element;
		if (directedRelationship.getTarget().iterator().hasNext()) {
			this.client = directedRelationship.getTarget().iterator().next();
		}
	}
}
