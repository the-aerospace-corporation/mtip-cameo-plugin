package org.aero.huddle.util;

import com.nomagic.magicdraw.core.Application;

public class CameoUtils {
	public static void logGUI(String text) {
		Application.getInstance().getGUILog().log(text);
	}
}
