/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.menu;

import java.util.List;
import org.aero.mtip.menu.actions.ExportPackageAction;
import org.aero.mtip.menu.actions.ImportPackageAction;
import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsID;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

public class BrowserConfigurator implements BrowserContextAMConfigurator {

	public int getPriority() {
		return AMConfigurator.HIGH_PRIORITY;
	}

	public void configure(ActionsManager manager, Tree tree) {
		ActionsCategory mtipCategory = manager.getCategory("MTIP");
		if(mtipCategory == null) {
			mtipCategory = new MDActionsCategory("MTIP", "MTIP");
			mtipCategory.setNested(true);
			ActionsCategory helpCategory = manager.getCategory(ActionsID.HELP);
			if(helpCategory !=null) {
				List<ActionsCategory> categories = manager.getCategories();
				int indexOfCat = categories.indexOf(helpCategory);
				manager.addCategory(indexOfCat,mtipCategory);
			}
			else {
				manager.addCategory(mtipCategory);
			}	
		}
		
		if(tree.getSelectedNode() != null) {
			Object selectedObj = tree.getSelectedNode().getUserObject();
			if(selectedObj instanceof Package) {
				Package startPackage = (Package)selectedObj;
				
				MDActionsCategory importExportCategory = new MDActionsCategory("Import/Export", "Import/Export");
				importExportCategory.setNested(true);
				
				ActionsCategory category = new ActionsCategory("", "");
		        category.addAction(new ImportPackageAction(null, "Import XML", startPackage));
		        category.addAction(new ExportPackageAction(null, "Export XML", startPackage));
		        
		        importExportCategory.addAction(category);
				mtipCategory.addAction(importExportCategory);
			}			
		}
	}
}
