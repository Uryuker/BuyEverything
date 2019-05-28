package fr.uryuker.games.buyeverything.spaces;

import java.util.Scanner;

import fr.uryuker.games.buyeverything.constants.EBuyActions;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CPropertySpace extends ASpace {
	private int pPrice;
	private CPlayer pOwner=null;
	private int pHouseCount=0;
	private boolean pHasHotel =false;
	
	public CPropertySpace(String aName) {
		super(aName);
	}
	public CPropertySpace(String aName, int aPrice) {
		super(aName);
		this.setPrice(aPrice);
	}
	public void addHouse() {
		this.pHouseCount ++;
	}
	private void displayMenu(CPlayer aPlayer) {
		final StringBuilder wStringBuilder = new StringBuilder();
		wStringBuilder.append(System.getProperty("line.separator"));
		wStringBuilder.append("Merci de choisir votre action parmis les suivantes : ");
		// Show buy action 
		if(this.getOwner()==null) {
			wStringBuilder.append("    - ");
			wStringBuilder.append(EBuyActions.BUY.getValue());
		}
		// Else if it's the owner
		else if(this.getOwner() == aPlayer ) {
			if(this.pHouseCount==4 && !this.hasHotel()) {
				wStringBuilder.append("    - ");
				wStringBuilder.append(EBuyActions.BUY_HOTEL.getValue());
			}
			else if(this.pHouseCount<4){
				wStringBuilder.append("    - ");
				wStringBuilder.append(EBuyActions.BUY_HOUSE.getValue());
			}
		}
		else {
			this.pay(aPlayer);
		}
		// Show Leave action
		wStringBuilder.append("    - ");
		wStringBuilder.append(EBuyActions.SKIP.getValue());

		System.out.println(wStringBuilder.toString());	
		final Scanner wScanner = new Scanner(System.in);
		final String wAction = wScanner.nextLine();
		wScanner.close();
	}

	@Override
	public void doAction(CPlayer aPlayer) {
		//TODO
		System.out.println(this.toString());
		this.displayMenu(aPlayer);
	}
	public int getHouseCount() {
		return this.pHouseCount;
	}
	public CPlayer getOwner() {
		return this.pOwner;
	}
	public int getPrice() {
		return this.pPrice;
	}
	
	public boolean hasHotel() {
		return this.pHasHotel;
	}
	
	private void pay(CPlayer aPlayer) {
		// TODO Auto-generated method stub
		
	}
	public void setHasHotel(boolean pHasHotel) {
		this.pHasHotel = pHasHotel;
	}
	public void setHouseCount(int pHouseCount) {
		this.pHouseCount = pHouseCount;
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
