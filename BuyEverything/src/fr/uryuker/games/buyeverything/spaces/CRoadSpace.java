package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.constants.EBuyActions;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CRoadSpace extends CPropertySpace {

	private int pHouseCount=0;
	private boolean pHasHotel =false;
	private int pHotelPrice=0;
	private int pHousePrice=0;
	private int[] pRent;
	
	
	private CSpaceFamily pFamily;

	public CRoadSpace(String aName) {
		super(aName);
	}
	
	public CRoadSpace(String aName, int aPrice) {
		super(aName, aPrice);
	}

	public CRoadSpace(String aName, int aPrice, int aHousePrice, int aHotelPrice, int aMortgagePrice, int[] aRent, CSpaceFamily aFamily) {
		super(aName);
		this.setPrice(aPrice);
		this.setHotelPrice(aHotelPrice);
		this.setHousePrice(aHousePrice);
		this.setMortgagePrice(aMortgagePrice);
		this.setFamily(aFamily);
		this.setRent(aRent);
		aFamily.addSpace(this);
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
	
	@Override
	protected void applyActionFromString(String aAction, CPlayer aPlayer) {
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
	@Override
	protected void displayMenu(CPlayer aPlayer, CDiceThrow aDiceThrow) {
			final StringBuilder wStringBuilder = new StringBuilder();
		if(this.getOwner() == aPlayer ) {
			if(this.pHouseCount==4 && !this.hasHotel()) {
				wStringBuilder.append(System.getProperty("line.separator"));
				wStringBuilder.append("    - ");
				wStringBuilder.append(EBuyActions.BUY_HOTEL.getValue());
				this.addLeaveMenu(wStringBuilder);
			}
			else if(this.getOwner() != aPlayer && this.pHouseCount<4){
				wStringBuilder.append(System.getProperty("line.separator"));
				wStringBuilder.append("    - ");
				wStringBuilder.append(EBuyActions.BUY_HOUSE.getValue());
				this.addLeaveMenu(wStringBuilder);
			}
			System.out.println(wStringBuilder.toString());	
			super.displayMenu(aPlayer, aDiceThrow);
		
		}
	}
	public CSpaceFamily getFamily() {
		return this.pFamily;
	}


	public int getHotelPrice() {
		return this.pHotelPrice;
	}
	public int getHouseCount() {
		return this.pHouseCount;
	}
	public int getHousePrice() {
		return this.pHousePrice;
	}
	public int[] getRent() {
		return this.pRent;
	}
	@Override
	protected double getRent(CDiceThrow aDiceThrow) {
		if(this.hasHotel()) {
			return this.pRent[5];
		}
		else {
			return this.pRent[this.getHouseCount()];
		}
	}
	public boolean hasHotel() {
		return this.pHasHotel;
	}
	public void setFamily(CSpaceFamily pFamily) {
		this.pFamily = pFamily;
	}
	
	public void setHasHotel(boolean pHasHotel) {
		this.pHasHotel = pHasHotel;
	}
	public void setHotelPrice(int pHotelPrice) {
		this.pHotelPrice = pHotelPrice;
	}
	public void setHouseCount(int pHouseCount) {
		this.pHouseCount = pHouseCount;
	}
	public void setHousePrice(int pHousePrice) {
		this.pHousePrice = pHousePrice;
	}
	public void setRent(int[] pRent) {
		this.pRent = pRent;
	}
}
