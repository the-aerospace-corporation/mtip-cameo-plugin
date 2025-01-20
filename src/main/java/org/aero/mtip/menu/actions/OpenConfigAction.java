package org.aero.mtip.menu.actions;

import java.awt.event.ActionEvent;
import org.aero.mtip.ui.ConfigPage;
import com.nomagic.magicdraw.actions.MDAction;

public class OpenConfigAction extends MDAction {
  private static final long serialVersionUID = -7413646039436653441L;

  public OpenConfigAction(String id, String name) {
    super(id, name, null, null);
  }

  public void actionPerformed(ActionEvent e) {
    ConfigPage.displayFrame();
  }
}
