package org.aero.huddle.csv;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.layout.LayoutManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.*;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.impl.ElementsFactory;
import com.opencsv.CSVReader;
import com.nomagic.magicdraw.core.project.ProjectsManager;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

///////////////////////		NOTICE		///////////////////////

//This class is depreciated and should not be used. It imports the diagram in the depreciated CSV format.
//It has been replaced by the XML import. It is being left here for reference and may be removed at any time.

///////////////////////		NOTICE		///////////////////////

@Deprecated
@SuppressWarnings("serial")
public class ImportFromHuddleAction extends MDAction
{
	public static final String SYSTEM = "System";
	public static final String INTERACTION = "Interaction";
	public static final String FUNCTION = "Function";
	public static final String EVENT = "Event";
	public static enum MDType {CLASS, ASSOCIATION, ACTIVITY, PARAMETER};
	
	private static final String PROFILE_URI = "http://huddle-csm.com/Huddle Stereotypes";
	
	public ImportFromHuddleAction(String id, String name)
	{
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e)
	{		
		//Open a file browser to select import file
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents" + System.getProperty("file.separator") + "Demo");
		int option = chooser.showOpenDialog(null);
		if(option == JFileChooser.APPROVE_OPTION)
		{
			if(chooser.getSelectedFile().getName().endsWith(".csv"))
			{
				ArrayList<ArrayList<String>> csv = parseCSV(chooser.getSelectedFile());
				ArrayList<String> header = csv.get(0);		
				
				//Get the project
				Project project = Application.getInstance().getProject();
				
				//If there is not an open project, notify
				if(project == null)
				{
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "No active project. Manually create a new project, then try again.");
				}
				else
				{
					//Create a new package for Huddle Elements
					com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg = createTopLevelPackage("Top-Level Package");
					
					//Create the profile for stereotypes
					Profile profile = createProfile("Huddle Stereotypes", pkg);
					
					//Create the system stereotype
					Stereotype s = createStereotype("system", profile);
					//Get tags for system elements
					ArrayList<String> tags = getTagDefinitions(getElements(csv, header, SYSTEM), header);
					//Add tag Definitions to the stereotype
					applyTagDefinitions(tags, s);
					
					//Populate the model with systems
					generateSystems(getElements(csv, header, SYSTEM), header, s, pkg);
					
					
					//Create the interaction stereotype
					s = createStereotype("interaction", profile);
					//Get tags for interaction elements
					tags = getTagDefinitions(getElements(csv, header, INTERACTION), header);
					//Add tag definitions to the stereotype
					applyTagDefinitions(tags, s);

					//Populate the interactions between systems
					generateInteractions(getElements(csv, header, INTERACTION), header, s, pkg);
					
					
					//Create the function stereotype
					s = createStereotype("function", profile);
					//Get tags for function elements
					tags = getTagDefinitions(getElements(csv, header, FUNCTION), header);
					//Add tag definitions to the stereotype
					applyTagDefinitions(tags, s);
					
					//Populate the systems with functions (i.e., activities)
					generateFunctions(getElements(csv, header, FUNCTION), header, s, pkg);
					
					//Create the event stereotype
					s = createStereotype("event", profile);
					//Get tags for event elements
					tags = getTagDefinitions(getElements(csv, header, EVENT), header);
					//Add tag definitions to the stereotype
					applyTagDefinitions(tags, s);
					
					//Populate the activities with parameters (i.e., events)
					generateEvents(getElements(csv, header, EVENT), header, s, pkg);
					
					//Create a diagram to show systems and interactions
					createDiagram(pkg);
				}
			}
		}
	}
	
	//Parse the CSV file
	//Returns ArrayList<ArrayList<String>>
	//Wrapper list contains the rows, inner list contains the row elements
	public static ArrayList<ArrayList<String>> parseCSV(File csvfile)
	{
		List<String[]> myentries = null;
		try
		{
			CSVReader reader = new CSVReader(new FileReader(csvfile));
			myentries = reader.readAll();
			reader.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		ArrayList<ArrayList<String>> csvline = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < myentries.size(); i++)
		{
			ArrayList<String> line = new ArrayList<String>(Arrays.asList(myentries.get(i)));
			csvline.add(line);
		}
		return csvline;
	}
	
	//Returns a list of any keys used by the provided elements (i.e., any CSV rows of the given element type that have a value associated with a key)
	public static ArrayList<String> getTagDefinitions(ArrayList<ArrayList<String>> elements, ArrayList<String> header)
	{
		ArrayList<String> tags = new ArrayList<String>();
		
		//For each key in the header
		for(String key : header)
		{
			//For each element row
			for(ArrayList<String> row : elements)
			{
				//If a row contains a value for the key and the key is not in tags
				if(!row.get(header.indexOf(key)).isEmpty() && !tags.contains(key))
				{
					//Add the key to tags and break
					tags.add(key);
					break;
				}
			}
		}
		
		return tags;
	}
	
	//Returns a list of elements from the CSV of the provided type (e.g., SYSTEM, INTERACTION)
	//The 'csv' parameter contains all rows of the CSV, header is the header row
	public static ArrayList<ArrayList<String>> getElements(ArrayList<ArrayList<String>> csv, ArrayList<String> header, String type)
	{
		//List of elements - contains all row information
		ArrayList<ArrayList<String>> elements = new ArrayList<ArrayList<String>>();
		
		//List of all current UUIDs in the elements list
		ArrayList<String> UUID = new ArrayList<String>();
		
		//For each row
		for (ArrayList<String> row : csv)
		{
			//If the row is of the correct type (should be at index 0)
			if(row.get(header.indexOf("PythontologyType")).contains(type))
			{
				//Check if the element is a duplicate
				boolean duplicate = false;
				for (String uuid : UUID)
				{
					//pyUUID should be at index 1
					if(row.get(header.indexOf("pyUUID")).equals(uuid))
					{
						duplicate = true;
						break;
					}
				}
				//If not, add the row to the list of elements. Add the UUID to the list of discovered UUIDs.
				if(duplicate == false)
				{
					elements.add(row);
					UUID.add(row.get(header.indexOf("pyUUID")));
				}
			}
		}
		
		return elements;
	}
	
	//Adds the pythontology systems to the model as blocks
	public static void generateSystems(ArrayList<ArrayList<String>> systems, ArrayList<String> header, Stereotype stereo, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{	
		//Determine the index of "Component" in the CSV row - this is the naming convention used in the test scenario
		//CSV not guaranteed to have "Component" keyword, the name value could be associated with a different keyword
		//Used later for assigning the class name
		int nameIndex = header.indexOf("Component");
		
		//Determine the index of "pyUUID" in the CSV row
		//"pyUUID" is guaranteed to be in the CSV - this is always included for every Huddle element
		int uuidIndex = header.indexOf("pyUUID");
		
		//Get the project
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Generate Systems");
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Populate Model with Systems");
			
			//MagicDraw helper classes
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Iterate through systems, adding each to model
			for(ArrayList<String> sys : systems)
			{
				//Create a new class instance and comment. Comment used to hold values not represented elsewhere in the model, but which must be retained for losslessness.
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class element = ef.createClassInstance();
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = ef.createCommentInstance();
				
				//If the csv contains "Component" (i.e., the name) use it, else use the assigned unique pythontology ID
				if(nameIndex != -1)
					element.setName(sys.get(nameIndex));
				else
					element.setName(sys.get(uuidIndex));
				
				//Apply the <<Block>> stereotype to the class element
				Stereotype block_stereotype = StereotypesHelper.getStereotype(project, "Block", StereotypesHelper.getProfile(project, "SysML"));
				if(block_stereotype != null)
				{
					StereotypesHelper.addStereotype(element, block_stereotype);
				}
				
				//Apply the secondary stereotype (i.e., <<system>>) and add the values to the stereotype tags
				if(StereotypesHelper.canApplyStereotype(element, stereo))
				{
					StereotypesHelper.addStereotype(element, stereo);
					applyTagValues(sys, header, element, stereo);
				}
				
				//Add the csv row information to the comment
				comment.setBody(pairCommentValues(sys, header, SYSTEM));
				
				//Add the comment to the element and the element to the package
				try
				{
					em.addElement(comment, element);
					em.addElement(element, pkg);
				}
				catch (ReadOnlyElementException roee) {
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Populate Model with Systems");
				}
			}
			
			SessionManager.getInstance().closeSession();
		}
	}
	
	//Adds the pythontology interactions to the model as associations
	public static void generateInteractions(ArrayList<ArrayList<String>> interactions, ArrayList<String> header, Stereotype stereo, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		//Determine the index of "Entity" in the CSV row
		//CSV not guaranteed to have "Entity" keyword, the name value could be associated with a different keyword
		//Used later for assigning the interaction name
		int nameIndex = header.indexOf("Entity");
		
		//Determine the index of "pyUUID", "System1", and "System2" in the CSV row
		//All three guaranteed to be in the CSV - "pyUUID" included for every Huddle element, and every interaction has two systems
		//"System1" and "System2" are the "pyUUID" values of the two systems
		int uuidIndex = header.indexOf("pyUUID");
		int system1Index = header.indexOf("SystemParticipant1");
		int system2Index = header.indexOf("SystemParticipant2");
		
		//Get the project
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Generate Interactions");
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Populate Model with Interactions");
			
			//MagicDraw helper classes
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Iterate through interactions, adding each to model
			for(ArrayList<String> inter : interactions)
			{
				//Create a new association instance and comment. Comment used to hold values not represented elsewhere in the model, but which must be retained for losslessness.
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association element = ef.createAssociationInstance();
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = ef.createCommentInstance();
				
				//If "Entity" keyword is in the CSV use it for the name, otherwise use "pyUUID"
				if(nameIndex != -1)
					element.setName(inter.get(nameIndex));
				else
					element.setName(inter.get(uuidIndex));
				
				//Retrieve all classes in the top-level package
				Collection<Element> classCollection = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class.class}, true);
				
				//From the list of classes, get the class by matching the 'SystemOwningFunction' pyUUID
				Element client = getElement(inter.get(system2Index), classCollection, "system");
				Element supplier = getElement(inter.get(system1Index), classCollection, "system");
				
				//Set the client and supplier for the association
				ModelHelper.setClientElement(element, client);
				ModelHelper.setSupplierElement(element, supplier);
				
				//Set the association to be navigable from both ends of the relationship by first getting the ends
				Property firstMemberEnd = ModelHelper.getFirstMemberEnd(element);
				Property secondMemberEnd = ModelHelper.getSecondMemberEnd(element);
				
				//To set associations as navigable, the roles may not have commas (it is an illegal character)
				//If not set prior to setting association navigable, CSM will try to make the class name the role even if it contains commas
				//This will generate warnings and will be inconsistent as those with commas will not have roles set
				//To make consistent and avoid warnings, use the class name but remove commas
				firstMemberEnd.setName(((NamedElement)supplier).getName().replaceAll(",", ""));
				secondMemberEnd.setName(((NamedElement)client).getName().replaceAll(",", ""));
				
				//Set navigable
				ModelHelper.setNavigable(firstMemberEnd, true);
				ModelHelper.setNavigable(secondMemberEnd, true);
				
				//Apply the secondary stereotype (i.e., <<interaction>>) and add values to stereotype tags
				if(StereotypesHelper.canApplyStereotype(element, stereo))
				{
					StereotypesHelper.addStereotype(element, stereo);
					applyTagValues(inter, header, element, stereo);
				}
				
				//Add the csv row information to the comment.
				comment.setBody(pairCommentValues(inter, header, INTERACTION));
				
				//Add the comment to the element and the element to the package
				try
				{
					em.addElement(comment, element);
					em.addElement(element, pkg);
				}
				catch (ReadOnlyElementException roee) {
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Populate Model with Systems");
				}
			}
			
			SessionManager.getInstance().closeSession();
		}
	}
	
	//Helper method. Generalized search for getting an single element from a collection of elements provided that element's pyUUID
	//Requires passing in the name of the stereotype describing the pythontology element (i.e., 'system', 'interaction', 'function', or 'event')
	//Required because a stereotype must be provided to StereotypesHelper to find the appropriate property of the provided stereotype (i.e., pyUUID)
	//Pythontology metadata properties are not a separate stereotype (i.e., there is no stereotype 'pythontology element'), but instead included with each pythontology stereotype
	public static Element getElement(String uuid, Collection<Element> elements, String stereotype)
	{
		//Element to return
		Element ret_element = null;
		
		//Get the stereotype in order to get the property
		Project project = Application.getInstance().getProject();
		Profile profile = StereotypesHelper.getProfileByURI(project, getProfileURI());
		Stereotype s = StereotypesHelper.getStereotype(project, stereotype, profile);
		
		//Iterate through the list of classes to find the one matching the system name
		for(Element element : elements)
		{
			//Get the pyUUID value
			Object value = StereotypesHelper.getStereotypePropertyFirst(element, s, "pyUUID");
			
			if(uuid.equals(value))
			{
				ret_element = element;
				break;
			}
		}
		
		return ret_element;
	}
	
	//Depreciated: replaced by provided MagicDraw API method Finder.byTypeRecursively.find(). See example below.
	//List<Element> classList = (List<Element>) Finder.byTypeRecursively().find(model, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class.class}, true);
	//
	//This method is left here for reference as a customized option for searching the model
	//Helper method, retrieve all elements of the given type (provided element and any children); support can be added for more element types
	//To get all the elements of a certain type within the model, pass this method the top level package
	//The MD API class and method to find elements by type:
	public static void getElementsByType(Element e, MDType type, List<Element> elementsOfType)
	{
		//com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package model = project.getPrimaryModel();
		//Get all the child elements of the element
		List<Element> children = (List<Element>) e.getOwnedElement();
		//If the child has children (i.e., grandchildren), call method recursively to get their children, and so on
		//Recursion ends once a child has no children
		if(!children.isEmpty())
		{
			for(Element child : children)
			{
				getElementsByType(child, type, elementsOfType);
			}
		}
		//To get here, either the element has no children or all child elements have been processed
		//Check the type of the element and add it to the elementsOfType list if valid
		//Note this may contain duplicates if the same element is defined twice in the model, however this is bad practice and should not be done
		switch (type)
		{
			case ASSOCIATION:
				if(e instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association)
					elementsOfType.add(e);
				break;
			case CLASS:
				//Also check if stereotype is of 'system' - this prevents all metaclasses derived from the MagicDraw class from being included in the list
				//Investigate if there is a MagicDraw OpenAPI method to retrieve all user defined classes (not metaclasses, other element types that derive from class)
				if(e instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class && StereotypesHelper.hasStereotype(e, "system", getProfileURI()))
					elementsOfType.add(e);
				break;
			case ACTIVITY:
				if(e instanceof com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity)
					elementsOfType.add(e);
				break;
			case PARAMETER:
				if(e instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter)
					elementsOfType.add(e);
				break;
			default:
				break;
		}
	}
	
	//Takes a row from the CSV, obtains the appropriate subset of information regarding a specific element, and returns a String of (key, value) pairs
	//Assumes that row and header are the same size
	public static String pairCommentValues(ArrayList<String> row, ArrayList<String> header, String type)
	{
		//Pair all values with their keys
		//LinkedHashMap to preserve relative order of key-value pairs
		LinkedHashMap<String, String> temp = new LinkedHashMap<String, String>();
		for(int i = 0; i < header.size(); i++)
		{
			temp.put(header.get(i), row.get(i));
		}
		
		//New map to return only values relevant to the particular element type
		//Override toString in order to apply '::' delimiter, not ','
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>() {
			public String toString()
			{
				String return_string = "";
				
				Iterator<String> key_iterator = this.keySet().iterator();
				while(key_iterator.hasNext())
				{
					String key = key_iterator.next();
					return_string = return_string.concat(key.toString() + "=" + this.get(key).toString());
					if(key_iterator.hasNext())
					{
						return_string = return_string.concat(" :: ");
						
					}
				}
				return return_string;
			}
		};
		
		//For each key, if there is a value, add it to the return value
		//For each key
		for(String key : temp.keySet())
		{
			//If the value is not empty (i.e., has a value)
			if(!temp.get(key).isEmpty())
			{
				//Add the key and value to the return value
				map.put(key, temp.get(key));
			}
		}
		
		return map.toString();
	}
	
	//Creates a diagram and adds all of the model elements with their relations to it
	//Utilizes helper method applyDiagramLayout to layout the diagram elements
	//Note this adds ALL classes to the diagram - it is not selective in any way
	public static void createDiagram(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Create Diagram");
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Create SysML BDD");
			ModelElementsManager em = ModelElementsManager.getInstance();
			PresentationElementsManager pm = PresentationElementsManager.getInstance();
			Diagram d = null;
			
			try
			{
				//Create the new diagram and add it to the base model
				d = em.createDiagram("SysML Block Definition Diagram", pkg);
				d.setName("BDD");
				
				//Element used to encapsulate all other presentation elements in the diagram
				DiagramPresentationElement diagram = project.getDiagram(d);
				
				//Retrieve all classes in the top-level package
				Collection<Element> classCollection = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class.class}, true);
				
				//Iterate through classes in model, adding each to the diagram
				//Associations are automatically added to the diagram
				for(Element system : classCollection)
				{
					pm.createShapeElement(system, diagram, true);
				}
			}
			catch(ReadOnlyElementException roee)
			{
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create SysML BDD");
			}
			
			SessionManager.getInstance().closeSession();
			
			applyDiagramLayout(d);
		}
	}
	
	//Applies layout to the currently opened diagram
	//If there is no open diagram, does nothing
	public static void applyDiagramLayout(Diagram d)
	{
		Project project = Application.getInstance().getProject();
		
		//Req: Must have active project
		//Req: No active session manager, per MagicDraw documentation
		if(project != null && !SessionManager.getInstance().isSessionCreated())
		{
			DiagramPresentationElement diagram = project.getDiagram(d);
			
			//Must have diagram open
			diagram.open();
			
			LayoutManager lm = com.nomagic.magicdraw.uml.symbols.layout.LayoutManager.getInstance();
			diagram.layout(true, lm.getDefaultLayouter("SysML Block Definition Diagram"));
		}
	}
	
	//Create a new package at the top level of the data model
	public static com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package createTopLevelPackage(String name)
	{		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Create Package: " + name);
		
		Project project = Application.getInstance().getProject();
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package model = project.getPrimaryModel();
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Create Top Level Package");
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new package
			pkg = ef.createPackageInstance();
			pkg.setName(name);
			pkg.setOwner(model);
			
			//Add the package to the model
			try
			{
				em.addElement(pkg, model);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Package");
			}
			
			SessionManager.getInstance().closeSession();
		}
		
		return pkg;
	}
	
	//Generate the pythontology functions as activities in the model
	public static void generateFunctions(ArrayList<ArrayList<String>> functions, ArrayList<String> header, Stereotype stereo, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		//Determine the index of "name" in the CSV format
		//Used later for assigning the activity (function) name
		int nameIndex = header.indexOf("name");
		
		//Determine the index of "pyUUID" and "SystemOwningFunction" in the CSV format
		//Every function will have a uuid identifier and is also 'owned' by a single system
		int uuidIndex = header.indexOf("pyUUID");
		int systemOwningFunctionIndex = header.indexOf("SystemOwningFunction");
		
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Generate Functions");
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Populate Model with Activities");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Iterate through functions, adding each to model
			for(ArrayList<String> func : functions)
			{
				Activity element = ef.createActivityInstance();
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = ef.createCommentInstance();
				
				//If the csv contains "name" (i.e., the name) use it, else use the assigned unique pythontology ID
				if(nameIndex != -1)
					element.setName(func.get(nameIndex));
				else
					element.setName(func.get(uuidIndex));
				
				//Apply the stereotype (i.e., <<function>>) and add the values to the stereotype tags
				if(StereotypesHelper.canApplyStereotype(element, stereo))
				{
					StereotypesHelper.addStereotype(element, stereo);
					applyTagValues(func, header, element, stereo);
				}
				
				//Add the csv row information to the comment.
				comment.setBody(pairCommentValues(func, header, FUNCTION));
				
				//Add the comment to the element and the element to the owning system (i.e., block)
				try
				{
					//Retrieve all classes in the top-level package
					Collection<Element> classCollection = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class.class}, true);
					
					//From the list of classes, get the class by matching the 'SystemOwningFunction' pyUUID
					Element owningSystem = getElement(func.get(systemOwningFunctionIndex), classCollection, "system");
					
					em.addElement(comment, element);
					em.addElement(element, owningSystem);
				}
				catch (ReadOnlyElementException roee) {
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Populate Model with Activities");
				}
			}
			
			SessionManager.getInstance().closeSession();
		}
	}
	
	//Generate the pythontology events as parameters in the model
	public static void generateEvents(ArrayList<ArrayList<String>> events, ArrayList<String> header, Stereotype stereo, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		//Determine the index of "Entity" in the CSV format
		//Used later for assigning the parameter (event) name
		int nameIndex = header.indexOf("Entity");
		
		//Determine the index of "pyUUID" and "SystemOwningFunction" in the CSV format
		//Every function will have a uuid identifier and is also 'owned' by a single system
		int uuidIndex = header.indexOf("pyUUID");
		int functionOwningEventIndex = header.indexOf("FunctionOwningEvent");
		
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Generate Events");
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Populate Model with Activities");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Iterate through functions, adding each to model
			for(ArrayList<String> event : events)
			{
				Parameter element = ef.createParameterInstance();
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment comment = ef.createCommentInstance();
				
				//If the csv contains "Entity" (i.e., the name) use it, else use the assigned unique pythontology ID
				if(nameIndex != -1)
					element.setName(event.get(nameIndex));
				else
					element.setName(event.get(uuidIndex));
				
				//Check and set the direction of the parameter (out or in) based on if the owning function output matches the event name
				//For demonstration purposes (and based on current data) all events are outputs, set to out
				element.setDirection(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ParameterDirectionKindEnum.OUT);
				
				//Apply the stereotype (i.e., <<event>>) and add the values to the stereotype tags
				if(StereotypesHelper.canApplyStereotype(element, stereo))
				{
					StereotypesHelper.addStereotype(element, stereo);
					applyTagValues(event, header, element, stereo);
				}
				
				//Add the csv row information to the comment.
				comment.setBody(pairCommentValues(event, header, FUNCTION));
				
				//Add the comment to the element and the element to the owning function (i.e., activity)
				try
				{					
					//Retrieve all activities in the top-level package
					Collection<Element> activityCollection = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity.class}, true);
					
					//From the list of activities, get the activity by matching the 'FunctionOwningEvent' pyUUID
					Element owningFunction = getElement(event.get(functionOwningEventIndex), activityCollection, "function");
					
					em.addElement(comment, element);
					em.addElement(element, owningFunction);
				}
				catch (ReadOnlyElementException roee) {
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Populate Model with Activities");
				}
			}
			
			SessionManager.getInstance().closeSession();
		}
	}
	
	//Creates a Profile element at the top level package with the provided name
	public static Profile createProfile(String name, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Create Profile: " + name);
		
		Profile profile = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated())
		{
			SessionManager.getInstance().createSession(project, "Create Profile");
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new profile, set its name, and set its owner to the top level package
			profile = ef.createProfileInstance();
			profile.setURI("http://huddle-csm.com/"+name);
			profile.setName(name);
			profile.setOwner(pkg);
			
			//Add the profile to the package
			try
			{
				em.addElement(profile, pkg);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Profile");
			}
			
			SessionManager.getInstance().closeSession();
		}
		
		return profile;
	}
	
	//Creates and returns a stereotype with the given name, applies it to the provided profile
	public static Stereotype createStereotype(String name, Profile profile)
	{
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Create Stereotype: " + name);
		Stereotype stereo = null;

		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated() && profile != null)
		{
			SessionManager.getInstance().createSession(project, "Create Stereotype");
			
			//Get the metaclass
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class metaclass = StereotypesHelper.getMetaClassByName(project, "Element");
			
			//Create the stereotype with name and metaclass set and apply to profile
			stereo = StereotypesHelper.createStereotype(profile, name, Arrays.asList(metaclass));
			
			SessionManager.getInstance().closeSession();
		}
		
		return stereo;
	}
	
	//Generate the tag definitions for a given stereotype from the collection of tag names
	public static void applyTagDefinitions(ArrayList<String> tags, Stereotype stereo)
	{
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Define Stereotype Tags: " + stereo.getName());
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated() && stereo != null)
		{
			SessionManager.getInstance().createSession(project, "Define Stereotype Tags");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//For each tag
			for(String name : tags)
			{
				//Create property (i.e., tag)
				Property tag = ef.createPropertyInstance();
				tag.setName(name);
				
				//Would set classifier type here if necessary
				
				//Add the tag to the stereotype
				try
				{
					em.addElement(tag, stereo);
				}
				catch (ReadOnlyElementException roee) {
					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Define Stereotype Tags");
				}
			}
			
			SessionManager.getInstance().closeSession();
		}
	}
	
	//Populates the element's stereotype tags with the values from the CSV row
	public static void applyTagValues(ArrayList<String> row, ArrayList<String> header, Element e, Stereotype s)
	{
		Set<Property> properties = StereotypesHelper.getPropertiesWithDerived(s);
		
		//Consider adding a check if the element has the correct stereotype here?
		
		//For each property
		for(Property property : properties)
		{
			//If it is contained in the header (excludes tags that may not be applicable)
			if(header.contains(property.getName()))
			{
				//Get the index of the property name in the header (e.g., UUID is index 1)
				int index = header.indexOf(property.getName());
				//Get the value from the same index for the row containing element values
				String value = row.get(index);
				//Set the property (tag) value to the row value
				StereotypesHelper.setStereotypePropertyValue(e, s, property.getName(), value);
			}
		}
	}
	
	//Helper method to get the profile URI, could be placed in separate helper class if desired
	public static String getProfileURI()
	{
		return PROFILE_URI;
	}
}
