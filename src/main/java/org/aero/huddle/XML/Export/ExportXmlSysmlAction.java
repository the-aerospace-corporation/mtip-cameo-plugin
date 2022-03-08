/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.XML.Export;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.FileSelect;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;

public class ExportXmlSysmlAction extends MDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293853861208772420L;
	public ExportXmlSysmlAction(String id, String name)
	{
		super(id, name, null, null);
	}
	public void actionPerformed(ActionEvent e)
	{
		Project project = Application.getInstance().getProject();
		if(project == null) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "No active project. Open a project, then try again.");
		}
		try
		{
//			int n = JOptionPane.showOptionDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Select XML import type", "Choose Export Format", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
//			
			File file = FileSelect.chooseXMLFile();
			if(file != null) {
				Document doc = createDocument();
				
				ExportXmlSysml.buildXML(doc, file, null);
				FileSelect.writeXMLToFile(doc, file);
			}
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export complete.");
			
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export aborted - NullPointerException");
			StringWriter sw = new StringWriter();
			npe.printStackTrace(new PrintWriter(sw));
			CameoUtils.logGUI(sw.toString());
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Export aborted - ParserConfigurationException");
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
	
	//Creates a Document object to be used in creating the XML output
	public static Document createDocument() throws ParserConfigurationException	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		return doc;
	}
}
