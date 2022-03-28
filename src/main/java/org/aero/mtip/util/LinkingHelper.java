/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.util.HashMap;

// Helper class to manage linking elements in the XML based on UUID
public class LinkingHelper 
{

	private HashMap<String, org.w3c.dom.Element> systemUuidMap;
	private HashMap<String, org.w3c.dom.Element> interactionUuidMap;
	private HashMap<String, org.w3c.dom.Element> functionUuidMap;
	private HashMap<String, org.w3c.dom.Element> eventUuidMap;
	private HashMap<String, org.w3c.dom.Element> processUuidMap;
	private HashMap<String, org.w3c.dom.Element> outportUuidMap;
	private HashMap<String, org.w3c.dom.Element> eventTraceUuidMap;
	
	public LinkingHelper() 
	{
		systemUuidMap = new HashMap<String, org.w3c.dom.Element>();
		interactionUuidMap = new HashMap<String, org.w3c.dom.Element>();
		functionUuidMap = new HashMap<String, org.w3c.dom.Element>();
		eventUuidMap = new HashMap<String, org.w3c.dom.Element>();
		processUuidMap = new HashMap<String, org.w3c.dom.Element>();
		outportUuidMap = new HashMap<String, org.w3c.dom.Element>();
		eventTraceUuidMap = new HashMap<String, org.w3c.dom.Element>();
	}
	
	public void addMapping(String uuid, org.w3c.dom.Element element, XmlTagConstants.Type type)
	{
		switch(type)
		{
			case SYSTEM:
				systemUuidMap.put(uuid, element);
				break;
			case INTERACTION:
				interactionUuidMap.put(uuid, element);
				break;
			case FUNCTION:
				functionUuidMap.put(uuid, element);
				break;
			case EVENT:
				eventUuidMap.put(uuid, element);
				break;
			case PROCESS:
				processUuidMap.put(uuid, element);
				break;
			case OUTPORT:
				outportUuidMap.put(uuid, element);
				break;
			case EVENT_TRACE:
				eventTraceUuidMap.put(uuid, element);
				break;
			default:
				break;
		}
	}
	
	public org.w3c.dom.Element systemLookup(String uuid)
	{
		return systemUuidMap.get(uuid);
	}
	
	public org.w3c.dom.Element interactionLookup(String uuid)
	{
		return interactionUuidMap.get(uuid);
	}
	
	public org.w3c.dom.Element functionLookup(String uuid)
	{
		return functionUuidMap.get(uuid);
	}
	
	public org.w3c.dom.Element eventLookup(String uuid)
	{
		return eventUuidMap.get(uuid);
	}
	
	public org.w3c.dom.Element processLookup(String uuid)
	{
		return processUuidMap.get(uuid);
	}
	
	public org.w3c.dom.Element outportLookup(String uuid)
	{
		return outportUuidMap.get(uuid);
	}
	
	public org.w3c.dom.Element eventTraceLookup(String uuid)
	{
		return eventTraceUuidMap.get(uuid);
	}
}
