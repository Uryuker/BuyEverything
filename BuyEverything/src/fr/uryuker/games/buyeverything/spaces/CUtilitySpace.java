package fr.uryuker.games.buyeverything.spaces;

public class CUtilitySpace extends CPropertySpace {

	public CUtilitySpace(String aName) {
		super(aName, DEFAULT_UTILITY_SPACE_PRICE);
	}
	
	public CUtilitySpace(String aName, int aPrice) {
		super(aName, aPrice);
	}

}
