/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.menu;

import org.aero.mtip.menu.actions.AboutAction;
import org.aero.mtip.menu.actions.ExportAction;
import org.aero.mtip.menu.actions.ImportAction;
import org.aero.mtip.menu.actions.OpenConfigAction;
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
	 * Here creates the "MTIP" menu.
	 * @param manager: the actions manager to be configured.
	 */
	public void configure(ActionsManager manager) {
		//Search for MTIP action category
		ActionsCategory aCategory = manager.getCategory("MTIP");
		if(aCategory == null) {
			//Create a new category
			aCategory = new MDActionsCategory("MTIP","MTIP");
			aCategory.setNested(true);
			
			//Add to the end of the menu list
			manager.addCategory(aCategory);
		}
		
		//Add actions to MTIP category here
		ActionsCategory category = new ActionsCategory("","");
		category.addAction(new ImportAction("", "Import"));
		category.addAction(new ExportAction("", "Export"));
		category.addAction(new OpenConfigAction("", "Config"));
		category.addAction(new AboutAction("","About"));
		
		aCategory.addAction(category);
	}
	
	public int getPriority() {
		return AMConfigurator.MEDIUM_PRIORITY;
	}
}
