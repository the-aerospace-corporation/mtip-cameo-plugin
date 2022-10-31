package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;

public abstract class DoDAFDiagram extends AbstractDiagram {

	public DoDAFDiagram(String name, String EAID) {
		super(name, EAID);
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		try {
			CameoUtils.logGUI("Creating element from abstract diagram class");
			sysmlElement = ModelElementsManager.getInstance().createDiagram(DiagramTypeConstants.UML_CLASS_DIAGRAM, (Namespace) owner);
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Caught read only exception");
		}
//		setOwner(project, sysmlElement);
		((NamedElement) sysmlElement).setName(name);

		return sysmlElement;
	}
}
