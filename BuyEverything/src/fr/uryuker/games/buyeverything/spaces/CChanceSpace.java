package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CChanceSpace extends ASpace{

	public CChanceSpace() {
		super("Chance");
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		super.toString();
		aPlayer.drawCard(ECardTypes.CHANCE);
	}
}
