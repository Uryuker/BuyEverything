package fr.uryuker.games.buyeverything.cards;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.engine.CGame;
import fr.uryuker.games.buyeverything.entities.CPlayer;
import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.spaces.CRoadSpace;

public class CBuildingTaxCard extends ACard {


	public CBuildingTaxCard(String aTitle, ECardTypes aType, int[] aValues) {
		super(aTitle, aType, aValues);
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		super.doAction(aPlayer);
		final int wHouseCost = this.getValues()[0];
		final int wHotelCost = this.getValues()[1];
		final int wHouseCount = this.getHousesForPlayer(aPlayer);	
		final int wHotelCount = this.getHotelForPlayer(aPlayer);		
		aPlayer.removeMoney(wHotelCount*wHotelCost+wHouseCost*wHouseCount);
	}
	
	private int getHotelForPlayer(CPlayer aPlayer) {
		final CBoard wBoard = CGame.getInstance().getBoard();
		int wHotelCount=0;
		for(final ASpace wSpace : wBoard.getSpaces()) {
			if(wSpace instanceof CRoadSpace && ((CRoadSpace) wSpace).getOwner() == aPlayer) {
				if(((CRoadSpace) wSpace).hasHotel()) {
					wHotelCount++;
				}
			}
		}
		return wHotelCount;
	}
	
	private int getHousesForPlayer(CPlayer aPlayer) {
		final CBoard wBoard = CGame.getInstance().getBoard();
		int wHouseCount=0;
		for(final ASpace wSpace : wBoard.getSpaces()) {
			if(wSpace instanceof CRoadSpace && ((CRoadSpace) wSpace).getOwner() == aPlayer) {
				if(!((CRoadSpace) wSpace).hasHotel()) {
					wHouseCount+=((CRoadSpace) wSpace).getHouseCount();
				}
			}
		}
		return wHouseCount;
	}

}
