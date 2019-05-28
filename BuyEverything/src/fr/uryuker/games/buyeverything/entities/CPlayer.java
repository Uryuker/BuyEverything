package fr.uryuker.games.buyeverything.entities;

import java.util.ArrayList;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.cards.ACard;
import fr.uryuker.games.buyeverything.constants.EPlayerStatus;
import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.engine.CGame;
public class CPlayer implements IGameRules{

	private String pName;
	private String pAvatar;
	private int pMoney;
	private boolean pIsInJail=false;
	private boolean pIsFirstBoardTurn = true;
	private int pCurrentCase=0;
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
	
	public void addMoney(int pMoney) {
		this.pMoney += pMoney;
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

	public void goToJail() {
		this.setCurrentCase(CGame.getInstance().getBoard().getJailSpaceIndex());
		this.setInJail(true);
	}

	public boolean isFirstBoardTurn() {
		return this.pIsFirstBoardTurn;
	}

	public boolean isInJail() {
		return this.pIsInJail;
	}
	
	public boolean move(CDiceThrow aDiceThrow) {
		this.addDicesResult(aDiceThrow.getResult());
		if(this.getCurrentCase()==CGame.getInstance().getBoard().getJailSpaceIndex() && this.isInJail()) {
			return false;
		} else {
			CGame.getInstance().getBoard().getSpaceAt(this.pCurrentCase).doAction(this);
			return true;
		}
	}

	private void passByStart() {
		// TODO Auto-generated method stub
		this.setFirstBoardTurn(false);
		this.addMoney(200);
	}

	public void playCard() {
		
	}

	public void removeCardFromHand(ACard aCard) {
		this.pHand.remove(aCard);
	}	
	
	public void removeMoney(int pMoney) {
		this.pMoney -= pMoney;
	}
	
	public void setAvatar(String pAvatar) {
		this.pAvatar = pAvatar;
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
	
}
