/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public abstract class OperatorNode implements Evaluable {

	private char operator;
	protected Evaluable left;
	protected Evaluable right;

	public OperatorNode(char symbol) {
		this.operator = symbol;
	}

	@Override
	public String representation() {
		return "(" + left.representation() + " " + operator + " " + right.representation() + ")";
	}

	public void setLeft(Evaluable left) {
		this.left = left;
	}

	public void setRight(Evaluable right) {
		this.right = right;
	}

}
