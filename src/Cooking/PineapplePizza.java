package Cooking;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.script.Category;
import java.awt.Color;
import java.awt.Graphics;


@ScriptManifest(author = "Lucas Stafford", name = "Pizza Maker", version = 1.0, description = "Pineapple Pizza", category = Category.COOKING)
public class PineapplePizza extends AbstractScript {
	
	public static final String GRAPES = "Grapes";
	public static final String JUG_OF_WATER = "Jug of water";
	public static final String JUG_OF_WINE = "Jug of wine";
	public static final String UNFERMENTED_WINE = "Unfermented wine";
	public static final String PLAIN_PIZZA = "Plain pizza";
	public static final String PINEAPPLE_RING = "Pineapple ring";
	public static final String PINEAPPLE_PIZZA = "Pineapple pizza";
	private Timer timer;
	private int wine;
	private int pizza;
	private int xp;

	@Override
    public void onStart() {
		timer = new Timer();
		wine = 0;
		pizza = 0;
		xp = (int)getSkillTracker().getGainedExperience(Skill.COOKING);
    }
 
    @Override
    public int onLoop() {
    	if(getInventory().contains(PLAIN_PIZZA) && getInventory().contains(PINEAPPLE_RING)) {
    		if(getBank().isOpen()) {
    			getBank().close();
    		} else {
    		getInventory().get(PLAIN_PIZZA).useOn(PINEAPPLE_RING);
    		sleep(Calculations.random(1000, 3000));
    		getWidgets().getWidgetChild(270, 14, 38).interact();
    		sleepUntil( () -> !getInventory().contains(PLAIN_PIZZA), Calculations.random(20000, 25000));
    		}
    	} else if (getBank().isOpen()) {
    		if(getInventory().contains(PINEAPPLE_PIZZA) || getInventory().contains(UNFERMENTED_WINE)) {
    			getBank().depositAllItems();
    			wine = wine + 14;
    		} else if (!getInventory().contains(PLAIN_PIZZA) || !getInventory().contains(PINEAPPLE_RING)) {
    			getBank().withdraw(PINEAPPLE_RING, 14);
    			getBank().withdraw(PLAIN_PIZZA, 14);
    		} else {
    			getBank().close();
    		}
    	} else {
    		getBank().openClosest();
    	}
    	return 1000;
    }
       
    @Override
    public void onExit() {
    }
    
    public void onPaint(Graphics g) {
    	g.setColor(Color.GREEN);
    	g.drawString("Timer: " + timer.formatTime(), 10, 35);
    	g.drawString("Pizza: " + (getSkillTracker().getGainedExperience(Skill.COOKING)-xp)/45, 10, 50);
    	g.drawString("Pizza/HR: " + timer.getHourlyRate((int)(getSkillTracker().getGainedExperience(Skill.COOKING)-xp)/45), 10, 65);
    }
}
