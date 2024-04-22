/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Export;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.FileSelect;
import org.aero.mtip.util.Logger;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

public class ExportPackageAction extends MDAction {
	static final long serialVersionUID = 6293853861208772420L;
	Package packageElement = null;
	
	public ExportPackageAction(String id, String name, Package packageElement) {
		super(id, name, null, null);
		this.packageElement = packageElement;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(Application.getInstance().getProject() == null) {
			CameoUtils.popUpMessage("No active project. Open a project, then try again.");
		}
		
		try {
			File file = FileSelect.chooseXMLFile();
			
			if(file == null) {
				return;
			}
			
			Exporter.exportModelFromPackage(file, packageElement);
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
