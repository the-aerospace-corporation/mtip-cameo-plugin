package org.aero.huddle.XML.Import;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.FileSelect;
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
			File file = FileSelect.chooseXMLFileOpen();
			Document doc = FileSelect.createDocument(file);
			doc.getDocumentElement().normalize();
			
			//Use Puddle structure, initial mapping
			ImportXmlSysml.createModel(doc, packageElement);
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import complete.");
			
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - NullPointerException");
			CameoUtils.logGUI(org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(npe));
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