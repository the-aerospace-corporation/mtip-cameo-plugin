package org.aero.huddle.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportLog {
	protected List<String> logData;
	private final String logName = "Import Log " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".txt";
	
	public ImportLog() {
		logData = new ArrayList<String> ();
	}
	
	public void save() {
		try {
			File f = new File(logName);
			FileWriter fw = null;
			if(f.isFile()) { 
				fw = new FileWriter(logName, false); //the true will append the new data
			} else {
				fw = new FileWriter(logName, true);
			}
		   
		    for(String line : logData) {
		    	fw.write(line);
		    }
		    fw.close();
		}
		catch(IOException ioe) {
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public void log(String message) {
		logData.add(message);
	}
}
