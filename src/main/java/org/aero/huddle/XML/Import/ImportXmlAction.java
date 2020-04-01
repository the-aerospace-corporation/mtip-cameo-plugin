package org.aero.huddle.XML.Import;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.aero.huddle.util.FileSelect;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.browser.Browser;
import com.nomagic.magicdraw.ui.browser.Node;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

// Base class for plugin's import options.
public class ImportXmlAction extends MDAction
{
	private Object[] options = {"XML (Puddle)", "XML (Blocks)"};
	
	public ImportXmlAction(String id, String name) {
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		Project project = Application.getInstance().getProject();
		if(project == null) {
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "No active project. Open a project, then try again.");
		}
		else {
			try {
				int n = JOptionPane.showOptionDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Select XML import type", "Choose Export Format", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				
				File file = FileSelect.chooseXMLFile();
				Document doc = createDocument(file);
				doc.getDocumentElement().normalize();
				
				//Use Puddle structure, initial mapping
				if(n == 0) {
					ImportXmlPuddle.buildModel(doc);
				}
				//Use Puddle structure, blocks only
				else if(n == 1)	{
					ImportXmlBlocks.buildModel(doc);
				}
				
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import complete.");

				
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Import aborted - NullPointerException");
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
	
	//Creates a Document object to be used in creating the XML output
	public static Document createDocument(File inputFile) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		return doc;
	}
}
