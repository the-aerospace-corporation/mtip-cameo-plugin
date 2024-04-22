/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.HashMap;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class StateMachine extends CommonElement {
	public StateMachine(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STATE_MACHINE;
		this.xmlConstant = XmlTagConstants.STATEMACHINE;
		this.element = f.createStateMachineInstance();
		
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		// Remove auto-created region as they are defined explicitly in the XML
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine stateMachine = ((com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine) element);
		stateMachine.getRegion().clear();
		
		return element;
	}
	
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.isSubmachine() && !modelElement.newSubmachineCreated()) {
			String submachineID = modelElement.getSubmachine();
			XMLItem submachine = parsedXML.get(submachineID);
			Element submachineElement = Importer.getInstance().buildElement(parsedXML, submachine);
			modelElement.setNewSubmachineID(submachineElement.getID());
		}
	}
}
