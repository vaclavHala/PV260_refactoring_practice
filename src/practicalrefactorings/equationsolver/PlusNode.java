/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public class PlusNode extends OperatorNode {

	public PlusNode() {
		super('+');
	}

	@Override
	public int evaluate() {
		return left.evaluate() + right.evaluate();
	}

}
