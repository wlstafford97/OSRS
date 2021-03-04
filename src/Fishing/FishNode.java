package Fishing;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;

public class FishNode extends Node{

	public FishNode(LumbFisher c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate() {
		return !c.getInventory().isFull() && !c.getLocalPlayer().isAnimating();
	}

	@Override
	public int execute() {
		NPC fishingSpot = c.getNpcs().closest(item -> item != null && item.getName().contains("Fishing spot") && item.hasAction("Net"));
		fishingSpot.interact("Net");
		c.sleepUntil( () -> !c.getLocalPlayer().isAnimating(), Calculations.random(20000, 25000));
		return 1000;
	}

}
