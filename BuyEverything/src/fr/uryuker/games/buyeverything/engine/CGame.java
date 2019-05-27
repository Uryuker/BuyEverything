package fr.uryuker.games.buyeverything.engine;

import java.util.ArrayList;
import java.util.Collections;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.constants.EGameStatus;
import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.entities.CPlayer;
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
		this.setBoard(new CBoard());
		this.setPlayers(CBoardGenerator.initDefaultplayers());
	}
	
	public CGame(ArrayList<CPlayer> pPlayers) {
		this.setBoard(new CBoard());
		this.setPlayers(pPlayers);
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
	

	public CBoard getBoard() {
		return this.pBoard;
	}
    
	public EGameStatus getGameStatus() {
		return this.pGameStatus;
	}

	public ArrayList<CPlayer> getPlayers() {
		return this.pPlayers;
	}
	
	private void playNextTurn(int aPlayerIndex) {
		aPlayerIndex=aPlayerIndex%this.getPlayers().size();
		new CTurn(this.getPlayers().get(aPlayerIndex));
		if(this.pGameStatus == EGameStatus.IN_PROGRESS) {
			this.playNextTurn(aPlayerIndex++);
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

	public void startGame() throws Exception {
		// TODO Auto-generated method stub
		this.checkPlayerCount();
		//Shuffle players to randomize order
		Collections.shuffle(this.pPlayers);
		this.playNextTurn(0);
		
	}

}
