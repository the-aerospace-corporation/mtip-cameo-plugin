package org.aero.mtip.profiles;

import org.aero.mtip.util.Logger;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class DependencyMatrixProfile {
	private static DependencyMatrixProfile instance;
	
	private Project project;
	private Profile profile;
	
	public static final String NAME = "Dependency Matrix Profile";
	
	private static final String MATRIX_FILTER_NAME = "MatrixFilter";
	private static Stereotype MATRIX_FILTER_STEREOTYPE;
	
	private static String COLUMN_ELEMENT_TYPE_NAME = "columnElementType";
	private static String ROW_ELEMENT_TYPE_NAME = "rowElementType";
	private static String COLUMN_SCOPE_NAME = "columnScope";
	private static String ROW_SCOPE_NAME = "rowScope";
	
	private DependencyMatrixProfile() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
	}
	
	public static DependencyMatrixProfile getInstance() {
		if (instance == null) {
			instance = new DependencyMatrixProfile();
		}
		
		return instance;
	}
	
	public Stereotype getMatrixFilterStereotype() {
		if (MATRIX_FILTER_STEREOTYPE == null) {
			MATRIX_FILTER_STEREOTYPE = StereotypesHelper.getStereotype(instance.project, MATRIX_FILTER_NAME, instance.profile);
		}
		
		return MATRIX_FILTER_STEREOTYPE;
	}
	
	private boolean hasStereotype(Element element, String stereotypeName) {
		if (profile == null) {
			Logger.log(String.format("Profile not initialized when looking for stereotype name %s", stereotypeName));
			return false;
		}
		
		Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
		
		if (stereotype == null) {
			Logger.log(String.format("Stereotype %s not found in profile %s", stereotypeName, profile.getHumanName()));
			return false;
		}
		
		if (!StereotypesHelper.hasStereotype(element, stereotype)) {
			return false;
		}
		
		return true;
	}
	
	public Property getColumnElementTypeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), COLUMN_ELEMENT_TYPE_NAME);
	}
	
	public Property getRowElementTypeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), ROW_ELEMENT_TYPE_NAME);
	}
	
	public Property getColumnScopeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), COLUMN_SCOPE_NAME);
	}
	
	public Property getRowScopeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), ROW_SCOPE_NAME);
	}
	
	// Update for 2021x
//	public Slot getColumnElementTypeSlot(Element element) {
//		return StereotypesHelper.getSlot(element, getColumnElementTypeProperty(), false);
//	}
//	
//	public Slot getRowElementTypeSlot(Element element) {
//		return StereotypesHelper.getSlot(element, getRowElementTypeProperty(), false);
//	}
//	
//	public Slot getColumnScopeSlot(Element element) {
//		return StereotypesHelper.getSlot(element, getColumnScopeProperty(), false);
//	}
//	
//	public Slot getRowScopeSlot(Element element) {
//		return StereotypesHelper.getSlot(element, getRowScopeProperty(), false);
//	}
}
