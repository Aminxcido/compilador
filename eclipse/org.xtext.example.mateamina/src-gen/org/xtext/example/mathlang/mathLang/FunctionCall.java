/**
 * generated by Xtext 2.38.0
 */
package org.xtext.example.mathlang.mathLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mathlang.mathLang.FunctionCall#getFunc <em>Func</em>}</li>
 *   <li>{@link org.xtext.example.mathlang.mathLang.FunctionCall#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mathlang.mathLang.MathLangPackage#getFunctionCall()
 * @model
 * @generated
 */
public interface FunctionCall extends Expression
{
  /**
   * Returns the value of the '<em><b>Func</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Func</em>' attribute.
   * @see #setFunc(String)
   * @see org.xtext.example.mathlang.mathLang.MathLangPackage#getFunctionCall_Func()
   * @model
   * @generated
   */
  String getFunc();

  /**
   * Sets the value of the '{@link org.xtext.example.mathlang.mathLang.FunctionCall#getFunc <em>Func</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Func</em>' attribute.
   * @see #getFunc()
   * @generated
   */
  void setFunc(String value);

  /**
   * Returns the value of the '<em><b>Args</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mathlang.mathLang.Expression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Args</em>' containment reference list.
   * @see org.xtext.example.mathlang.mathLang.MathLangPackage#getFunctionCall_Args()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getArgs();

} // FunctionCall
