/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.menu;

import org.aero.mtip.menu.actions.ExportPackageAction;
import org.aero.mtip.menu.actions.ImportPackageAction;
import org.aero.mtip.menu.actions.ProfileAssessmentAction;
import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class BrowserConfigurator implements BrowserContextAMConfigurator {

  public int getPriority() {
    return AMConfigurator.HIGH_PRIORITY;
  }

  public void configure(ActionsManager manager, Tree tree) {
    ActionsCategory mtipCategory = manager.getCategory("MTIP");
    
    if (mtipCategory == null) {
      mtipCategory = createMtipCategory(manager);
    }

    if (tree.getSelectedNode() == null) {
      return;
    }
    
    Object selectedObj = tree.getSelectedNode().getUserObject();
    
    if (selectedObj instanceof Profile) {
      createProfileActions(mtipCategory, (Profile)selectedObj);
    }
    
    if (selectedObj instanceof Package) {
      createImportExportCategory((Package) selectedObj, mtipCategory);      
    }
  }
  
  private ActionsCategory createMtipCategory(ActionsManager manager) {
    ActionsCategory mtipCategory = new MDActionsCategory("MTIP", "MTIP");
    
    mtipCategory.setNested(true);
    manager.addCategory(mtipCategory);
    
    return mtipCategory;
  }
  
  private void createProfileActions(ActionsCategory mtipCategory, Profile profile) {
    MDActionsCategory profileCategory =
        new MDActionsCategory("profile", "profile");
    profileCategory.setNested(true);
    
    ActionsCategory category = new ActionsCategory("", "");
    category.addAction(new ProfileAssessmentAction(null, "Assess Profile", profile));
    
    profileCategory.addAction(category);
    mtipCategory.addAction(profileCategory);
  }
  
  private void createImportExportCategory(Package startPackage, ActionsCategory mtipCategory) {
    MDActionsCategory importExportCategory =
        new MDActionsCategory("Import/Export", "Import/Export");
    importExportCategory.setNested(true);

    ActionsCategory category = new ActionsCategory("", "");
    category.addAction(new ImportPackageAction(null, "Import XML", startPackage));
    category.addAction(new ExportPackageAction(null, "Export XML", startPackage));

    importExportCategory.addAction(category);
    mtipCategory.addAction(importExportCategory);
  }
}
