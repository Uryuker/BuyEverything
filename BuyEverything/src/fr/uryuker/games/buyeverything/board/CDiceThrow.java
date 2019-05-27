package fr.uryuker.games.buyeverything.board;

import java.util.concurrent.ThreadLocalRandom;

public class CDiceThrow {

	private int pResult=0;
	private int pFirstDice=0;
	private int pSecondDice=0;
	private boolean pIsDouble=false;
	
	public CDiceThrow() {
		final int wFirstDice = this.thowOneDice();
		final int wSecondDice = this.thowOneDice();
		this.setResult(wFirstDice, wSecondDice);
		this.setDouble(wFirstDice, wSecondDice);
	}
	

	public int getFirstDice() {
		return this.pFirstDice;
	}

	public int getResult() {
		return this.pResult;
	}

	public int getSecondDice() {
		return this.pSecondDice;
	}

	public boolean isDouble() {
		return this.pIsDouble;
	}
	
	public void setDouble(int aFirstDice, int aSecondDice) {
		this.pIsDouble = aFirstDice==aSecondDice;
	}


	public void setFirstDice(int pFirstDice) {
		this.pFirstDice = pFirstDice;
	}


	public void setResult(int aFirstDice, int aSecondDice) {
		this.setFirstDice(aFirstDice);
		this.setSecondDice(aSecondDice);
		this.pResult = aFirstDice+aSecondDice;
	}


	public void setSecondDice(int pSecondDice) {
		this.pSecondDice = pSecondDice;
	}


	private int thowOneDice() {
		return ThreadLocalRandom.current().nextInt(1,6);
	}
	
	@Override
	public String toString() {
		final StringBuilder wStringBuilder = new StringBuilder();
		wStringBuilder.append(this.getFirstDice());
		wStringBuilder.append(" + ");
		wStringBuilder.append(this.getSecondDice());
		return wStringBuilder.toString();
	}
}
