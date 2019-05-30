package fr.uryuker.games.buyeverything.spaces;

import java.util.Scanner;

import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.constants.EBuyActions;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CPropertySpace extends ASpace {
	private int pPrice;
	private CPlayer pOwner=null;
	private int pMortgagePrice=0;
	private boolean pIsMortgaged=false;
	
	public CPropertySpace(String aName) {
		super(aName);
	}
	public CPropertySpace(String aName, int aPrice) {
		super(aName);
		this.setPrice(aPrice);
	}
	protected void addLeaveMenu (StringBuilder aStringBuilder) {
		// Show Leave action
		aStringBuilder.append(System.getProperty("line.separator"));
		aStringBuilder.append("    - ");
		aStringBuilder.append(EBuyActions.SKIP.getValue());
	}
	protected void applyActionFromString(String aAction, CPlayer aPlayer) {
        switch(aAction) 
        { 
            case "Acheter": 
                this.buyProperty(aPlayer);
                break; 
            case "Enchere": 
                this.bidProperty(aPlayer);
                break; 
            default: 
            	return; 
        } 
	}
	private void bidProperty(CPlayer aPlayer) {
		// TODO Auto-generated method stub
		
	}
	protected void buyProperty(CPlayer aPlayer) {
		aPlayer.removeMoney(this.pPrice);
		this.setOwner(aPlayer);		
	}
	protected void displayMenu(CPlayer aPlayer, CDiceThrow aDiceThrow) {
		final StringBuilder wStringBuilder = new StringBuilder();
		wStringBuilder.append(System.getProperty("line.separator"));
		wStringBuilder.append("Merci de choisir votre action parmis les suivantes : ");
		// Show buy action 
		if(this.getOwner()==null) {
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("    - ");
			wStringBuilder.append(EBuyActions.BUY.getValue());
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("    - ");
			wStringBuilder.append(EBuyActions.BID.getValue());
			this.addLeaveMenu(wStringBuilder);
		}
		else if(this.getOwner() != aPlayer &&!this.isMortgaged()){
			final double wRent = this.getRent(aDiceThrow, aPlayer);
			this.payToOwner(aPlayer, wRent);
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("Le proprio ( "+this.getOwner().getName()+" ) s'est saucé et vous a pris "+wRent+CURRENCY);
		}

		System.out.println(wStringBuilder.toString());	
		final Scanner wScanner = new Scanner(System.in);
		final String wAction = wScanner.nextLine();
		wScanner.close();
		this.applyActionFromString(wAction,aPlayer);
	}
	
	@Override
	public void doAction(CPlayer aPlayer) {
		this.doAction(aPlayer, new CDiceThrow());
	}
	
	public void doAction(CPlayer aPlayer, CDiceThrow aDiceThrow) {
		System.out.println(this.toString());
		this.displayMenu(aPlayer, aDiceThrow);
	}

	public int getMortgagePrice() {
		return this.pMortgagePrice;
	}
	
	
	
	public CPlayer getOwner() {
		return this.pOwner;
	}
	
	public int getPrice() {
		return this.pPrice;
	}
	protected double getRent(CDiceThrow aDiceThrow, CPlayer aPlayer) {
		return 0;
	}
	public boolean isMortgaged() {
		return this.pIsMortgaged;
	}
	
	private void payToOwner(CPlayer aPlayer, double wRent) {
		aPlayer.payToOwner(wRent, this.getOwner());
	}

	public void setMortgaged(boolean pIsMortgaged) {
		this.pIsMortgaged = pIsMortgaged;
	}
	public void setMortgagePrice(int pMortgagePrice) {
		this.pMortgagePrice = pMortgagePrice;
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
