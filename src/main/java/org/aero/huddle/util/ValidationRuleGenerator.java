package org.aero.huddle.util;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.ModelElements.CommonElementsFactory;
import org.aero.huddle.XML.Import.ImportXmlSysml;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class ValidationRuleGenerator {
	private static final String STRUCTURED_EXPRESSION = "StructuredExpression";
	private static int compositionRuleCount = 0;
	private static int aggregationRuleCount = 0;
	
	private static Element modelValidationProfile;
	
	public void reset() {
		
	}
	
	public static Element createAggregationRule(Project project, Element validationProfile, Element supplier, Element client) {
		return null;
	}
	
	public static Element createCompositionRule(Project project, Element validationProfile, Element supplier, Element client) {
		CameoUtils.logGUI("\t...Creating new validation rule based on profile customization (relationship constraint.");
		
		CommonElementsFactory cef = new CommonElementsFactory();
		String body = createCompositionBody(supplier, client);
		
		CommonElement constraint = cef.createElement(SysmlConstants.CONSTRAINT, "CompositionConstraint" + Integer.toString(compositionRuleCount + 1), Integer.toString(compositionRuleCount + 1));
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint cameoConstraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)constraint.createElement(project, ImportXmlSysml.MODEL_VALIDATION_PACKAGE, null);
		
		CommonElement opaqueExpression = cef.createElement(SysmlConstants.OPAQUEEXPRESSION, "CompositionConstraint" + Integer.toString(compositionRuleCount + 1), Integer.toString(compositionRuleCount + 1));
		ValueSpecification cameoOpaqueExpression = (ValueSpecification) ((org.aero.huddle.ModelElements.Profile.OpaqueExpression)opaqueExpression).createElement(project, cameoConstraint, body, ValidationRuleGenerator.STRUCTURED_EXPRESSION);
		
		cameoConstraint.setSpecification(cameoOpaqueExpression);
		Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Association");
		cameoConstraint.getConstrainedElement().add(constrainedCameoElement);
		
		compositionRuleCount += 1;
		return cameoConstraint;
	}
	
	public static void setModelValidationProfile(Element mvp) {
		ValidationRuleGenerator.modelValidationProfile = mvp;
	}
	
	private static String createCompositionBody(Element supplier, Element client) {
		return ValidationRuleGenerator.compositionBody1 + supplier.getLocalID() + ValidationRuleGenerator.compositionBody2 + supplier.getLocalID() + ValidationRuleGenerator.compositionBody3 + client.getLocalID() + ValidationRuleGenerator.compositionBody4;
	}
	
	private static String createAggregationBody(Element supplier, Element client) {
		return ValidationRuleGenerator.aggregationBody1 + supplier.getLocalID() + ValidationRuleGenerator.aggregationBody2 + supplier.getLocalID() + ValidationRuleGenerator.aggregationBody3 + client.getLocalID() + ValidationRuleGenerator.aggregationBody4;
	}
	
	private static String compositionBody1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
			"<unionExpressionSpecification xmlns=\"http://www.nomagic.com/schemas/MagicDraw/StructuredExpression/2013\">\r\n" + 
			"    <taggedValues>\r\n" + 
			"        <entry key=\"name\">\r\n" + 
			"            <value>Body</value>\r\n" + 
			"        </entry>\r\n" + 
			"        <entry key=\"ROOT_UNION\">\r\n" + 
			"            <value>true</value>\r\n" + 
			"        </entry>\r\n" + 
			"    </taggedValues>\r\n" + 
			"    <value xsi:type=\"callExpressionSpecification\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"        <taggedValues>\r\n" + 
			"            <entry key=\"name\">\r\n" + 
			"                <value>Not1</value>\r\n" + 
			"            </entry>\r\n" + 
			"        </taggedValues>\r\n" + 
			"        <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"            <taggedValues>\r\n" + 
			"                <entry key=\"name\">\r\n" + 
			"                    <value>IsEmpty1</value>\r\n" + 
			"                </entry>\r\n" + 
			"            </taggedValues>\r\n" + 
			"            <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                <taggedValues>\r\n" + 
			"                    <entry key=\"name\">\r\n" + 
			"                        <value>Filter1</value>\r\n" + 
			"                    </entry>\r\n" + 
			"                </taggedValues>\r\n" + 
			"                <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                    <taggedValues>\r\n" + 
			"                        <entry key=\"name\">\r\n" + 
			"                            <value>Metachain Navigation1</value>\r\n" + 
			"                        </entry>\r\n" + 
			"                    </taggedValues>\r\n" + 
			"                    <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                        <taggedValues>\r\n" + 
			"                            <entry key=\"name\">\r\n" + 
			"                                <value>Filter</value>\r\n" + 
			"                            </entry>\r\n" + 
			"                        </taggedValues>\r\n" + 
			"                        <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                            <taggedValues>\r\n" + 
			"                                <entry key=\"name\">\r\n" + 
			"                                    <value>roles of association connected class </value>\r\n" + 
			"                                </entry>\r\n" + 
			"                            </taggedValues>\r\n" + 
			"                            <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"THIS\">\r\n" + 
			"                                <taggedValues>\r\n" + 
			"                                    <entry key=\"name\">\r\n" + 
			"                                        <value>Contextual Variable1</value>\r\n" + 
			"                                    </entry>\r\n" + 
			"                                </taggedValues>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <expression xsi:type=\"metaChainExpressionSpecification\">\r\n" + 
			"                                <chain xsi:type=\"umlProperty\" metaclass=\"Class\" property=\"_associationOfEndType\"/>\r\n" + 
			"                                <chain xsi:type=\"umlProperty\" metaclass=\"Association\" property=\"memberEnd\"/>\r\n" + 
			"                            </expression>\r\n" + 
			"                        </argument>\r\n" + 
			"                        <argument xsi:type=\"lambdaExpressionSpecification\">\r\n" + 
			"                            <parameter name=\"roles\" type=\"java.lang.Object\" ordered=\"false\" unique=\"false\" multiple=\"false\"/>\r\n" + 
			"                            <body xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                                <taggedValues>\r\n" + 
			"                                    <entry key=\"name\">\r\n" + 
			"                                        <value>Property Test1(Aggregation=composite)</value>\r\n" + 
			"                                    </entry>\r\n" + 
			"                                </taggedValues>\r\n" + 
			"                                <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"roles\"/>\r\n" + 
			"                                <argument xsi:type=\"stringExpressionSpecification\">\r\n" + 
			"                                    <value><?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			"\r\n" + 
			"<PropertyVisitorAcceptor>\r\n" + 
			"	<mdElement elementClass='PropertyManager'>\r\n" + 
			"		<mdElement elementClass='ChoiceProperty'>\r\n" + 
			"			<propertyID>QPROP:Element:aggregation</propertyID>\r\n" + 
			"			<value>composite</value>\r\n" + 
			"		</mdElement>\r\n" + 
			"	</mdElement>\r\n" + 
			"</PropertyVisitorAcceptor></value>\r\n" + 
			"                                </argument>\r\n" + 
			"                                <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                                <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                                <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                                <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                                <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                                    <element xsi:type=\"elementExpressionSpecification\" element=\"_18_5beta_8ca0285_1476867204509_502508_4139\"/>\r\n" + 
			"                                </expression>\r\n" + 
			"                            </body>\r\n" + 
			"                        </argument>\r\n" + 
			"                        <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                            <element xsi:type=\"elementExpressionSpecification\" element=\"_18_0beta_94c0290_1392135311908_183621_3586\"/>\r\n" + 
			"                        </expression>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <expression xsi:type=\"metaChainExpressionSpecification\">\r\n" + 
			"                        <chain xsi:type=\"umlProperty\" metaclass=\"Property\" property=\"UMLClass\"/>\r\n" + 
			"                    </expression>\r\n" + 
			"                </argument>\r\n" + 
			"                <argument xsi:type=\"lambdaExpressionSpecification\">\r\n" + 
			"                    <parameter name=\"classes\" type=\"java.lang.Object\" ordered=\"false\" unique=\"false\" multiple=\"false\"/>\r\n" + 
			"                    <body xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                        <taggedValues>\r\n" + 
			"                            <entry key=\"name\">\r\n" + 
			"                                <value>Property Test1(Applied Stereotype=bus)</value>\r\n" + 
			"                            </entry>\r\n" + 
			"                        </taggedValues>\r\n" + 
			"                        <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"classes\"/>\r\n" + 
			"                        <argument xsi:type=\"stringExpressionSpecification\">\r\n" + 
			"                            <value><?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			"\r\n" + 
			"<PropertyVisitorAcceptor>\r\n" + 
			"	<mdElement elementClass='PropertyManager'>\r\n" + 
			"		<mdElement elementClass='ElementListProperty'>\r\n" + 
			"			<propertyID>QPROP:Element:APPLIED_STEREOTYPES</propertyID>\r\n" + 
			"			<selectableTypes xmi:value='Stereotype'/>\r\n" + 
			"			<containment xmi:value='false'/>\r\n" + 
			"			<ordered xmi:value='false'/>\r\n" + 
			"			<value>";
	
	private static String compositionBody2 = "</value>\r\n" + 
			"		</mdElement>\r\n" + 
			"	</mdElement>\r\n" + 
			"</PropertyVisitorAcceptor></value>\r\n" + 
			"                        </argument>\r\n" + 
			"                        <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                        <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                        <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                        <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                        <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                            <element xsi:type=\"elementExpressionSpecification\" element=\"_18_5beta_8ca0285_1476867204509_502508_4139\"/>\r\n" + 
			"                        </expression>\r\n" + 
			"                    </body>\r\n" + 
			"                </argument>\r\n" + 
			"                <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                    <element xsi:type=\"elementExpressionSpecification\" element=\"_18_0beta_94c0290_1392135311908_183621_3586\"/>\r\n" + 
			"                </expression>\r\n" + 
			"            </argument>\r\n" + 
			"            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                <element xsi:type=\"elementExpressionSpecification\" element=\"";
	
	private static String compositionBody3 = "\"/>\r\n" + 
			"            </expression>\r\n" + 
			"        </argument>\r\n" + 
			"        <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"            <element xsi:type=\"elementExpressionSpecification\" element=\"";
	
	private static String compositionBody4 = "\"/>\r\n" + 
			"        </expression>\r\n" + 
			"    </value>\r\n" + 
			"</unionExpressionSpecification>";
	
	private static String aggregationBody1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
			"<unionExpressionSpecification xmlns=\"http://www.nomagic.com/schemas/MagicDraw/StructuredExpression/2013\">\r\n" + 
			"    <taggedValues>\r\n" + 
			"        <entry key=\"name\">\r\n" + 
			"            <value>Body</value>\r\n" + 
			"        </entry>\r\n" + 
			"        <entry key=\"ROOT_UNION\">\r\n" + 
			"            <value>true</value>\r\n" + 
			"        </entry>\r\n" + 
			"    </taggedValues>\r\n" + 
			"    <value xsi:type=\"callExpressionSpecification\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n" + 
			"        <taggedValues>\r\n" + 
			"            <entry key=\"name\">\r\n" + 
			"                <value>IfThenElse1</value>\r\n" + 
			"            </entry>\r\n" + 
			"        </taggedValues>\r\n" + 
			"        <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"            <taggedValues>\r\n" + 
			"                <entry key=\"name\">\r\n" + 
			"                    <value>IfThenElse2</value>\r\n" + 
			"                </entry>\r\n" + 
			"            </taggedValues>\r\n" + 
			"            <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                <taggedValues>\r\n" + 
			"                    <entry key=\"name\">\r\n" + 
			"                        <value>IsEmpty1</value>\r\n" + 
			"                    </entry>\r\n" + 
			"                </taggedValues>\r\n" + 
			"                <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                    <taggedValues>\r\n" + 
			"                        <entry key=\"name\">\r\n" + 
			"                            <value>Filter1</value>\r\n" + 
			"                        </entry>\r\n" + 
			"                    </taggedValues>\r\n" + 
			"                    <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                        <taggedValues>\r\n" + 
			"                            <entry key=\"name\">\r\n" + 
			"                                <value>Metachain Navigation1</value>\r\n" + 
			"                            </entry>\r\n" + 
			"                        </taggedValues>\r\n" + 
			"                        <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"THIS\"/>\r\n" + 
			"                        <expression xsi:type=\"metaChainExpressionSpecification\">\r\n" + 
			"                            <chain xsi:type=\"umlProperty\" metaclass=\"Association\" property=\"memberEnd\"/>\r\n" + 
			"                            <chain xsi:type=\"umlProperty\" metaclass=\"Property\" property=\"type\"/>\r\n" + 
			"                        </expression>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <argument xsi:type=\"lambdaExpressionSpecification\">\r\n" + 
			"                        <parameter name=\"ends\" type=\"java.lang.Object\" ordered=\"false\" unique=\"false\" multiple=\"false\"/>\r\n" + 
			"                        <body xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                            <taggedValues>\r\n" + 
			"                                <entry key=\"name\">\r\n" + 
			"                                    <value>Property Test1(Applied Stereotype=bus)</value>\r\n" + 
			"                                </entry>\r\n" + 
			"                            </taggedValues>\r\n" + 
			"                            <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"ends\">\r\n" + 
			"                                <taggedValues>\r\n" + 
			"                                    <entry key=\"name\">\r\n" + 
			"                                        <value>Contextual Variable1</value>\r\n" + 
			"                                    </entry>\r\n" + 
			"                                </taggedValues>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <argument xsi:type=\"stringExpressionSpecification\">\r\n" + 
			"                                <value><?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			"\r\n" + 
			"<PropertyVisitorAcceptor>\r\n" + 
			"	<mdElement elementClass='PropertyManager'>\r\n" + 
			"		<mdElement elementClass='ElementListProperty'>\r\n" + 
			"			<propertyID>QPROP:Element:APPLIED_STEREOTYPES</propertyID>\r\n" + 
			"			<selectableTypes xmi:value='Stereotype'/>\r\n" + 
			"			<containment xmi:value='false'/>\r\n" + 
			"			<ordered xmi:value='false'/>\r\n" + 
			"			<value>";
	private static String aggregationBody2 = "</value>\r\n" + 
			"		</mdElement>\r\n" + 
			"	</mdElement>\r\n" + 
			"</PropertyVisitorAcceptor></value>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                                <element xsi:type=\"elementExpressionSpecification\" element=\"_18_5beta_8ca0285_1476867204509_502508_4139\"/>\r\n" + 
			"                            </expression>\r\n" + 
			"                        </body>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                        <element xsi:type=\"elementExpressionSpecification\" element=\"_18_0beta_94c0290_1392135311908_183621_3586\"/>\r\n" + 
			"                    </expression>\r\n" + 
			"                </argument>\r\n" + 
			"                <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                    <element xsi:type=\"elementExpressionSpecification\" element=\"_19_0beta_8ca0285_1502101213659_300258_4511\"/>\r\n" + 
			"                </expression>\r\n" + 
			"            </argument>\r\n" + 
			"            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\">\r\n" + 
			"                <taggedValues>\r\n" + 
			"                    <entry key=\"name\">\r\n" + 
			"                        <value>Boolean1</value>\r\n" + 
			"                    </entry>\r\n" + 
			"                </taggedValues>\r\n" + 
			"            </argument>\r\n" + 
			"            <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                <taggedValues>\r\n" + 
			"                    <entry key=\"name\">\r\n" + 
			"                        <value>IsEmpty1</value>\r\n" + 
			"                    </entry>\r\n" + 
			"                </taggedValues>\r\n" + 
			"                <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                    <taggedValues>\r\n" + 
			"                        <entry key=\"name\">\r\n" + 
			"                            <value>Filter1</value>\r\n" + 
			"                        </entry>\r\n" + 
			"                    </taggedValues>\r\n" + 
			"                    <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                        <taggedValues>\r\n" + 
			"                            <entry key=\"name\">\r\n" + 
			"                                <value>Exclude1</value>\r\n" + 
			"                            </entry>\r\n" + 
			"                        </taggedValues>\r\n" + 
			"                        <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                            <taggedValues>\r\n" + 
			"                                <entry key=\"name\">\r\n" + 
			"                                    <value>Metachain Navigation1</value>\r\n" + 
			"                                </entry>\r\n" + 
			"                            </taggedValues>\r\n" + 
			"                            <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"THIS\"/>\r\n" + 
			"                            <expression xsi:type=\"metaChainExpressionSpecification\">\r\n" + 
			"                                <chain xsi:type=\"umlProperty\" metaclass=\"Association\" property=\"memberEnd\"/>\r\n" + 
			"                                <chain xsi:type=\"umlProperty\" metaclass=\"Property\" property=\"type\"/>\r\n" + 
			"                            </expression>\r\n" + 
			"                        </argument>\r\n" + 
			"                        <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                            <taggedValues>\r\n" + 
			"                                <entry key=\"name\">\r\n" + 
			"                                    <value>Filter1</value>\r\n" + 
			"                                </entry>\r\n" + 
			"                            </taggedValues>\r\n" + 
			"                            <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                                <taggedValues>\r\n" + 
			"                                    <entry key=\"name\">\r\n" + 
			"                                        <value>Metachain Navigation1</value>\r\n" + 
			"                                    </entry>\r\n" + 
			"                                </taggedValues>\r\n" + 
			"                                <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"THIS\"/>\r\n" + 
			"                                <expression xsi:type=\"metaChainExpressionSpecification\">\r\n" + 
			"                                    <chain xsi:type=\"umlProperty\" metaclass=\"Association\" property=\"memberEnd\"/>\r\n" + 
			"                                    <chain xsi:type=\"umlProperty\" metaclass=\"Property\" property=\"type\"/>\r\n" + 
			"                                </expression>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <argument xsi:type=\"lambdaExpressionSpecification\">\r\n" + 
			"                                <parameter name=\"ends\" type=\"java.lang.Object\" ordered=\"false\" unique=\"false\" multiple=\"false\"/>\r\n" + 
			"                                <body xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                                    <taggedValues>\r\n" + 
			"                                        <entry key=\"name\">\r\n" + 
			"                                            <value>Property Test1(Applied Stereotype=bus)</value>\r\n" + 
			"                                        </entry>\r\n" + 
			"                                    </taggedValues>\r\n" + 
			"                                    <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"ends\">\r\n" + 
			"                                        <taggedValues>\r\n" + 
			"                                            <entry key=\"name\">\r\n" + 
			"                                                <value>Contextual Variable1</value>\r\n" + 
			"                                            </entry>\r\n" + 
			"                                        </taggedValues>\r\n" + 
			"                                    </argument>\r\n" + 
			"                                    <argument xsi:type=\"stringExpressionSpecification\">\r\n" + 
			"                                        <value><?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			"\r\n" + 
			"<PropertyVisitorAcceptor>\r\n" + 
			"	<mdElement elementClass='PropertyManager'>\r\n" + 
			"		<mdElement elementClass='ElementListProperty'>\r\n" + 
			"			<propertyID>QPROP:Element:APPLIED_STEREOTYPES</propertyID>\r\n" + 
			"			<selectableTypes xmi:value='Stereotype'/>\r\n" + 
			"			<containment xmi:value='false'/>\r\n" + 
			"			<ordered xmi:value='false'/>\r\n" + 
			"			<value>";
	private static String aggregationBody3 = "</value>\r\n" + 
			"		</mdElement>\r\n" + 
			"	</mdElement>\r\n" + 
			"</PropertyVisitorAcceptor></value>\r\n" + 
			"                                    </argument>\r\n" + 
			"                                    <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                                    <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                                    <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                                    <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                                    <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                                        <element xsi:type=\"elementExpressionSpecification\" element=\"_18_5beta_8ca0285_1476867204509_502508_4139\"/>\r\n" + 
			"                                    </expression>\r\n" + 
			"                                </body>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                                <element xsi:type=\"elementExpressionSpecification\" element=\"_18_0beta_94c0290_1392135311908_183621_3586\"/>\r\n" + 
			"                            </expression>\r\n" + 
			"                        </argument>\r\n" + 
			"                        <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                            <element xsi:type=\"elementExpressionSpecification\" element=\"_17_0_5beta_f720368_1373897929113_784143_3302\"/>\r\n" + 
			"                        </expression>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <argument xsi:type=\"lambdaExpressionSpecification\">\r\n" + 
			"                        <parameter name=\"ends\" type=\"java.lang.Object\" ordered=\"false\" unique=\"false\" multiple=\"false\"/>\r\n" + 
			"                        <body xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                            <taggedValues>\r\n" + 
			"                                <entry key=\"name\">\r\n" + 
			"                                    <value>Property Test1(Applied Stereotype=subsystem)</value>\r\n" + 
			"                                </entry>\r\n" + 
			"                            </taggedValues>\r\n" + 
			"                            <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"ends\">\r\n" + 
			"                                <taggedValues>\r\n" + 
			"                                    <entry key=\"name\">\r\n" + 
			"                                        <value>Contextual Variable1</value>\r\n" + 
			"                                    </entry>\r\n" + 
			"                                </taggedValues>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <argument xsi:type=\"stringExpressionSpecification\">\r\n" + 
			"                                <value><?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			"\r\n" + 
			"<PropertyVisitorAcceptor>\r\n" + 
			"	<mdElement elementClass='PropertyManager'>\r\n" + 
			"		<mdElement elementClass='ElementListProperty'>\r\n" + 
			"			<propertyID>QPROP:Element:APPLIED_STEREOTYPES</propertyID>\r\n" + 
			"			<selectableTypes xmi:value='Stereotype'/>\r\n" + 
			"			<containment xmi:value='false'/>\r\n" + 
			"			<ordered xmi:value='false'/>\r\n" + 
			"			<value>";
	private static String aggregationBody4 = "</value>\r\n" + 
			"		</mdElement>\r\n" + 
			"	</mdElement>\r\n" + 
			"</PropertyVisitorAcceptor></value>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                                <element xsi:type=\"elementExpressionSpecification\" element=\"_18_5beta_8ca0285_1476867204509_502508_4139\"/>\r\n" + 
			"                            </expression>\r\n" + 
			"                        </body>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                        <element xsi:type=\"elementExpressionSpecification\" element=\"_18_0beta_94c0290_1392135311908_183621_3586\"/>\r\n" + 
			"                    </expression>\r\n" + 
			"                </argument>\r\n" + 
			"                <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                    <element xsi:type=\"elementExpressionSpecification\" element=\"_19_0beta_8ca0285_1502101213659_300258_4511\"/>\r\n" + 
			"                </expression>\r\n" + 
			"            </argument>\r\n" + 
			"            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                <element xsi:type=\"elementExpressionSpecification\" element=\"_19_0beta_8ca0285_1502101213659_239801_4510\"/>\r\n" + 
			"            </expression>\r\n" + 
			"        </argument>\r\n" + 
			"        <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\">\r\n" + 
			"            <taggedValues>\r\n" + 
			"                <entry key=\"name\">\r\n" + 
			"                    <value>Boolean1</value>\r\n" + 
			"                </entry>\r\n" + 
			"            </taggedValues>\r\n" + 
			"        </argument>\r\n" + 
			"        <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"            <taggedValues>\r\n" + 
			"                <entry key=\"name\">\r\n" + 
			"                    <value>Not1</value>\r\n" + 
			"                </entry>\r\n" + 
			"            </taggedValues>\r\n" + 
			"            <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                <taggedValues>\r\n" + 
			"                    <entry key=\"name\">\r\n" + 
			"                        <value>IsEmpty1</value>\r\n" + 
			"                    </entry>\r\n" + 
			"                </taggedValues>\r\n" + 
			"                <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                    <taggedValues>\r\n" + 
			"                        <entry key=\"name\">\r\n" + 
			"                            <value>Filter1</value>\r\n" + 
			"                        </entry>\r\n" + 
			"                    </taggedValues>\r\n" + 
			"                    <argument xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                        <taggedValues>\r\n" + 
			"                            <entry key=\"name\">\r\n" + 
			"                                <value>Metachain Navigation</value>\r\n" + 
			"                            </entry>\r\n" + 
			"                        </taggedValues>\r\n" + 
			"                        <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"THIS\"/>\r\n" + 
			"                        <expression xsi:type=\"metaChainExpressionSpecification\">\r\n" + 
			"                            <chain xsi:type=\"umlProperty\" metaclass=\"Association\" property=\"memberEnd\"/>\r\n" + 
			"                        </expression>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <argument xsi:type=\"lambdaExpressionSpecification\">\r\n" + 
			"                        <parameter name=\"association end\" type=\"java.lang.Object\" ordered=\"false\" unique=\"false\" multiple=\"false\"/>\r\n" + 
			"                        <body xsi:type=\"callExpressionSpecification\">\r\n" + 
			"                            <taggedValues>\r\n" + 
			"                                <entry key=\"name\">\r\n" + 
			"                                    <value>Property Test1(Aggregation=composite)</value>\r\n" + 
			"                                </entry>\r\n" + 
			"                            </taggedValues>\r\n" + 
			"                            <argument xsi:type=\"lookupExpressionSpecification\" symbol=\"association end\"/>\r\n" + 
			"                            <argument xsi:type=\"stringExpressionSpecification\">\r\n" + 
			"                                <value><?xml version='1.0' encoding='UTF-8'?>\r\n" + 
			"\r\n" + 
			"<PropertyVisitorAcceptor>\r\n" + 
			"	<mdElement elementClass='PropertyManager'>\r\n" + 
			"		<mdElement elementClass='ChoiceProperty'>\r\n" + 
			"			<propertyID>QPROP:Element:aggregation</propertyID>\r\n" + 
			"			<value>composite</value>\r\n" + 
			"		</mdElement>\r\n" + 
			"	</mdElement>\r\n" + 
			"</PropertyVisitorAcceptor></value>\r\n" + 
			"                            </argument>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"false\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                            <argument xsi:type=\"booleanExpressionSpecification\" value=\"true\"/>\r\n" + 
			"                            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                                <element xsi:type=\"elementExpressionSpecification\" element=\"_18_5beta_8ca0285_1476867204509_502508_4139\"/>\r\n" + 
			"                            </expression>\r\n" + 
			"                        </body>\r\n" + 
			"                    </argument>\r\n" + 
			"                    <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                        <element xsi:type=\"elementExpressionSpecification\" element=\"_18_0beta_94c0290_1392135311908_183621_3586\"/>\r\n" + 
			"                    </expression>\r\n" + 
			"                </argument>\r\n" + 
			"                <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                    <element xsi:type=\"elementExpressionSpecification\" element=\"_19_0beta_8ca0285_1502101213659_300258_4511\"/>\r\n" + 
			"                </expression>\r\n" + 
			"            </argument>\r\n" + 
			"            <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"                <element xsi:type=\"elementExpressionSpecification\" element=\"_19_0beta_8ca0285_1502101213659_906613_4522\"/>\r\n" + 
			"            </expression>\r\n" + 
			"        </argument>\r\n" + 
			"        <expression xsi:type=\"interpretElementExpressionSpecification\">\r\n" + 
			"            <element xsi:type=\"elementExpressionSpecification\" element=\"_19_0beta_8ca0285_1502101213659_239801_4510\"/>\r\n" + 
			"        </expression>\r\n" + 
			"    </value>\r\n" + 
			"</unionExpressionSpecification>\r\n";
}
