package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.engine.CGame;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CUtilitySpace extends CPropertySpace {

	public CUtilitySpace(String aName) {
		super(aName, DEFAULT_UTILITY_SPACE_PRICE);
		this.setMortgagePrice(DEFAULT_UTILITY_MORTGAGE_PRICE);
	}
	
	public CUtilitySpace(String aName, int aPrice) {
		super(aName, aPrice);
	}
	

	@Override
	protected double getRent(CDiceThrow aDiceThrow, CPlayer aPlayer) {
		final int wNbUtilities = CGame.getInstance().getPropertyCountForUser(this.getOwner(), CUtilitySpace.class);
		return aDiceThrow.getResult()*4*(Math.pow(2.5, (wNbUtilities-1)));
	}

}
