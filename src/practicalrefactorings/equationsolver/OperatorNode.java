/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public class OperatorNode extends Node {

	public OperatorNode(char symbol) {
		super(symbol);
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

}
