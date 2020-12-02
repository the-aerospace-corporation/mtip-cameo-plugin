package org.aero.huddle.XML.Import;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.FileSelect;
import org.aero.huddle.util.ImportLog;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;

@SuppressWarnings("serial")
public class ImportXmlSysmlAction extends MDAction {
	public ImportXmlSysmlAction(String id, String name)	{
		super(id, name, null, null);
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
				ImportLog.addFilePrefix(file);
				ImportXmlSysml.parseXML(doc, null);
			}
			//Use Puddle structure, initial mapping
			ImportXmlSysml.createModel();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import complete.");
			
		} catch (NullPointerException npe) {
			StringWriter sw = new StringWriter();
			npe.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			ImportLog.save();
			CameoUtils.logGUI(org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(npe));
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - NullPointerException" + exceptionAsString);
		} catch (ParserConfigurationException e1) {
			StringWriter sw = new StringWriter();
			e1.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - ParserConfigurationException" + exceptionAsString);
		} catch (FileNotFoundException e1) {
			StringWriter sw = new StringWriter();
			e1.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - FileNotFoundException" + exceptionAsString);
		} catch (SAXException e1) {
			StringWriter sw = new StringWriter();
			e1.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - SAXException: " + exceptionAsString);
		} catch (IOException e1) {
			StringWriter sw = new StringWriter();
			e1.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - IOException" + exceptionAsString);
		}
	}
}