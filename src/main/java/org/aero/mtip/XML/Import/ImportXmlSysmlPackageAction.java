/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Import;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.FileSelect;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

@SuppressWarnings("serial")
public class ImportXmlSysmlPackageAction extends MDAction {
	private Package packageElement = null;
	
	public ImportXmlSysmlPackageAction(String id, String name, Package packageElement)	{
		super(id, name, null, null);
		this.packageElement = packageElement;
	}
	
	public void actionPerformed(ActionEvent e) {
		Project project = Application.getInstance().getProject();
		if(project == null) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "No active project. Open a project, then try again.");
		}
		try
		{
			File[] files = FileSelect.chooseXMLFileOpen();
			ImportXmlSysml.resetImportParameters();
			for(File file : files) {
				Document doc = FileSelect.createDocument(file);
				doc.getDocumentElement().normalize();
				ImportXmlSysml.parseXML(doc, packageElement);
			}
			//Use Puddle structure, initial mapping
			ImportXmlSysml.createModel();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import complete.");
			
		} catch (NullPointerException npe) {
			StringWriter sw = new StringWriter();
			npe.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - NullPointerException");
			CameoUtils.logGUI(exceptionAsString);
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - ParserConfigurationException");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - FileNotFoundException");
		} catch (SAXException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - SAXException");
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - IOException");
		}
	}
}