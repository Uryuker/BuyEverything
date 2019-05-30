package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CTaxSpace extends ASpace{

	private int pCost;
	
	public CTaxSpace(String aName) {
		super(aName);
	}
	
	public CTaxSpace(String aName, int aCost) {
		super(aName);
		this.setCost(aCost);
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		super.doAction(aPlayer);
		aPlayer.removeMoney(this.getCost());
	}

	public int getCost() {
		return this.pCost;
	}

	public void setCost(int pCost) {
		this.pCost = pCost;
	}
}
