/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public class ValueNode implements Evaluable {

	private int value;

	public ValueNode(int value) {
		this.value = value;
	}

	@Override
	public int evaluate() {
		return value;
	}

	@Override
	public String representation() {
		return String.valueOf(value);
	}

}
