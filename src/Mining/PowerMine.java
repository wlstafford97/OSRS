package Mining;

import java.awt.Color;
import java.awt.Graphics;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.script.Category;
@ScriptManifest(author = "Lucas Stafford", name = "PowerMine", version = 1.0, description = "Varrock Iron Miner", category = Category.MINING)
public class PowerMine extends AbstractScript{
	private Timer timer;
	private int xp;

	@Override
    public void onStart() {
		timer = new Timer();
		xp = (int)getSkillTracker().getGainedExperience(Skill.MINING);
    }
	
	@Override
	public int onLoop(){
        GameObject myORE = getGameObjects().closest(ore -> ore.getID() == 11365);
        if (!getInventory().isFull()) {
        	if(getLocalPlayer().isAnimating()) {
        		//do nothing
        	} else {
                myORE.interact("Mine");
                sleepUntil( () -> getLocalPlayer().isAnimating(), Calculations.random(5000, 15000));
        	}
        } else {
                getInventory().dropAll();
        }
        sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(5000, 15000));
        return 500;
    }
	
	@Override
    public void onExit() {
    }
	
	public void onPaint(Graphics g) {
    	g.setColor(Color.GREEN);
    	g.drawString("Timer: " + timer.formatTime(), 10, 35);
    	g.drawString("IRON/HR: " + timer.getHourlyRate((int)(getSkillTracker().getGainedExperience(Skill.MINING)-xp)/35), 10, 50);
    	g.drawString("XP/HR: " + timer.getHourlyRate((int)(getSkillTracker().getGainedExperience(Skill.MINING)-xp)), 10, 65);
    }

}
