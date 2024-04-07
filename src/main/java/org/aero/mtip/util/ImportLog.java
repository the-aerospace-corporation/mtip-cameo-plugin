/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
	private final static String logFolder = "Cameo Logs";
	private final static String subFolder = "Import";
	private static String logFileName;
	private static Path filePath = null;	
	private static ArrayList<String> xmlFileNames = new ArrayList<String> ();
	
	public ImportLog() {
		
	}
	
	public static void reset() {
		logData = new ArrayList<String> ();
		filePath = null;
		logFileName = "";
		xmlFileNames = new ArrayList<String> ();
	}
	
	public static void save() {
		new File(Paths.get(documentsPath, logFolder, subFolder).toString()).mkdirs();
		createFileName();
		filePath = Paths.get(documentsPath, logFolder, subFolder, logFileName);
		
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
//		logName += "Huddle_Import_Log_";
		logName += new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		logName += "_";
		for(String xmlFileName : xmlFileNames) {
			if(xmlFileName != null) {
				logName += FilenameUtils.removeExtension(xmlFileName) + "_";
			}
		}
		
		logName = logName.substring(0, logName.length() - 1);
		logName += ".txt";
		if(logName.length() > 250) {
			logName = logName.substring(0, 249);
		}
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
	
	public static void logException(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		
		log(sw.toString());
	}
}
