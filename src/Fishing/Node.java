package Fishing;

public abstract class Node {
	protected final LumbFisher c;
	
	public Node(LumbFisher c) {
		this.c = c;
	}	
	public abstract boolean validate();
	public abstract int execute();

}
