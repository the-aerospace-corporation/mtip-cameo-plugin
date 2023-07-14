/* The Aerospace Corporation MTIP_Cameo
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
import org.aero.mtip.util.FileSelect;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

public class ExportXmlSysmlPackageAction extends MDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293853861208772420L;
	private Package packageElement = null;
	
	public ExportXmlSysmlPackageAction(String id, String name, Package packageElement) {
		super(id, name, null, null);
		this.packageElement = packageElement;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Project project = Application.getInstance().getProject();
		if(project == null) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "No active project. Open a project, then try again.");
		}
		
		try {
			File file = FileSelect.chooseXMLFile();
			if(file == null) {
				return;
			}
			
			XmlWriter.initialize();
			
			ExportXmlSysml.buildXML(file, packageElement);
			FileSelect.writeXMLToFile(file);
			
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export complete.");
			
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export aborted - NullPointerException");
			StringWriter sw = new StringWriter();
			npe.printStackTrace(new PrintWriter(sw));
			CameoUtils.logGUI(sw.toString());
		} catch (FileNotFoundException fnf) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export aborted - FileNotFoundException");
			StringWriter sw = new StringWriter();
			fnf.printStackTrace(new PrintWriter(sw));
			CameoUtils.logGUI(sw.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export aborted - IOException");
		} catch (TransformerException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export aborted - TransformerException");
		}
	}
}
