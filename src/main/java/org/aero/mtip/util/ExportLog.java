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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.aero.mtip.XML.Export.ExportMetrics;

import com.nomagic.magicdraw.core.Application;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ExportLog {
	protected static List<String> logData = new ArrayList<String> ();
	private static String documentsPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	private final static String logFolder = "Cameo Logs";
	private final static String subFolder = "Export";
	private static String logFileName;
	private static Path filePath = null;
	private static final String horizontalLine = "--------------------------------------\n";
	
	public ExportLog() {
		
	}
	
	public static void reset() {
		logData = new ArrayList<String> ();
		filePath = null;
		logFileName = "";
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
			    CameoUtils.logGUI(String.format("Log file saved to disk at: %s", filePath.toString()));
			}
			catch(IOException ioe) {
				CameoUtils.logGUI("IOException: " + ioe.getMessage());
			}
		} else {
			 CameoUtils.logGUI("No data to log.");
		}
		
	}

	private static void createFileName() {
		String logName = Application.getInstance().getProject().getName() + "_";
		logName += new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		logName += ".txt";
		
		if(logName.length() > 250) {
			logName = logName.substring(0, 249);
		}
		logFileName = logName;
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
	
	public static void logCrashException(Exception e) {
		CameoUtils.popUpMessage(String.format("Export aborted due to %s.", e.getClass().getName()));
		logException(e);
	}
	
	public static void logSummary(HashSet<String> exportedElementIds) {
		log(String.format("\n------------ Exported %s Elements -----------\n", exportedElementIds.size()));
		
		TreeMap<String, Integer> typeCounts = new TreeMap<String, Integer>(Collections.reverseOrder());
		
		for (String elementId : exportedElementIds) {
			Element element = (Element) Application.getInstance().getProject().getElementByID(elementId);
			
			if (!typeCounts.containsKey(element.getHumanType())) {
				typeCounts.put(element.getHumanType(), 1);
				continue;
			}
			
			typeCounts.put(element.getHumanType(), typeCounts.get(element.getHumanType()) + 1);			
		}
		
		for (String elementType : typeCounts.descendingKeySet()) {
			log(String.format("%s: %s", elementType, Integer.toString(typeCounts.get(elementType))));
		}
	}

	public static void logMetrics(ExportMetrics em) {
		if (em.getElementCounts().isEmpty()) {
			logData.add("No elements imported.");
			return;
		}
		
		logData.add(horizontalLine);
		logData.add("Successfully exported element counts:\n\n");
		
		Object[] elementNames = em.getElementCounts().keySet().toArray();
		Arrays.sort(elementNames);
		
		int total = 0;
		for (Object elementName : elementNames) {
			int count = em.getElementCounts().get(elementName);
			total += count;
			
			logData.add((String)elementName + " " + Integer.toString(count) + "\n");
		}
		
		logData.add("\nTotal " + Integer.toString(total) + "\n");
		logData.add(horizontalLine);
	}
}
