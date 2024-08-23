package org.aero.mtip.menu.actions;

import java.awt.event.ActionEvent;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class ProfileAssessmentAction extends MDAction {
  private static final long serialVersionUID = 7664536328826849970L;

  public ProfileAssessmentAction(String id, String name, Profile profile) {
    super(id, name, null, null);
  }

  public void actionPerformed(ActionEvent e) {
    // Analyze Profile
  }
}
