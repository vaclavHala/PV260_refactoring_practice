/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public class OperatorNode implements Evaluable {

	private char operator;
	private Evaluable left;
	private Evaluable right;

	public OperatorNode(char symbol) {
		this.operator = symbol;
	}

	@Override
	public int evaluate() {
		switch (operator) {
			case '+':
				return left.evaluate() + right.evaluate();
			case '-':
				return left.evaluate() - right.evaluate();
			case '*':
				return left.evaluate() * right.evaluate();
			case '/':
				return left.evaluate() / right.evaluate();
			default:
				throw new IllegalStateException("Unknown operator: " + operator);
		}
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
