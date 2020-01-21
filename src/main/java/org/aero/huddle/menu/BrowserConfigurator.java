package org.aero.huddle.menu;

import java.util.List;

import org.aero.huddle.XML.Export.ExportXmlSysmlPackageAction;
import org.aero.huddle.XML.Import.ImportXmlSysmlPackageAction;
import org.aero.huddle.mab.ImportAction;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsID;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

public class BrowserConfigurator implements BrowserContextAMConfigurator {

	public int getPriority() {
		return AMConfigurator.HIGH_PRIORITY;
	}

	public void configure(ActionsManager manager, Tree tree) {
		ActionsCategory huddleCategory = manager.getCategory("HUDDLE");
		if(huddleCategory == null) {
			huddleCategory = new MDActionsCategory("HUDDLE", "HUDDLE");
			huddleCategory.setNested(true);
			ActionsCategory helpCategory = manager.getCategory(ActionsID.HELP);
			if(helpCategory !=null) {
				List<ActionsCategory> categories = manager.getCategories();
				int indexOfCat = categories.indexOf(helpCategory);
				manager.addCategory(indexOfCat,huddleCategory);
			}
			else {
				manager.addCategory(huddleCategory);
			}	
		}
		
		if(tree.getSelectedNode() != null) {
			Object selectedObj = tree.getSelectedNode().getUserObject();
			if(selectedObj instanceof Element) {
				Element element = (Element)selectedObj;
				
				ActionsCategory transformCategory = new ActionsCategory(null, "Import MAB");
				huddleCategory.addAction(transformCategory);
			    transformCategory.addAction(new ImportAction(null, "MAB XML Import", element));	
			}
			if(selectedObj instanceof Package) {
				Package startPackage = (Package)selectedObj;
				
				MDActionsCategory importExportCategory = new MDActionsCategory("Import/Export", "Import/Export");
				importExportCategory.setNested(true);
				
				ActionsCategory category = new ActionsCategory("", "");
		        category.addAction(new ImportXmlSysmlPackageAction(null, "Import XML", startPackage));
		        category.addAction(new ExportXmlSysmlPackageAction(null, "Export XML", startPackage));
		        
		        importExportCategory.addAction(category);
				huddleCategory.addAction(importExportCategory);
			}
			
		}
	}
}
