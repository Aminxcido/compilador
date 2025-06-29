/**
 * generated by Xtext 2.38.0
 */
package org.xtext.example.arithmetic.arithmetic;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.arithmetic.arithmetic.Model#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.arithmetic.arithmetic.ArithmeticPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.arithmetic.arithmetic.Expression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference list.
   * @see org.xtext.example.arithmetic.arithmetic.ArithmeticPackage#getModel_Expr()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getExpr();

} // Model
