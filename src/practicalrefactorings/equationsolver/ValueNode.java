/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public class ValueNode extends Node {

	public ValueNode(int value) {
		super(value);
	}

	@Override
	public int evaluate() {
		return value;
	}

}
