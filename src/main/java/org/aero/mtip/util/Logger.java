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
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import org.aero.mtip.io.Exporter;
import org.aero.mtip.io.Importer;
import org.apache.commons.io.FilenameUtils;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Logger {	
	static final String EXPORT = "Export";
	static final String IMPORT = "Import";
	static final String logFolder = "MTIP-Cameo Logs";
	
	static final String SUPPORTED = "Supported";
	static final String IMPLICIT = "Implicitly Supported";
	static final String UNSUPPORTED = "Unsupported";
	
	static Logger instance;
	
	Project project;
	String mode;
	Instant start;
    
	List<String> logData = new ArrayList<String> ();
	
	ArrayList<String> xmlFileNames = new ArrayList<String> ();
	
	public Logger(String mode) {
		this.start = java.time.Instant.now();
		this.mode = mode;
	}
	
	public static Logger createNewImportLogger() {
		instance = new Logger(IMPORT);
		return instance;
	}
	
	public static Logger createNewExportLogger() {
		instance = new Logger(EXPORT);
		return instance;
	}
	
	public static void save() {
		if (instance == null) {
			return;
		}
		
		instance.saveLog();
	}
	
	public static void destroy() {
		instance = null;
	}
	
	public void saveLog() {
		String savePath = getSavePath();
		
		if(logData.isEmpty()) {
			CameoUtils.logGui("No data logged.");
			return;
		}
		
		try {
			writeLines(savePath);
		} catch (IOException ioe) {
			CameoUtils.logExceptionToGui(ioe);
		}
		
		
	    CameoUtils.logGui(String.format("Log file saved to %s", savePath.toString()));
	}
	
	void writeLines(String savePath) throws IOException {
		FileWriter fw = new FileWriter(savePath, false);
		   
	    for(String line : logData) {
	    	fw.write(line);
	    }
	    
	    fw.close();
	}

	String createFileName() {
		String logName = String.format(
				"%s_%s",
				mode,
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()))
					.replace("-", "_")
					.replace(":", "_");

		for(String xmlFileName : xmlFileNames) {
			if(xmlFileName == null) {
				continue;
			}
			
			logName += String.format("_%s", FilenameUtils.removeExtension(xmlFileName));
		}
		
		logName += ".txt";
		
		if(logName.length() > 250) {
			logName = logName.substring(0, 249);
		}
		
		return logName;
	}
	
	public static void log(String message) {
		instance.logData.add(message + "\n");
	}
	
	public static void logException(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		
		log(sw.toString());
	}
	
	public static void logCrashException(Exception e) {
		CameoUtils.popUpMessage(String.format("Export aborted due to %s.", e.getClass().getName()));
		CameoUtils.logExceptionToGui(e);
		
		logException(e);
	}
	
	public static void logSummary(Importer importer) {
		instance.logImportedElements(importer.getImportedElementIds());
		instance.logUnsupportedElements(importer.getUnsupportedElementIds());
		instance.logDuration();
	}
	
	public static void logSummary(Exporter exporter) {
		instance.logExportedElements(exporter.getExportedElements());
		instance.logImplicitElements(exporter.getImplicitElements());
		instance.logUnsupportedElements(exporter.getUnsupportedElements());
		instance.logDuration();
	}
	
	public static void logConfigOptions() {
	  
	}
	
	public void logElements(Set<String> elementIds, String categoryMessage) {
		log(categoryMessage);
		
		TreeMap<String, Integer> typeCounts = new TreeMap<String, Integer>(Collections.reverseOrder());
		
		for (String elementId : elementIds) {
			Element element = (Element) Application.getInstance().getProject().getElementByID(elementId);			
			String elementType = MtipUtils.getEntityType(element);
			
			if (elementType == null) {
				elementType = MtipUtils.getCameoElementType(element);
			}
			
			if (!typeCounts.containsKey(elementType)) {
				typeCounts.put(elementType, 1);
				continue;
			}
			
			typeCounts.put(elementType, typeCounts.get(elementType) + 1);			
		}
		
		for (String elementType : typeCounts.descendingKeySet()) {
			log(String.format("%s: %s", elementType, Integer.toString(typeCounts.get(elementType))));
		}
	}
	
	public void logImportedElements(Set<String> importedElementIds) {
		String categoryMessage = String.format("\n------------ %sed %s Elements -----------\n", mode, importedElementIds.size());
		logElements(importedElementIds, categoryMessage);
	}
	
	public void logExportedElements(Set<String> exportedElementIds) {
		String categoryMessage = String.format("\n------------ %sed %s Elements -----------\n", mode, exportedElementIds.size());
		logElements(exportedElementIds, categoryMessage);
	}
	
	public void logImplicitElements(Set<String> implicitElementIds) {
		String categoryMessage = String.format("\n------------ %sed %s Elements Implicitly -----------\n", mode, implicitElementIds.size());
		logElements(implicitElementIds, categoryMessage);
	}
	
	public void logUnsupportedElements(Set<String> unsupportedElements) {
		String categoryMessage = String.format("\n------------ %s Elements Unsupported and not %sed -----------\n", unsupportedElements.size(), mode);
		logElements(unsupportedElements, categoryMessage);
	}
	
	String getElementTypeFromAmbiguousId(String id) {
	  if (mode.contentEquals(IMPORT) && Importer.isImportId(id)) {
	    return Importer.getTypeFromImportId(id);
	  }
	  
      Element element = (Element) Application.getInstance().getProject().getElementByID(id);
      
      if (element == null) {
        return null;
      }
      
      String elementType = MtipUtils.getEntityType(element);
      
      if (elementType == null) {
        elementType = element.getHumanType();
      }
      
      return elementType;
	}
	
	public static void logMultipleUafStereotypes(List<Stereotype> stereotypes, Element element) {
		String stereotypeNames = "";
		
		for (Stereotype stereotype : stereotypes) {
			stereotypeNames += CameoUtils.getElementName(stereotype) + " ";
		}
		
		log(String.format("Multiple stereotypes found for %s with id %s: %s", element.getHumanName(), MtipUtils.getId(element), stereotypeNames));
	}
	
	void logDuration() {
		Duration between = java.time.Duration.between(start, java.time.Instant.now());
		
		String timeStr = String.format("%02d:%02d:%02d.%04d \n",
		        between.toHours(), 
		        between.toMinutes() - between.toHours()*60, 
		        between.getSeconds() - between.toMinutes()*60, 
		        between.toMillis() - between.getSeconds()*1000);
		
		log(String.format("\n%sed in %s", mode, timeStr));
	}
	
	public static String getDocumentsPath() {
		return new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	}
	
	public String getSavePath() {
		File path = new File(Paths.get(getDocumentsPath(), logFolder, mode).toString());
		
		if (!path.exists()) {
			path.mkdirs();
		}
		
		return Paths.get(getDocumentsPath(), logFolder, mode, createFileName()).toString();
	}
}
