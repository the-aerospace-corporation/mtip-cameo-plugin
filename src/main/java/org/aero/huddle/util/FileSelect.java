/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// Basic methods for selecting import/export files
public class FileSelect
{
	public static File[] chooseXMLFileOpen() throws FileNotFoundException
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		chooser.setMultiSelectionEnabled(true);
		int option = chooser.showOpenDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION)
		{
			for(File file : chooser.getSelectedFiles()) {
				String filename = file.getName();
				if(!filename.endsWith(".xml")) {
					throw new FileNotFoundException("File for import must be .xml");
				}
			}
		}
		return chooser.getSelectedFiles();
	}
	
	public static File chooseXMLFile() throws FileNotFoundException	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String filename = chooser.getSelectedFile().toString();
			if(!filename.endsWith(".xml")) {
				filename += ".xml";
			} 
			chooser.setSelectedFile(new File(filename));
		} else {
			throw new FileNotFoundException("No File Selected.");
		}
		return chooser.getSelectedFile();
	}
	
	public static File chooseCSVFile()
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		int option = chooser.showSaveDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			String filename = chooser.getSelectedFile().toString();
			if(!filename.endsWith(".csv"))
				filename += ".csv";
			chooser.setSelectedFile(new File(filename));
		}
		
		return chooser.getSelectedFile();
	}
	//Creates a Document object to be used in creating the XML output
	public static Document createDocument(File inputFile) throws ParserConfigurationException, SAXException, IOException 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
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
