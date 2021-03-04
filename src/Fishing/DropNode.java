package Fishing;

public class DropNode extends Node {
	public static final String NET = "Small fishing net";
	public static final String TINDERBOX = "Tinderbox";

	public DropNode(LumbFisher c) {
		super(c);
	}

	@Override
	public boolean validate() {
		return c.getInventory().isFull();
	}

	@Override
	public int execute() {
		c.getInventory().dropAllExcept(NET, TINDERBOX);
		return 1000;
	}

}
