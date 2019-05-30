package fr.uryuker.games.buyeverything.cards;

import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CGetMoneyCard extends ACard {


	public CGetMoneyCard(String aTitle, ECardTypes aType, int[] aValues) {
		super(aTitle, aType, aValues);
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		super.doAction(aPlayer);
		aPlayer.addMoney(this.getValues()[0]);
	}

}
