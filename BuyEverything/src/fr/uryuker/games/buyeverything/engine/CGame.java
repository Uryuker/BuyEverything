package fr.uryuker.games.buyeverything.engine;

import java.util.ArrayList;
import java.util.Collections;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.cards.ACard;
import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.constants.EGameStatus;
import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.entities.CPlayer;
import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.spaces.CPropertySpace;
import fr.uryuker.games.buyeverything.spaces.CSpaceFamily;
import fr.uryuker.games.buyeverything.utils.CBoardGenerator;

public class CGame implements IGameRules{

	private static CGame pInstance = null;
	
	/** Point d'accès pour l'instance unique du singleton */
    public static synchronized CGame getInstance()
    {           
        if (pInstance == null)
        {   pInstance = new CGame(); 
        }
        return pInstance;
    }
    
    private CBoard pBoard;
    private EGameStatus pGameStatus = EGameStatus.IN_PROGRESS; 
	private ArrayList<CPlayer> pPlayers;
	
	public CGame() {
		// used when generating map
		// this.setBoard(new CBoard());
		this.setBoard(CBoardGenerator.generateEmptyBoard());
		this.setPlayers(CBoardGenerator.initDefaultplayers());
	}
	
	public CGame(ArrayList<CPlayer> pPlayers) {
		this.setBoard(new CBoard());
		this.setPlayers(pPlayers);
	}
	
	private boolean checkPile(ArrayList<? extends ACard> wPile) {
		//Check if at least one card is not used
		for(final ACard wCard : wPile) {
			if(!wCard.isUsed()) {
				return true;
			}
		}
		return false;
	}
	

	private void checkPlayerCount() throws Exception {
		final int wPlayerCount = this.getPlayers().size();
		if(wPlayerCount<MIN_PLAYERS) {
			throw new Exception("Please use at least "+MIN_PLAYERS+" players.");
		}
		if(wPlayerCount>=MAX_PLAYERS) {
			throw new Exception("Please use maximum "+MAX_PLAYERS+" players.");
		}
	}
    
	public ACard drawCard(ECardTypes aCardType) {
		ArrayList<? extends ACard> wPile;
		if(aCardType == ECardTypes.CHANCE) {
			wPile = this.getBoard().getChances();
		}
		else {
			wPile = this.getBoard().getCommunities();
		}
		if(this.checkPile(wPile)) {
			return wPile.get(0);
		}
		else {
			this.shuffleDraw(aCardType);
			return this.drawCard(aCardType);
		}
		
	}

	public CBoard getBoard() {
		return this.pBoard;
	}
	
	public EGameStatus getGameStatus() {
		return this.pGameStatus;
	} 
	
	public ArrayList<CPlayer> getPlayers() {
		return this.pPlayers;
	}
	
	public int getPropertyCountForUser(CPlayer aPlayer, Class<? extends CPropertySpace> aClass) {
		int wCount=0;
		for(final ASpace wSpace: this.pBoard.getSpaces()) {
			if(aClass.isInstance(wSpace) && ((CPropertySpace) wSpace).getOwner() == aPlayer) {
				wCount++;
			}
		}
		return wCount;
	}

	public int getSpaceIndex(String wSpaceName) {
		for(int i=0; i<this.pBoard.getSpaces().size(); i++) {
			final ASpace wSpace = this.pBoard.getSpaces().get(i);
			if(wSpace.getName().equals(wSpaceName)) {
				return i;
			}
		}
		return -1;
	}

	public boolean playHasFamily(CPlayer aPlayer, CSpaceFamily aFamily) {
		for(final CPropertySpace wSpace: aFamily.getSpaces()) {
			if(wSpace.getOwner() != aPlayer) {
				return false;
			}
		}
		return true;
	}

	private void playNextTurn(int aPlayerIndex) {
		aPlayerIndex=aPlayerIndex%(this.getPlayers().size());
		new CTurn(this.getPlayers().get(aPlayerIndex));
		aPlayerIndex++;
		if(this.pGameStatus == EGameStatus.IN_PROGRESS) {
			this.playNextTurn(aPlayerIndex);
		}
	}

	public void setBoard(CBoard pBoard) {
		this.pBoard = pBoard;
	}
	
	public void setGameStatus(EGameStatus pGameStatus) {
		this.pGameStatus = pGameStatus;
	}

	public void setPlayers(ArrayList<CPlayer> pPlayers) {
		this.pPlayers = pPlayers;
	}

	public void shuffleDraw(ECardTypes wType) {
		ArrayList<? extends ACard> wPile;
		if(wType == ECardTypes.CHANCE) {
			wPile = this.getBoard().getChances();
		}
		else {
			wPile = this.getBoard().getCommunities();
		}
		for(final ACard wCard : wPile) {
			if(!wCard.isInHand()) {
				wCard.setUsed(false);
			}
		}
		Collections.shuffle(wPile);
	}
	
	public void startGame() throws Exception {
		this.checkPlayerCount();
		//Shuffle players to randomize order
		Collections.shuffle(this.pPlayers);
		Collections.shuffle(this.getBoard().getChances());
		Collections.shuffle(this.getBoard().getCommunities());
		this.playNextTurn(0);
		
	}
}
