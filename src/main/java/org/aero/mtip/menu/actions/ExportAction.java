/*
 * The Aerospace CorporationMTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.menu.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import org.aero.mtip.io.Exporter;
import org.aero.mtip.profiles.Profile;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.FileSelect;
import org.aero.mtip.util.Logger;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;

public class ExportAction extends MDAction {
  private static final long serialVersionUID = 6293853861208772420L;

  public ExportAction(String id, String name) {
    super(id, name, null, null);
  }

  public void actionPerformed(ActionEvent e) {
    if (Application.getInstance().getProject() == null) {
      CameoUtils.popUpMessage("No active project. Open a project, then try again.");
      return;
    }

    try {
      File file = FileSelect.chooseXMLFile();

      if (file == null) {
        Logger.log("Failed to select file. Export aborted.");
        return;
      }

      Exporter.exportModel(file);
      FileSelect.writeXMLToFile(file);

      CameoUtils.popUpMessage("Export complete.");
    } catch (NullPointerException npe) {
      Logger.logCrashException(npe);
    } catch (FileNotFoundException fnf) {
      Logger.logCrashException(fnf);
    } catch (IOException ioe) {
      Logger.logCrashException(ioe);
    } catch (TransformerException te) {
      Logger.logCrashException(te);
    } finally {
      Logger.save();
      Logger.destroy();
    }
  }
}
