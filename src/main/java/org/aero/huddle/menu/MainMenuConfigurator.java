package org.aero.huddle.menu;

import org.aero.huddle.XML.Export.ExportXmlSysmlAction;
import org.aero.huddle.XML.Import.ImportXmlSysmlAction;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.MDActionsCategory;

public class MainMenuConfigurator implements AMConfigurator
{
	/*
	 * (non-Javadoc)
	 * @see com.nomagic.actions.AMConfigurator#configure(com.nomagic.actions.ActionsManager)
	 * 
	 * This method is called when the main CSM menu is initialized.
	 * Here creates the "Huddle" menu.
	 * @param manager: the actions manager to be configured.
	 */
	public void configure(ActionsManager manager) {
		//Search for Huddle action category
		ActionsCategory aCategory = manager.getCategory("Huddle");
		if(aCategory == null) {
			//Create a new category
			aCategory = new MDActionsCategory("Huddle","Huddle");
			aCategory.setNested(true);
			
			//Add to the end of the menu list
			manager.addCategory(aCategory);
		}
		
		//Add actions to Huddle category here
		ActionsCategory category = new ActionsCategory("","");
		category.addAction(new ImportXmlSysmlAction("", "Import model"));
		category.addAction(new ExportXmlSysmlAction("", "Export model"));
		category.addAction(new AboutAction("","About"));
		
		aCategory.addAction(category);
	}
	
	public int getPriority() {
		return AMConfigurator.MEDIUM_PRIORITY;
	}
}
