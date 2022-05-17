package org.aero.mtip.util;

import java.util.HashMap;
import java.util.List;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

// Helper class to retrieve an element's stereotype properties.
public class PropertyCollector
{
	//Used to indicate if two stereotypes have the same property name (i.e., key)
	private boolean duplicates;
	private HashMap<String, Object> characterizations;
	
	public PropertyCollector(Element e)
	{
		this.duplicates = false;
		this.characterizations = new HashMap<String, Object>();
		this.collectHuddleStereotypes(e);
	}
	
	private void collectHuddleStereotypes(Element e)
	{	
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(e);

		//Get all the huddle stereotypes
		Profile profile = Finder.byNameRecursively().find(
				Application.getInstance().getProject().getPrimaryModel(),
				new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile.class},
				"Huddle Stereotypes");
		List<Stereotype> huddleStereotypes = StereotypesHelper.getStereotypesByProfile(profile);
		
		//For each stereotype
		for(Stereotype stereotype : stereotypes)
		{
			//If the stereotype is a huddle stereotype
			if(huddleStereotypes.contains(stereotype))
			{
				//Get the properties (tags)
				List<Property> properties = StereotypesHelper.getPropertiesWithDerivedOrdered(stereotype);
				
				//For each property
				for(Property property : properties)
				{
					//Check if the tag name is already contained in characterizations key set. If it contains the tag name, set boolean to true.
					if(characterizations.containsKey(property.getName()))
						this.duplicates = true;
					//Else add it to the collection (excluding "base_Element" - this makes it so multiple stereotypes can be exported; all have base element)
					else if(!characterizations.containsKey(property.getName()) && !property.getName().equals("base_Element"))
					{
						Object value = StereotypesHelper.getStereotypePropertyFirst(e, stereotype, property.getName());
						this.characterizations.put(property.getName(), value);
					}
				}
			}
		}
	}
	
	public boolean duplicatesExist()
	{
		return this.duplicates;
	}
	
	public HashMap<String, Object> getPropertyCollection()
	{
		return this.characterizations;
	}
}
