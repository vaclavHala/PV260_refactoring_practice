/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

import java.util.Stack;

public class OnTheFlyRPNEquationBuilder implements RPNEquationBuilder {

	private Stack<Evaluable> stack = new Stack<>();

	@Override
	public RPNEquationBuilder push(String token) {
		if (isNumber(token)) {
			pushNumber(token);
		} else {
			pushOperator(token);
		}
		return this;
	}

	private void pushNumber(String token) {
		int value = Integer.parseInt(token);
		Evaluable number = new ValueNode(value);
		stack.push(number);
	}

	private void pushOperator(String token) {
		if (token.length() == 1) {
			OperatorNode operator = createOperator(token.charAt(0));
			if (stack.isEmpty()) {
				throw new IllegalStateException("Nothing left on the stack for operand");
			}
			Evaluable right = stack.pop();
			if (stack.isEmpty()) {
				throw new IllegalStateException("Nothing left on the stack for operand");
			}
			Evaluable left = stack.pop();
			operator.setLeft(left);
			operator.setRight(right);
			stack.push(operator);
		} else {
			throw new IllegalArgumentException("Dont understand token: " + token);
		}
	}

	private boolean isNumber(String token) {
		try {
			Integer.parseInt(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private OperatorNode createOperator(char token) {
		switch (token) {
			case '+':
				return new PlusNode();
			case '-':
				return new MinusNode();
			case '*':
				return new MultNode();
			case '/':
				return new DivNode();
			default:
				throw new IllegalStateException("Unknown token: " + token);
		}
	}

	@Override
	public Evaluable build() {
		if (stack.size() != 1) {
			throw new IllegalStateException("More than one token left on the stack, unbalanced input.");
		}
		return stack.pop();
	}

}
