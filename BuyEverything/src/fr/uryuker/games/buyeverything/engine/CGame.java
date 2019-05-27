package fr.uryuker.games.buyeverything.engine;

import java.util.ArrayList;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.entities.CPlayer;
import fr.uryuker.games.buyeverything.utils.CBoardGenerator;

public class CGame {

	private CBoard pBoard;
	
	private ArrayList<CPlayer> pPlayers;
	
	public CGame() {
		this.setBoard(new CBoard());
		this.setPlayers(CBoardGenerator.initDefaultplayers());
	}
	
	public CGame(ArrayList<CPlayer> pPlayers) {
		this.setBoard(new CBoard());
		this.setPlayers(pPlayers);
	}
	
	public CBoard getBoard() {
		return this.pBoard;
	}
	public ArrayList<CPlayer> getPlayers() {
		return this.pPlayers;
	}
	public void setBoard(CBoard pBoard) {
		this.pBoard = pBoard;
	}

	public void setPlayers(ArrayList<CPlayer> pPlayers) {
		this.pPlayers = pPlayers;
	}

	public void startGame() {
		// TODO Auto-generated method stub
	}

}
