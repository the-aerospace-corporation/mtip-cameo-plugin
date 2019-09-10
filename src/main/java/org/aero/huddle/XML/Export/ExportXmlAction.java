package org.aero.huddle.XML.Export;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.browser.Browser;
import com.nomagic.magicdraw.ui.browser.Node;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import org.aero.huddle.exceptions.DuplicateTagNameException;
import org.aero.huddle.util.FileSelect;
import org.aero.huddle.util.XmlTagConstants;

// Base class for the plugin's export options
public class ExportXmlAction extends MDAction
{
	private Object[] options = {"XML (Puddle)", "XML (Blocks)"};
	
	public ExportXmlAction(String id, String name)
	{
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Project project = Application.getInstance().getProject();
		if(project == null)
		{
			JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "No active project. Open a project, then try again.");
		}
		else
		{
			try
			{
				int n = JOptionPane.showOptionDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Select XML output type", "Choose Export Format", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				Element pkg = (Element) selectPackageForExport();
				
				File file = FileSelect.chooseXMLFile();
				if(file != null)
				{	
					Document doc = createDocument();
					
					//Use Puddle structure (direct mapping)
					if(n == 0)
					{
						//This is the initial mapping (block-system, association-interaction, activity-function, parameter-event)
						boolean duplicates = ExportXmlPuddle.buildXML(doc, pkg);
						if(duplicates)
							JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "**DUPLICATE TAG WARNING**\nDuplicate tags were discovered during the export and may cause discrepancies.");

					}
					//Use Puddle structure (blocks only)
					else if(n == 1)
					{
						//This is the revised import (everything as blocks and relationships as associations)
						boolean duplicates = ExportXmlBlocks.buildXML(doc, pkg);
						if(duplicates)
							JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "**DUPLICATE TAG WARNING**\nDuplicate tags were discovered during the export and may cause discrepancies.");
					}
					
					writeXMLToFile(doc, file);
					
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export complete.");
				}
				else
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export cancelled.");
				
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export aborted - NullPointerException");
			} catch (ClassCastException cce) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export aborted - Problem with selected element from containment tree.");
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export aborted - ParserConfigurationException");
			} catch (TransformerException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export aborted - TransformerException");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export aborted - FileNotFoundException");
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Export aborted - IOException");
			}
		}
	}
	
	//Get the first selected element from the containment tree for export
	//Consider adding exception if object being returned is not an instance of Element
	public static Object selectPackageForExport() throws NullPointerException
	{
		JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Please select the appropriate package for export from the containment tree. "
				+ "Then select OK to continue export.\nNote, do not select the data model - all model elements should be placed in a top-level package underneath the data model and this is the package to be selected for export.\n"
				+ "For example, if the heirarchy is Data Model => Top-Level Package => Model Elements, select Top-Level Package.");
		Browser browser = Application.getInstance().getMainFrame().getBrowser();
		browser.getContainmentTree().open();
		Node node = browser.getContainmentTree().getSelectedNode();
		if(node == null)
			throw new NullPointerException("No selected package from containment tree.");
		return node.getUserObject();
	}
	
	//Creates a Document object to be used in creating the XML output
	public static Document createDocument() throws ParserConfigurationException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		return doc;
	}
	
	public static void writeXMLToFile(Document doc, File file) throws TransformerException, IOException
	{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream(file));
		transformer.transform(source, result);
		result.getOutputStream().close();
	}
}
