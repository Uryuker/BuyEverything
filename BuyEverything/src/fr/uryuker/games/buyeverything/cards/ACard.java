package fr.uryuker.games.buyeverything.cards;

import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public abstract class ACard {
	
	private String pTitle;
	private ECardTypes pType;
	private boolean pInHand = false;
	private boolean pUsed = false;
	private int[] pValues;
	
	public ACard(String aTitle, ECardTypes aType, int[] aValues) {
		this.setTitle(aTitle);
		this.setType(aType);
		this.setValues(aValues);
	}
	
	public void doAction(CPlayer aPlayer) {
		System.out.print("Vous piochez une carte "+this.getType().getValue()+": \n"+this.getTitle());
	}

	public String getTitle() {
		return this.pTitle;
	}

	public ECardTypes getType() {
		return this.pType;
	}

	public int[] getValues() {
		return this.pValues;
	}

	public boolean isInHand() {
		return this.pInHand;
	}

	public boolean isUsed() {
		return this.pUsed;
	}

	public void setInHand(boolean pInHand) {
		this.pInHand = pInHand;
	}

	public void setTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public void setType(ECardTypes pType) {
		this.pType = pType;
	}

	public void setUsed(boolean pUsed) {
		this.pUsed = pUsed;
	}

	public void setValues(int[] pValues) {
		this.pValues = pValues;
	}
}
