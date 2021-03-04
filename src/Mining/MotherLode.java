package Mining;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.path.impl.LocalPath;
import org.dreambot.api.methods.walking.pathfinding.impl.obstacle.impl.DestructableObstacle;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.script.Category;

@ScriptManifest(author = "Lucas Stafford", name = "MotherLode Mine", version = 1.1, description = "Varrock Iron Miner", category = Category.MINING)
public class MotherLode extends AbstractScript{
	
	private Tile tile1 = new Tile(3726,5681,0);
	private Tile tile2 = new Tile(3730,5679,0);
	private Tile tile3 = new Tile(3730,5678,0);
	private Tile tile4 = new Tile(3730,5677,0);
	private Tile tile5 = new Tile(3725,5675,0);
	private Tile tile6 = new Tile(3725,5674,0);
	private Tile tile7 = new Tile(3728,5675,0);
	private Tile tile8 = new Tile(3728,5674,0);
	private Tile pathTile = new Tile(3748, 5673, 0);
	private Tile mineTile = new Tile(3727, 5681, 0);
	private Area mineArea = new Area(3725, 5682, 3730, 5658);
	

	@Override
    public void onStart() {
    }
	
	@Override
	public int onLoop(){
		
		 GameObject myORE = getGameObjects().getTopObjectOnTile(tile1);
	     GameObject myORE2 = getGameObjects().getTopObjectOnTile(tile2);
	     GameObject myORE3 = getGameObjects().getTopObjectOnTile(tile3);
	     GameObject myORE4 = getGameObjects().getTopObjectOnTile(tile4);
	     GameObject myORE5 = getGameObjects().getTopObjectOnTile(tile5);
	     GameObject myORE6 = getGameObjects().getTopObjectOnTile(tile6);
	     GameObject myORE7 = getGameObjects().getTopObjectOnTile(tile7);
	     GameObject myORE8 = getGameObjects().getTopObjectOnTile(tile8);
	     
	   
        
        if (!getInventory().isFull() && mineArea.contains(getLocalPlayer())) {
        	if(getLocalPlayer().isAnimating()) {
        		//do nothing
        	} else if(getGameObjects().getTopObjectOnTile(tile1).getID() == 26661) {
        		myORE.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile1).getID() != 26661 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile2).getID() == 26662) {
        		myORE2.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile2).getID() != 26662 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile3).getID() == 26663) {
        		myORE3.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile3).getID() != 26663 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile4).getID() == 26664) {
        		myORE4.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile4).getID() != 26664 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile5).getID() == 26664) {
        		myORE5.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile5).getID() != 26664 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile6).getID() == 26662) {
        		myORE6.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile6).getID() != 26662 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile7).getID() == 26662) {
        		myORE7.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile7).getID() != 26662 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	} else if(getGameObjects().getTopObjectOnTile(tile8).getID() == 26664) {
        		myORE8.interact("Mine");
        		sleepUntil( () -> getGameObjects().getTopObjectOnTile(tile8).getID() != 26664 || getInventory().isFull(), Calculations.random(20000, 30000));
        		sleepUntil( () -> !getLocalPlayer().isAnimating(), Calculations.random(15000, 30000));
        	}
        } else if(!mineArea.contains(getLocalPlayer()) && !getInventory().isFull()) {
        	LocalPath<Tile> Dest = getWalking().getAStarPathFinder().calculate(getLocalPlayer().getTile(),
    				mineTile);
        	getWalking().getAStarPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
    				new Tile(3727, 5682, 0), new Tile(3727, 5682, 0), new Tile(3727, 5683, 0)));
        	getWalking().getAStarPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
    				new Tile(3732, 5680, 0), new Tile(3732, 5680, 0), new Tile(3733, 5680, 0)));
        	getWalking().getAStarPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
    				new Tile(3730, 5683, 0), new Tile(3730, 5683, 0), new Tile(3731, 5683, 0)));
        	if (getGameObjects()
    				.closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall") && r.distance() < 2) != null
    				&& !getMap().canReach(mineTile)) {
    			if (getGameObjects().closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall"))
    					.interact()) {
    				sleepUntil(() -> getLocalPlayer().isAnimating(), Calculations.random(2000, 3000));
    				sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(2000, 3000));
    			}
    		}
    		getWalking().walk(Dest.next());
    		sleepUntil(() -> getWalking().getDestinationDistance() < 3, Calculations.random(2800, 3500));
		}else {
        	LocalPath<Tile> Dest = getWalking().getAStarPathFinder().calculate(getLocalPlayer().getTile(),
    				pathTile);
    		getWalking().getAStarPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
    				new Tile(3730, 5683, 0), new Tile(3730, 5683, 0), new Tile(3731, 5683, 0)));
    		getWalking().getAStarPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
    				new Tile(3732, 5680, 0), new Tile(3732, 5680, 0), new Tile(3733, 5680, 0)));
    		getWalking().getAStarPathFinder().addObstacle(new DestructableObstacle("Rockfall", "Mine",
    				new Tile(3727, 5682, 0), new Tile(3727, 5682, 0), new Tile(3727, 5683, 0)));
    		if (getGameObjects()
    				.closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall") && r.distance() < 2) != null
    				&& !getMap().canReach(pathTile)) {
    			if (getGameObjects().closest(r -> r != null && r.getName().equalsIgnoreCase("Rockfall"))
    					.interact()) {
    				sleepUntil(() -> getLocalPlayer().isAnimating(), Calculations.random(2000, 3000));
    				sleepUntil(() -> !getLocalPlayer().isAnimating(), Calculations.random(2000, 3000));
    			}
    		}
    		getWalking().walk(Dest.next());
    		sleepUntil(() -> getWalking().getDestinationDistance() < 3, Calculations.random(2800, 3500));
        }
        return 500;
    }
	
	@Override
    public void onExit() {
    }
}
