package org.aero.huddle.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

// Basic methods for selecting import/export files
public class FileSelect
{
	public static File chooseXMLFileOpen() throws FileNotFoundException
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		int option = chooser.showOpenDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION)
		{
			if(!chooser.getSelectedFile().getName().endsWith(".xml"))
				throw new FileNotFoundException("File for import must be .xml");
		}
		
		return chooser.getSelectedFile();
	}
	
	public static File chooseXMLFile()
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		int option = chooser.showSaveDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION)
		{
			String filename = chooser.getSelectedFile().toString();
			if(!filename.endsWith(".xml"))
				filename += ".xml";
			chooser.setSelectedFile(new File(filename));
		}
		
		return chooser.getSelectedFile();
	}
	
	public static File chooseCSVFile()
	{
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		int option = chooser.showSaveDialog(null);
		
		if(option == JFileChooser.APPROVE_OPTION)
		{
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
}
