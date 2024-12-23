/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.menu.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.aero.mtip.io.Importer;
import org.aero.mtip.profiles.Profile;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.FileSelect;
import org.aero.mtip.util.Logger;
import org.xml.sax.SAXException;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;

@SuppressWarnings("serial")
public class ImportAction extends MDAction {
	public ImportAction(String id, String name)	{
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(Application.getInstance().getProject() == null) {
			CameoUtils.popUpMessage("No active project. Open a project, then try again.");
			return;
		}
		
		try {
			File[] files = FileSelect.chooseXMLFileOpen();
			
			if (files == null || files.length == 0) {
				return;
			}
			
			Importer.importFromFiles(files, Application.getInstance().getProject().getPrimaryModel());
			
			CameoUtils.popUpMessage("Import complete.");
		} catch (NullPointerException npe) {
			Logger.logCrashException(npe);
		} catch (ParserConfigurationException pce) {
			Logger.logCrashException(pce);
		} catch (FileNotFoundException fnf) {
			Logger.logCrashException(fnf);
		} catch (SAXException saxe) {
			Logger.logCrashException(saxe);
		} catch (IOException ioe) {
			Logger.logCrashException(ioe);
		} finally {
			Logger.save();
			Logger.destroy();
			Importer.destroy();
		}
	}
}