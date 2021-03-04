package Smithing;

import java.awt.Color;
import java.awt.Graphics;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.script.Category;


@ScriptManifest(author = "Lucas Stafford", name = "BF BarsV2", version = 1.1, description = "Steel bar maker", category = Category.SMITHING)
public class BlastFurnace extends AbstractScript{
	
	Tile conveyorTile = new Tile(1938, 4965, 0);
	Tile conveyor = new Tile(1943, 4967, 0);
	Tile dispencerTile = new Tile(1940, 4962, 0);
	Tile dispencer = new Tile(1940, 4963, 0);
	Tile bankTile = new Tile(1948, 4957, 0);
	private Timer timer;
	private int xp;
	private String status;

	@Override
    public void onStart() {
		timer = new Timer();
		xp = (int)getSkillTracker().getGainedExperience(Skill.SMITHING);
    }
	
	@Override
	public int onLoop() {
		GameObject c = getGameObjects().getTopObjectOnTile(conveyor);
		GameObject d = getGameObjects().getTopObjectOnTile(dispencer);
		
		if(getInventory().contains("Coal Bag") && !getInventory().contains("Iron ore") && !getInventory().contains("Steel bar")) {
			status = "banking";
			getBank().openClosest();
			getBank().withdraw("Coal", 27);
			getBank().close();
			getInventory().get("Coal Bag").interact("Fill");
			sleepUntil( () -> getDialogues().canContinue(), Calculations.random(5000, 8000));
			getBank().openClosest();
			getBank().withdraw("Iron Ore", 27);
			getBank().close();
			if(getWalking().getRunEnergy() >= 75 && !getWalking().isRunEnabled()) {
				getWalking().toggleRun();
			}
		} else if(getInventory().contains("Iron ore")) {
			status = "walking to conveyor";
			getWalking().clickTileOnMinimap(conveyorTile);
			sleepUntil(() -> getLocalPlayer().distance(conveyorTile) < 7, Calculations.random(5000, 8000));
			status = "placing ore on conveyor";
			c.interact("Put-ore-on");
			sleepUntil(() -> !getInventory().contains("Iron ore"), Calculations.random(5000, 8000));
			getInventory().get("Coal Bag").interact("Empty");
			sleepUntil( () -> getDialogues().canContinue(), Calculations.random(15000, 30000));
			c.interact("Put-ore-on");
			status = "walking to dispencer";
			getWalking().clickTileOnMinimap(dispencerTile);
			if (getDialogues().inDialogue()) {
	            getDialogues().clickContinue();
					if (getDialogues().inDialogue()) {
			            getDialogues().clickContinue();
			        }
				sleepUntil(() -> getLocalPlayer().distance(dispencer) < 3 && (getSkillTracker().getGainedExperience(Skill.SMITHING)-xp)/17.48
					> 26, Calculations.random(5000, 8000));
				d.interact("Take");
				status = "taking steel bars";
				sleep(1500);
				getWidgets().getWidgetChild(270, 14, 38).interact();
				sleepUntil( () -> getInventory().contains("Steel bar"), Calculations.random(5000, 8000));
			} else if(getWalking().isRunEnabled()){
				sleepUntil(() -> getLocalPlayer().distance(dispencer) < 2 && (getSkillTracker().getGainedExperience(Skill.SMITHING)-xp)/17.48
							> 26, Calculations.random(5000, 8000));
				d.interact("Take");
				status = "taking steel bars";
				sleep(1500);
				getWidgets().getWidgetChild(270, 14, 38).interact();
				sleepUntil( () -> getInventory().contains("Steel bar"), Calculations.random(5000, 8000));
				if(getDialogues().inDialogue() && !getInventory().contains("Steel bar")) {
					d.interact("Take");
					status = "taking steel bars";
					sleep(1500);
					getWidgets().getWidgetChild(270, 14, 38).interact();
					sleepUntil( () -> getInventory().contains("Steel bar"), Calculations.random(5000, 8000));
				}
				
			} else {
				while(!getInventory().contains("Steel bar")) {
					sleepUntil(() -> getLocalPlayer().distance(dispencer) < 3 && (getSkillTracker().getGainedExperience(Skill.SMITHING)-xp)/17.48
							> 26, Calculations.random(5000, 8000));
				d.interact("Take");
				status = "taking steel bars";
				sleep(1500);
				getWidgets().getWidgetChild(270, 14, 38).interact();
				sleepUntil( () -> getInventory().contains("Steel bar"), Calculations.random(5000, 8000));
				}
			}
		} else if (getInventory().contains("Steel bar")) {
			status = "walking to bank";
			getWalking().clickTileOnMinimap(bankTile);
			sleepUntil(() -> getLocalPlayer().distance(bankTile) < 4, Calculations.random(5000, 8000));
			getBank().openClosest();
			status = "baking";
			getBank().depositAllExcept("Coal Bag");
		}
		
		return 500;
	}

	@Override
	public void onExit() {
		
	}
	
	
	 public void onPaint(Graphics g) {
	    	g.setColor(Color.GREEN);
	    	g.drawString("Timer: " + timer.formatTime(), 10, 35);
	    	g.drawString("Bar: " + (getSkillTracker().getGainedExperience(Skill.SMITHING)-xp)/17.48, 10, 50);
	    	g.drawString("Bar/HR: " + timer.getHourlyRate((int)(getSkillTracker().getGainedExperience(Skill.SMITHING)-xp)/18), 10, 65);
	    	g.drawString("XP/HR: " + timer.getHourlyRate((int)(getSkillTracker().getGainedExperience(Skill.SMITHING)-xp)), 10, 80);
	    	g.drawString("Status: " + status, 10, 95);
	    }
}
