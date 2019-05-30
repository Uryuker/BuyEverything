package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.engine.CGame;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CRailwayStationSpace extends CPropertySpace{

	public CRailwayStationSpace(String aName) {
		super(aName, DEFAULT_RAILWAY_STATION_SPACE_PRICE);
		this.setMortgagePrice(DEFAULT_RAILWAY_STATION_MORTGAGE_PRICE);
	}
	public CRailwayStationSpace(String aName, int aPrice) {
		super(aName, aPrice);
	}

	@Override
	protected double getRent(CDiceThrow aDiceThrow, CPlayer aPlayer) {
		final int wNbStations = CGame.getInstance().getPropertyCountForUser(this.getOwner(), CRailwayStationSpace.class);
		return 25*Math.pow(2,(wNbStations-1));
	}
}
