/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip;

import org.aero.mtip.menu.BrowserConfigurator;
import org.aero.mtip.menu.DiagramConfigurator;
import org.aero.mtip.menu.MainMenuConfigurator;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class MTIPCSMPlugin extends com.nomagic.magicdraw.plugins.Plugin {
  public void init() {
    ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
    
    manager.addMainMenuConfigurator(new MainMenuConfigurator());
    manager.addContainmentBrowserContextConfigurator(new BrowserConfigurator());
    manager.addDiagramContextConfigurator(DiagramTypeConstants.UML_ANY_DIAGRAM,
        new DiagramConfigurator());
  }

  public boolean close() {
    return true;
  }

  public boolean isSupported() {
    return true;
  }
}
