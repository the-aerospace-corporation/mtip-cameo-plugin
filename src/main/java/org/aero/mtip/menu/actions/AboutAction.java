/*
 * The Aerospace Corporation Huddle_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.menu.actions;

import java.awt.event.ActionEvent;
import org.aero.mtip.util.CameoUtils;
import com.nomagic.magicdraw.actions.MDAction;

public class AboutAction extends MDAction {
  private static final long serialVersionUID = -7302532135499669288L;
  
  public static String VERSION = "2021x v1.0.0";

  public AboutAction(String id, String name) {
    super(id, name, null, null);
  }

  public void actionPerformed(ActionEvent e) {
    CameoUtils.popUpMessage(String.format(
        "MTIP-Cameo version %s",
        VERSION));
  }
}
