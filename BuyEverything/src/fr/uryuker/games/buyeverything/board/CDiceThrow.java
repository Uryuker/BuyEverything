package fr.uryuker.games.buyeverything.board;

import java.util.concurrent.ThreadLocalRandom;

public class CDiceThrow {

	private int pResult=0;
	private boolean pIsDouble=false;
	
	public CDiceThrow() {
		final int wFirstDice = this.thowOneDice();
		final int wSecondDice = this.thowOneDice();
		this.setResult(wFirstDice, wSecondDice);
		this.setDouble(wFirstDice, wSecondDice);
	}
	

	public int getResult() {
		return this.pResult;
	}

	public boolean isDouble() {
		return this.pIsDouble;
	}

	public void setDouble(int aFirstDice, int aSecondDice) {
		this.pIsDouble = aFirstDice==aSecondDice;
	}

	public void setResult(int aFirstDice, int aSecondDice) {
		this.pResult = aFirstDice+aSecondDice;
	}
	
	private int thowOneDice() {
		return ThreadLocalRandom.current().nextInt(1,6);
	}
}
