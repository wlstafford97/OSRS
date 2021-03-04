package Fishing;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(author = "Lucas Stafford", name = "LumbFisher", version = 1.0, description = "Shrimp/Anchovies", category = Category.FISHING)
public class LumbFisher extends AbstractScript {
	private Node[] nodes;
	
	@Override
	public void onStart() {
		nodes = new Node[] {
				new DropNode(this),
				new FishNode(this),
		};
	}
	
	@Override
	public int onLoop() {
		for(Node node : nodes) {
			if(node.validate()) {
				return node.execute();
			}
		}
		return 1000;
	}

}
