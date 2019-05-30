package fr.uryuker.games.buyeverything.entities;

import java.util.ArrayList;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.cards.ACard;
import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.constants.EPlayerStatus;
import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.engine.CGame;
import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.spaces.CPropertySpace;
import fr.uryuker.games.buyeverything.spaces.CRoadSpace;
import fr.uryuker.games.buyeverything.spaces.CSpaceFamily;
import fr.uryuker.games.buyeverything.spaces.corner.CGoToJailSpace;
public class CPlayer implements IGameRules{

	private String pName;
	private String pAvatar;
	private int pMoney;
	private boolean pIsInJail=false;
	private boolean pIsFirstBoardTurn = true;
	private int pCurrentCase=0;
	private int pTurnsInJail=0;
	private final ArrayList<ACard> pHand= new ArrayList<>();
	private EPlayerStatus pStatus = EPlayerStatus.IN_GAME;
	
	public CPlayer(String aName) {
		this(aName, DEFAULT_AVATAR);
	}
	
	public CPlayer(String aName, String aAvatar) {
		this.setAvatar(aAvatar);
		this.setName(aName);
		this.setMoney(BASE_MONEY);
	}
	
	public void addCardToHand(ACard aCard) {
		this.pHand.add(aCard);
		aCard.setInHand(true);
	}
	
	public void addDicesResult(int aResult) {
		this.pCurrentCase += aResult;
		final CBoard wBoard = CGame.getInstance().getBoard();
		final int wBoardMaxIndex = wBoard.getSpaces().size()-1;
		//Manage case when player starts a new turn
		if(this.pCurrentCase>wBoardMaxIndex) {
			this.passByStart();
			this.pCurrentCase = this.pCurrentCase%wBoardMaxIndex;
		}
	}
	
	public void addMoney(double wRent) {
		this.pMoney += wRent;
	}

	private void addTurnInJail() {
		this.pTurnsInJail++;		
	}
	
	public void drawCard(ECardTypes aCardType) {
		final ACard wCard= CGame.getInstance().drawCard(aCardType);
	}
	
	public String getAvatar() {
		return this.pAvatar;
	}
	
	public int getCurrentCase() {
		return this.pCurrentCase;
	}

	public ArrayList<ACard> getHand() {
		return this.pHand;
	}

	public int getMoney() {
		return this.pMoney;
	}

	public String getName() {
		return this.pName;
	}

	public EPlayerStatus getStatus() {
		return this.pStatus;
	}

	public int getTurnsInJail() {
		return this.pTurnsInJail;
	}
	
	public void goToJail() {
		this.setCurrentCase(CGame.getInstance().getBoard().getJailSpaceIndex());
		this.setInJail(true);
	}
	public boolean hasAllFamily(CRoadSpace cRoadSpace) {
		final ArrayList<CSpaceFamily> wFamilies = CGame.getInstance().getBoard().getFamilies();
		for(final CSpaceFamily wFamily :wFamilies) {
			if(wFamily.getName().equals(cRoadSpace.getFamily())){
				for(final ASpace wSpace : wFamily.getSpaces()) {
					if(wSpace instanceof CRoadSpace && (((CRoadSpace)wSpace).getOwner()==null ||((CRoadSpace)wSpace).getOwner()==this )){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isFirstBoardTurn() {
		return this.pIsFirstBoardTurn;
	}

	public boolean isInJail() {
		return this.pIsInJail;
	}

	public boolean move(CDiceThrow aDiceThrow) {
		//If he's already in jail and he does not have a double
		if(this.getCurrentCase()==CGame.getInstance().getBoard().getJailSpaceIndex() && this.isInJail() && !aDiceThrow.isDouble() ) {
			//If he is not forced to play, keep him in jail
			if(this.getTurnsInJail()<2) {
				this.addTurnInJail();
				return false;
			}
			//If he has to pay, remove money then play the turn
			else {
				this.removeMoney(EXIT_JAIL_PRICE);
			}
		}
		this.setTurnsInJail(0);
		this.addDicesResult(aDiceThrow.getResult());
		final ASpace wCurrentSpace = CGame.getInstance().getBoard().getSpaceAt(this.pCurrentCase);
		wCurrentSpace.doAction(this);
		if(wCurrentSpace instanceof CGoToJailSpace) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean move(int aMoveValue) {
		final CDiceThrow wDiceThrow = new CDiceThrow();
		wDiceThrow.setResult(aMoveValue, 0);
		wDiceThrow.setDouble(1, 0);
		return this.move(wDiceThrow);
	}

	private void passByStart() {
		this.setFirstBoardTurn(false);
		this.addMoney(200);
	}	
	
	public void payToOwner(double wRent, CPlayer aOwner) {
		if(wRent>this.getMoney()) {
			this.removeMoney(wRent);
			aOwner.addMoney(wRent);
		}
		else {
			this.removeMoney(this.getMoney());
			aOwner.addMoney(this.getMoney());
			this.setBusted(aOwner);
		}

	}
	
	public void playCard() {
		final ACard wCard = this.pHand.get(0);
		wCard.setInHand(false);
		this.pHand.remove(0);
	}

	public void removeCardFromHand(ACard aCard) {
		this.pHand.remove(aCard);
	}

	public void removeMoney(double wRent) {
		if(wRent>this.getMoney()) {
			this.pMoney -= wRent;
		}
		else {
			this.setBusted(null);
		}
	}

	public void setAvatar(String pAvatar) {
		this.pAvatar = pAvatar;
	}

	public void setBusted(CPlayer aOwner) {
		this.setStatus(EPlayerStatus.BANKRUPT);
		for(final ASpace wSpace : CGame.getInstance().getBoard().getSpaces()) {
			if(wSpace instanceof CPropertySpace && ((CPropertySpace)wSpace).getOwner()== this) {
				if(((CPropertySpace)wSpace).isMortgaged()) {
					((CPropertySpace)wSpace).setOwner(aOwner);
				}else {
					((CPropertySpace)wSpace).setOwner(null);
				}
			}
		}
	}

	public void setCurrentCase(int pCurrentCase) {
		this.pCurrentCase = pCurrentCase;
	}

	public void setFirstBoardTurn(boolean pIsFirstBoardTurn) {
		this.pIsFirstBoardTurn = pIsFirstBoardTurn;
	}

	public void setInJail(boolean pIsInJail) {
		this.pIsInJail = pIsInJail;
	}

	public void setMoney(int pMoney) {
		this.pMoney = pMoney;
	}

	public void setName(String pName) {
		this.pName = pName;
	}

	public void setStatus(EPlayerStatus pStatus) {
		this.pStatus = pStatus;
	}

	public void setTurnsInJail(int pTurnsInJail) {
		this.pTurnsInJail = pTurnsInJail;
	}
	
}
