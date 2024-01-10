/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Matrix;

import java.util.List;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.ModelDiagram;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.profiles.DependencyMatrixProfile;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.dependencymatrix.MatrixManager;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;

public abstract class AbstractMatrix extends AbstractDiagram implements ModelDiagram {
	protected final String MATRIX_FILTER = "MatrixFilter";
	protected String cameoConstant = "";
	protected int columnElementTypeCount = 0;
	protected int rowElementTypeCount = 0;
	
	public AbstractMatrix(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Diagram diagram = null;
		try {
			diagram = ModelElementsManager.getInstance().createDiagram(cameoConstant, (Namespace) owner);
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Read only exception caught");
		}
		com.nomagic.magicdraw.dependencymatrix.DependencyMatrix matrix = MatrixManager.getInstance(project).getMatrix(diagram);
		if(diagram != null) {
			StereotypesHelper.addStereotypeByString(diagram, "DependencyMatrix");
			StereotypesHelper.addStereotypeByString(diagram, "DiagramInfo");
			StereotypesHelper.addStereotypeByString(diagram, MATRIX_FILTER);
		}
		
		((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement)diagram).setName(name);
		return diagram;
	}	
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeColumnElementTypes(attributes, element);
		writeRowElementTypes(attributes, element);
		writeColumnScope(relationships, element);
		writeRowScope(relationships, element);
				
		return data;
	}
	
	protected void writeColumnElementTypes(org.w3c.dom.Element attributes, Element element) {
		Slot columnElementTypeSlot = DependencyMatrixProfile.getInstance().getColumnElementTypeSlot(element);
		
		if (columnElementTypeSlot == null) {
			return;
		}
		
		List<Object> columnElementTypes = StereotypesHelper.getValueFromSlot(element, false, null, columnElementTypeSlot);
		
		if (columnElementTypes == null) {
			return;
		}
		
		org.w3c.dom.Element columnElementTypeListTag 
			= XmlWriter.createTag(
					XmlTagConstants.ATTRIBUTE_NAME_COLUMN_ELEMENT_TYPE, 
					XmlTagConstants.ATTRIBUTE_TYPE_LIST);

		for(Object columnElementType : columnElementTypes) {
			if (!(columnElementType instanceof Element)) {
				continue;
			}
			
			writeColumnElementType(columnElementTypeListTag, columnElementTypeCount, ((NamedElement)columnElementType).getName());
			columnElementTypeCount++;
		}
		
		XmlWriter.add(attributes, columnElementTypeListTag);
	}
	
	protected void writeColumnElementType(org.w3c.dom.Element columnElementTypeListTag, int listPosition, String elementName) {
		org.w3c.dom.Element columnElementTag 
			= XmlWriter.createTag(
					XmlTagConstants.ATTRIBUTE_NAME_COLUMN_ELEMENT_TYPE, 
					XmlTagConstants.ATTRIBUTE_TYPE_STRING, 
					elementName);
		
		XmlWriter.addAttributeKey(columnElementTag, Integer.toString(listPosition));
		XmlWriter.add(columnElementTypeListTag, columnElementTag);
	}
	
	protected void writeRowElementTypes(org.w3c.dom.Element attributes, Element element) {
		Slot rowElementTypeSlot = DependencyMatrixProfile.getInstance().getRowElementTypeSlot(element);
		
		if (rowElementTypeSlot == null) {
			return;
		}
		
		List<Object> rowElementTypes = StereotypesHelper.getValueFromSlot(element, false, null, rowElementTypeSlot);
		
		if (rowElementTypes == null) {
			return;
		}
		
		org.w3c.dom.Element rowElementTypeListTag 
			= XmlWriter.createTag(XmlTagConstants.ATTRIBUTE_NAME_ROW_ELEMENT_TYPE,
					XmlTagConstants.ATTRIBUTE_NAME_COLUMN_ELEMENT_TYPE, 
					XmlTagConstants.ATTRIBUTE_TYPE_LIST);
		
		for(Object rowElementType : rowElementTypes) {
			if (!(rowElementType instanceof NamedElement)) {
				continue;
			}
			
			writeRowElementType(rowElementTypeListTag, rowElementTypeCount, ((NamedElement)rowElementType).getName());
			rowElementTypeCount++;
		}
		
		XmlWriter.add(attributes, rowElementTypeListTag);
	}
	
	protected void writeRowElementType(org.w3c.dom.Element rowElementTypeListTag, int listPosition, String elementName) {
		
	}
	
	protected void writeColumnScope(org.w3c.dom.Element relationships, Element element) {
		Slot columnScopeSlot = DependencyMatrixProfile.getInstance().getColumnScopeSlot(element);
		
		if (columnScopeSlot == null) {
			return;
		}
		
		List<Object> columnScopes = StereotypesHelper.getValueFromSlot(element, false, null, columnScopeSlot);
		
		for(Object columnScope : columnScopes) {
			if (!(columnScope instanceof Element)) {
				continue;
			}
			
			org.w3c.dom.Element columnScopeTag = XmlWriter.createMtipRelationship((Element)columnScope, XmlTagConstants.COLUMN_SCOPE);
			XmlWriter.add(relationships, columnScopeTag);
		}
	}
	
	protected void writeRowScope(org.w3c.dom.Element relationships, Element element) {
		Slot rowScopeSlot = DependencyMatrixProfile.getInstance().getRowScopeSlot(element);
		
		if (rowScopeSlot == null) {
			return;
		}
		
		List<Object> rowScopes = StereotypesHelper.getValueFromSlot(element, false, null, rowScopeSlot);
		
		for(Object rowScope : rowScopes) {
			if (!(rowScope instanceof Element)) {
				continue;
			}
			
			org.w3c.dom.Element rowScopeTag = XmlWriter.createMtipRelationship((Element)rowScope, XmlTagConstants.ROW_SCOPE);
			XmlWriter.add(relationships, rowScopeTag);
		}
	}
	
	@Override
	public String getSysmlConstant() {
		return this.cameoConstant;
	}

	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}
}
