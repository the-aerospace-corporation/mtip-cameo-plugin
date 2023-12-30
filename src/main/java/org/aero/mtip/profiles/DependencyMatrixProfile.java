package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class DependencyMatrixProfile extends Profile {
	private static final String NAME = "Dependency Matrix Profile";
	
	private static final String MATRIX_FILTER_NAME = "MatrixFilter";
	
	private static Stereotype MATRIX_FILTER_STEREOTYPE;
	
	private static String COLUMN_ELEMENT_TYPE_NAME = "columnElementType";
	private static String ROW_ELEMENT_TYPE_NAME = "rowElementType";
	private static String COLUMN_SCOPE_NAME = "columnScope";
	private static String ROW_SCOPE_NAME = "rowScope";
	
	public DependencyMatrixProfile(Project project) {
		this.project = project;
		this.PROFILE = StereotypesHelper.getProfile(project, NAME);
	}
	
	public static void initialize(Project project) {
		instance = new DependencyMatrixProfile(project);
	}
	
	public static Stereotype getMatrixFilterStereotype() {
		if (MATRIX_FILTER_STEREOTYPE == null) {
			MATRIX_FILTER_STEREOTYPE = StereotypesHelper.getStereotype(instance.project, MATRIX_FILTER_NAME, instance.PROFILE);
		}
		
		return MATRIX_FILTER_STEREOTYPE;
	}
	
	public static Property getColumnElementTypeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), COLUMN_ELEMENT_TYPE_NAME);
	}
	
	public static Property getRowElementTypeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), ROW_ELEMENT_TYPE_NAME);
	}
	
	public static Property getColumnScopeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), COLUMN_SCOPE_NAME);
	}
	
	public static Property getRowScopeProperty() {
		return StereotypesHelper.getPropertyByName(getMatrixFilterStereotype(), ROW_SCOPE_NAME);
	}
	
	public static Slot getColumnElementTypeSlot(Element element) {
		return StereotypesHelper.getSlot(element, getColumnElementTypeProperty(), false);
	}
	
	public static Slot getRowElementTypeSlot(Element element) {
		return StereotypesHelper.getSlot(element, getRowElementTypeProperty(), false);
	}
	
	public static Slot getColumnScopeSlot(Element element) {
		return StereotypesHelper.getSlot(element, getColumnScopeProperty(), false);
	}
	
	public static Slot getRowScopeSlot(Element element) {
		return StereotypesHelper.getSlot(element, getRowScopeProperty(), false);
	}
}
