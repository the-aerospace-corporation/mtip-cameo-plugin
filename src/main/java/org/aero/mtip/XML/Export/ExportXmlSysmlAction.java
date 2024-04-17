/* The Aerospace CorporationMTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Export;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.FileSelect;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;

public class ExportXmlSysmlAction extends MDAction {
	private static final long serialVersionUID = 6293853861208772420L;
	
	public ExportXmlSysmlAction(String id, String name)	{
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (Application.getInstance().getProject() == null) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "No active project. Open a project, then try again.");
			return;
		}
		try {
			File file = FileSelect.chooseXMLFile();
			
			if(file == null) {
				ExportLog.log("Failed to select file. Export aborted.");
				return;
			}
			
			XmlWriter.initialize();
			ExportXmlSysml.buildXML(file, null);
			FileSelect.writeXMLToFile(file);
			
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export complete.");
		} catch (NullPointerException npe) {
			ExportLog.logCrashException(npe);
		} catch (FileNotFoundException fnf) {
			ExportLog.logCrashException(fnf);
		} catch (IOException ioe) {
			ExportLog.logCrashException(ioe);
		} catch (TransformerException te) {
			ExportLog.logCrashException(te);
		}
	}
}
