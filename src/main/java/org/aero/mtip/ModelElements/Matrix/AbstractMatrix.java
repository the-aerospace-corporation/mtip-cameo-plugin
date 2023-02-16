/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Matrix;

import java.util.List;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.ModelDiagram;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.dependencymatrix.MatrixManager;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

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
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		Profile dependencyMatrixProfile = StereotypesHelper.getProfile(project, SysmlConstants.DEPENDENCY_MATRIX_PROFILE);
		Stereotype matrixFilterStereotype = StereotypesHelper.getStereotype(project, MATRIX_FILTER);
		
		Property columnElementTypeProperty = StereotypesHelper.getPropertyByName(matrixFilterStereotype, "columnElementType");
		Property rowElementTypeProperty = StereotypesHelper.getPropertyByName(matrixFilterStereotype, "rowElementType");
		Property columnScopeProperty = StereotypesHelper.getPropertyByName(matrixFilterStereotype, "columnScope");
		Property rowScopeProperty = StereotypesHelper.getPropertyByName(matrixFilterStereotype, "rowScope");
		
		Slot columnElementTypeSlot = StereotypesHelper.getSlot(element, columnElementTypeProperty, false);
		Slot rowElementTypeSlot = StereotypesHelper.getSlot(element, rowElementTypeProperty, false);
		Slot columnScopeSlot = StereotypesHelper.getSlot(element, columnScopeProperty, false);
		Slot rowScopeSlot = StereotypesHelper.getSlot(element, rowScopeProperty, false);
		
		List<Object> columnElementTypes = null;
		if(columnElementTypeSlot != null) {
			columnElementTypes = StereotypesHelper.getValueFromSlot(element, false, null, columnElementTypeSlot);
		}
		List<Object> rowElementTypes = null;
		if(rowElementTypeSlot != null) {
			rowElementTypes = StereotypesHelper.getValueFromSlot(element, false, null, rowElementTypeSlot);
		}
		List<Object> columnScopes = null;
		if(columnScopeSlot != null) {
			columnScopes = StereotypesHelper.getValueFromSlot(element, false, null, columnScopeSlot);
		}
		List<Object> rowScopes = null;
		if(rowScopeSlot != null) {
			rowScopes = StereotypesHelper.getValueFromSlot(element, false, null, rowScopeSlot);
		}
		
		if(columnElementTypes != null) {
			org.w3c.dom.Element columnElementTypeListTag = createListElement(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_COLUMN_ELEMENT_TYPE);
			for(Object columnElementType : columnElementTypes) {
				Element columnElement = (Element)columnElementType;
				String elementName = ((NamedElement)columnElement).getName();
//				ExportLog.log("elementTypeMap.put(\"" + ((NamedElement)columnElement).getName() + ", \"" + columnElement.getID() + "\");");
				org.w3c.dom.Element columnElementTypeTag = createColumnElementType(xmlDoc, Integer.toString(columnElementTypeCount), elementName);
				columnElementTypeListTag.appendChild(columnElementTypeTag);
				columnElementTypeCount++;
			}
			attributes.appendChild(columnElementTypeListTag);
		}
		
		if(rowElementTypes != null) {
			org.w3c.dom.Element rowElementTypeListTag = createListElement(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_ROW_ELEMENT_TYPE);
			for(Object rowElementType : rowElementTypes) {
				Element rowElement = (Element)rowElementType;
				CameoUtils.logGUI("Column element type found to be " + rowElement.getHumanName() + " with id " + rowElement.getID());
				String elementName = ((NamedElement)rowElement).getName();
				org.w3c.dom.Element rowElementTypeTag = createRowElementType(xmlDoc, Integer.toString(rowElementTypeCount), elementName);
				rowElementTypeListTag.appendChild(rowElementTypeTag);
				rowElementTypeCount++;
			}
			attributes.appendChild(rowElementTypeListTag);
		}
		
		if(columnScopes != null) {
			for(Object columnScope : columnScopes) {
				Element columnScopeElement = (Element)columnScope;
				org.w3c.dom.Element columnScopeTag = createRel(xmlDoc, columnScopeElement, XmlTagConstants.COLUMN_SCOPE);
				relationships.appendChild(columnScopeTag);
			}
		}
		
		if(rowScopes != null) {
			for(Object rowScope : rowScopes) {
				Element rowScopeElement = (Element)rowScope;
				org.w3c.dom.Element rowScopeTag = createRel(xmlDoc, rowScopeElement, XmlTagConstants.ROW_SCOPE);
				relationships.appendChild(rowScopeTag);
			}
		}
		
		return data;
	}
	
	protected org.w3c.dom.Element createColumnElementType(Document xmlDoc, String listPosition, String elementName) {
		org.w3c.dom.Element tag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE_NAME_COLUMN_ELEMENT_TYPE);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, listPosition);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		if(!(elementName == null)) {
			tag.appendChild(xmlDoc.createTextNode(elementName));
		}
		return tag;
	}
	
	protected org.w3c.dom.Element createRowElementType(Document xmlDoc, String listPosition, String elementName) {
		org.w3c.dom.Element tag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE_NAME_ROW_ELEMENT_TYPE);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, listPosition);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		if(!(elementName == null)) {
			tag.appendChild(xmlDoc.createTextNode(elementName));
		}
		return tag;
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
