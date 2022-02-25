package org.aero.huddle.ModelElements.Table;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;

import com.nomagic.generictable.GenericTableManager;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;

public class AbstractTable extends CommonElement {
	protected Diagram table;
	protected String cameoConstant;
	//InstanceTable Information
	//Profile MDcustomsysMLProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML");
    //Stereotype instanceTableStereotype = StereotypesHelper.getStereotype(project, "InstanceTable", MDcustomsysMLProfile);
	
	// Get Properties of a steretoype for columns of a table 
//	for (Property p : StereotypesHelper.getPropertiesWithDerivedOrdered(resultStereotype)){
//		   columns.add(GenericTableManager.getColumnIDByTag(p.getName(), resultStereotype));
//		}
//	GenericTableManager.addColumnsById(resultsTable, columns);
	public AbstractTable(String name, String EAID) {
		super(name, EAID);
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		try {
			sysmlElement = GenericTableManager.createGenericTable(project, this.name);
			table = (Diagram)sysmlElement;
			table.setOwnerOfDiagram((Namespace)owner);
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Read only Exception caught.");
			e.printStackTrace();
		}
		addInitialStereotype();
		return sysmlElement;
	}
	
	protected void addColumns() {
		
	}
}
