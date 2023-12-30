package org.aero.mtip.ModelElements;

import java.util.ArrayList;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.uml.DiagramType;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class CustomDiagram extends AbstractDiagram {

	public CustomDiagram(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.CUSTOM_DIAGRAM;
		this.allowableElements = new String[0];
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		// Set xmlConstant which will be placed in type field to 'custom.<CustomDiagramTypeName>'
		Diagram diag = (Diagram) element;
		DiagramPresentationElement presentationDiagram = project.getDiagram(diag);
		DiagramType diagType = presentationDiagram.getDiagramType();
		this.xmlConstant = XmlTagConstants.CUSTOM + diagType.getType();
		
		org.w3c.dom.Element data = super.writeToXML(element);
		
		return data;
	}

	@Override
	public String getSysmlConstant() {
		return SysmlConstants.CUSTOM_DIAGRAM;
	}

	@Override
	public String getDiagramType() {
		// TODO Auto-generated method stub
		return null;
	}

}
