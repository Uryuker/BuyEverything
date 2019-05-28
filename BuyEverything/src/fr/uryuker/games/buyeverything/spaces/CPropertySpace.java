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
	private void addHotel(CPlayer aPlayer) {
		this.setHasHotel(true);
	}
	public void addHouse() {
		this.pHouseCount ++;
	}

	private void addHouse(CPlayer aPlayer) {
		this.addHouse();
	}
	private void addLeaveMenu (StringBuilder aStringBuilder) {
		// Show Leave action
		aStringBuilder.append(System.getProperty("line.separator"));
		aStringBuilder.append("    - ");
		aStringBuilder.append(EBuyActions.SKIP.getValue());
	}
	private void applyActionFromString(String aAction, CPlayer aPlayer) {
        switch(aAction) 
        { 
            case "Acheter": 
                this.buyProperty(aPlayer);
                break; 
            case "Maison": 
                this.addHouse(aPlayer);
                break; 
            case "Hotel": 
                this.addHotel(aPlayer);
                break; 
            default: 
            	return; 
        } 
	}
	private void buyProperty(CPlayer aPlayer) {
		aPlayer.removeMoney(this.pPrice);
		this.setOwner(aPlayer);		
	}
	
	private void displayMenu(CPlayer aPlayer) {
		final StringBuilder wStringBuilder = new StringBuilder();
		wStringBuilder.append(System.getProperty("line.separator"));
		wStringBuilder.append("Merci de choisir votre action parmis les suivantes : ");
		// Show buy action 
		if(this.getOwner()==null) {
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("    - ");
			wStringBuilder.append(EBuyActions.BUY.getValue());
			this.addLeaveMenu(wStringBuilder);
		}
		// Else if it's the owner
		else if(this.getOwner() == aPlayer ) {
			if(this.pHouseCount==4 && !this.hasHotel()) {
				wStringBuilder.append(System.getProperty("line.separator"));
				wStringBuilder.append("    - ");
				wStringBuilder.append(EBuyActions.BUY_HOTEL.getValue());
				this.addLeaveMenu(wStringBuilder);
			}
			else if(this.pHouseCount<4){
				wStringBuilder.append(System.getProperty("line.separator"));
				wStringBuilder.append("    - ");
				wStringBuilder.append(EBuyActions.BUY_HOUSE.getValue());
				this.addLeaveMenu(wStringBuilder);
			}
		}
		else {
			final int wRent = this.getRent();
			this.payToOwner(aPlayer, wRent);
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("Le proprio ( "+this.getOwner().getName()+" ) s'est saucé et vous a pris "+wRent+CURRENCY);
		}

		System.out.println(wStringBuilder.toString());	
		final Scanner wScanner = new Scanner(System.in);
		final String wAction = wScanner.nextLine();
		this.applyActionFromString(wAction,aPlayer);
	}
	@Override
	public void doAction(CPlayer aPlayer) {
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
	
	private int getRent() {
		return 0;
	}
	
	public boolean hasHotel() {
		return this.pHasHotel;
	}
	private void payToOwner(CPlayer aPlayer, int aRent) {
		aPlayer.removeMoney(aRent);
		this.getOwner().addMoney(aRent);
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
