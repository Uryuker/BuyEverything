package fr.uryuker.games.buyeverything.spaces;

public class CRailwayStationSpace extends CPropertySpace{

	public CRailwayStationSpace(String aName) {
		super(aName, DEFAULT_RAILWAY_STATION_SPACE_PRICE);
	}
	public CRailwayStationSpace(String aName, int aPrice) {
		super(aName, aPrice);
	}

}
