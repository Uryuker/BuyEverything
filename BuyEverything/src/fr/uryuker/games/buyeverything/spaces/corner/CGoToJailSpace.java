package fr.uryuker.games.buyeverything.spaces.corner;

import fr.uryuker.games.buyeverything.entities.CPlayer;
import fr.uryuker.games.buyeverything.spaces.ASpace;

public class CGoToJailSpace  extends ASpace{

	public CGoToJailSpace() {
		super("Go to Jail");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doAction(CPlayer aPlayer) {
		aPlayer.goToJail();
	}

}
