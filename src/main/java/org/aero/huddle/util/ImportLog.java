package org.aero.huddle.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.io.FilenameUtils;

public class ImportLog {
	protected static List<String> logData = new ArrayList<String> ();
	private static String documentsPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	//private final static String logName = "Import Log " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".txt";
	private final static String logFolder = "Cameo Import Logs";
	private static String logFileName;
	private static Path filePath = null;	
	private static ArrayList<String> xmlFileNames = new ArrayList<String> ();

	
	public ImportLog() {
		
	}
	
	public static void reset() {
		logData = new ArrayList<String> ();
		filePath = null;
	}
	
	public static void save() {
		new File(Paths.get(documentsPath, logFolder).toString()).mkdirs();
		createFileName();
		filePath = Paths.get(documentsPath, logFolder, logFileName);
		
		if(!logData.isEmpty()) {
			try {
				File f = new File(filePath.toString());
				FileWriter fw = null;
				if(f.isFile()) { 
					fw = new FileWriter(filePath.toString(), false); //the true will append the new data
				} else {
					fw = new FileWriter(filePath.toString(), true);
				}
			   
			    for(String line : logData) {
			    	fw.write(line);
			    }
			    fw.close();
			    CameoUtils.logGUI("Log file saved to disk at: " + filePath.toString());
			}
			catch(IOException ioe) {
				CameoUtils.logGUI("IOException: " + ioe.getMessage());
			}
		} else {
			 CameoUtils.logGUI("No data to log.");
		}
		
	}

	private static void createFileName() {
		String logName = "";
		for(String xmlFileName : xmlFileNames) {
			if(xmlFileName != null) {
				logName += FilenameUtils.removeExtension(xmlFileName) + "_";
			}
		}
		logName += "HuddleLog_";
		logName += new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		logName += ".txt";
		
		logFileName = logName;
	}
	/**
	 * Prepends file name of XML being imported to the file name which will be used for the text log file. Supports
	 * multiple file names prepended when multiple XMLs are imported at once.
	 * @param fileName String file name
	 */
	public static void addFilePrefix(File file) {
		xmlFileNames.add(file.getName());
	}
	
	public static void log(String message) {
		logData.add(message + "\n");
	}
}
