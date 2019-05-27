package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CPropertySpace extends ASpace {
	private int pPrice;
	private CPlayer pOwner=null;
	
	public CPropertySpace(String aName) {
		super(aName);
	}
	public CPropertySpace(String aName, int aPrice) {
		super(aName);
		this.setPrice(aPrice);
	}
	public CPlayer getOwner() {
		return this.pOwner;
	}
	public int getPrice() {
		return this.pPrice;
	}

	public void setOwner(CPlayer pOwner) {
		this.pOwner = pOwner;
	}
	public void setPrice(int aPrice) {
		this.pPrice = aPrice;
	}
	@Override
	public String toString() {
		final StringBuilder wStringBuilder = new StringBuilder(); 
		wStringBuilder.append(super.toString());
		wStringBuilder.append(" which costs ");
		wStringBuilder.append(this.getPrice());
		wStringBuilder.append(CURRENCY);
		wStringBuilder.append(" and is owned by ");
		wStringBuilder.append(this.getOwner()==null? "noone": this.getOwner().getName());
		
		return wStringBuilder.toString();
	}
}
