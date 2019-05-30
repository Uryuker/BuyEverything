package fr.uryuker.games.buyeverything.cards;

import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.engine.CGame;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CGetMoneyFromEveryoneCard  extends ACard{


	public CGetMoneyFromEveryoneCard(String aTitle, ECardTypes aType, int[] aValues) {
		super(aTitle, aType, aValues);
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		super.doAction(aPlayer);
		for(final CPlayer wPlayer : CGame.getInstance().getPlayers()) {
			if(wPlayer!= aPlayer) {
				wPlayer.payToOwner(this.getValues()[0], aPlayer);
			}
		}
	}

}
