/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public abstract class Node implements Evaluable {

	private boolean isNumber;
	protected int value;
	protected char operator;
	protected Node left;
	protected Node right;

	/** Construct number node */
	public Node(int value) {
		this.isNumber = true;
		this.value = value;
	}

	/** Construc operator node */
	public Node(char symbol) {
		this.isNumber = false;
		this.operator = symbol;
	}

}
