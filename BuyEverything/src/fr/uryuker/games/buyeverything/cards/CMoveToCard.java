package fr.uryuker.games.buyeverything.cards;

import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.constants.EMoveWay;
import fr.uryuker.games.buyeverything.engine.CGame;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CMoveToCard extends ACard {

	private String pSpaceName;
	private EMoveWay pMoveWay;

	public CMoveToCard(String aTitle, ECardTypes aType, int[] aValues, String aSpaceName) {
		super(aTitle, aType, aValues);
		this.setSpaceName(aSpaceName);
		this.setMoveWay((aValues[0]<0?EMoveWay.BACKWARD:EMoveWay.FORWARD));
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		super.doAction(aPlayer);
		final int wSpaceIndex = CGame.getInstance().getSpaceIndex(this.pSpaceName);
		int wMovesToDo =0;
		if(this.getMoveWay()==EMoveWay.BACKWARD) {
			wMovesToDo=wSpaceIndex - aPlayer.getCurrentCase();
		}
		else {
			wMovesToDo=aPlayer.getCurrentCase()-wSpaceIndex;
		}
		aPlayer.move(wMovesToDo);
	}

	public EMoveWay getMoveWay() {
		return this.pMoveWay;
	}

	public String getSpaceName() {
		return this.pSpaceName;
	}

	public void setMoveWay(EMoveWay pMoveWay) {
		this.pMoveWay = pMoveWay;
	}

	public void setSpaceName(String pSpaceName) {
		this.pSpaceName = pSpaceName;
	}

}
