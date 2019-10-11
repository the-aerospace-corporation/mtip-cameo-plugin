package org.aero.huddle.XML.Import;

import java.awt.event.ActionEvent;

import com.nomagic.magicdraw.actions.MDAction;

@SuppressWarnings("serial")
public class ElementTestAction extends MDAction {
	
	public ElementTestAction(String id, String name) {
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e){
		ImportXmlSysml.testAllCommonElements();
	}
}
