package fr.uryuker.games.buyeverything.engine;

import java.util.Scanner;

import fr.uryuker.games.buyeverything.board.CDiceThrow;
import fr.uryuker.games.buyeverything.constants.EGameActions;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CTurn {

	private CPlayer pPlayer;
	private int pThrowCount=0;
	
	
	public CTurn(CPlayer aPlayer) {
		this.setPlayer(aPlayer);
		final EGameActions wAction = this.displayMenu();
		this.doAction(wAction);
	}


	public void addThrow() {
		this.pThrowCount ++;
	}


	/**
	 * @return EGameActions depending on the choice of the user
	 * 	The default will be THROW_DICES
	 */
	private EGameActions displayMenu() {
		StringBuilder wStringBuilder = new StringBuilder(); 
		if(this.pThrowCount==0) {
			wStringBuilder.append("C'est à votre tour de jouer : ");
		} else {
			wStringBuilder.append("C'est encore à vous ");
		}
		wStringBuilder.append(this.getPlayer().getName());
		final CPlayer wPlayer = this.getPlayer();

		wStringBuilder.append(System.getProperty("line.separator"));
		wStringBuilder.append("Etes-vous prêt ? ");
		System.out.println(wStringBuilder.toString());	
		final Scanner sc = new Scanner(System.in);
		sc.nextLine();
		if(wPlayer.isInJail() && !wPlayer.getHand().isEmpty()) {
			return this.getUserAction();			
		}
		else {
			wStringBuilder = new StringBuilder();
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("Vous n'avez pas le choix, nous lançons les dés à votre place! ");
			System.out.println(wStringBuilder.toString());	
			return EGameActions.THROW_DICES;
		}
	}


	/**
	 * Call the right method depending of the action chosen
	 * 
	 * @param wAction
	 */
	private void doAction(EGameActions wAction) {
		if(wAction == EGameActions.THROW_DICES) {
			this.throwDices();
		}
		else if(wAction == EGameActions.PLAY_CARD) {
			this.playCard();
		}
	}


	public CPlayer getPlayer() {
		return this.pPlayer;
	}


	public int getThrowCount() {
		return this.pThrowCount;
	}


	/**
	 * If the user can choose, display actions
	 * 
	 * @param aStringBuilder
	 * @return EGameActions Corresponding to the choice of the user
	 */
	private EGameActions getUserAction() {
		final StringBuilder wStringBuilder = new StringBuilder();
		wStringBuilder.append(System.getProperty("line.separator"));
		wStringBuilder.append("Merci de choisir votre action parmis les suivantes : ");
		for(final EGameActions wValue : EGameActions.values()) {
			wStringBuilder.append("    - ");
			wStringBuilder.append(wValue.getValue());
		}
		System.out.println(wStringBuilder.toString());	
		final Scanner wScanner = new Scanner(System.in);
		final String wAction = wScanner.nextLine();
		wScanner.close();
		return EGameActions.getEnum(wAction);
	}

	private void playCard() {
		this.pPlayer.playCard();
		this.throwDices();
	}

	public void setPlayer(CPlayer pPlayer) {
		this.pPlayer = pPlayer;
	}

	public void setThrowCount(int pThrowCount) {
		this.pThrowCount = pThrowCount;
	}


	/**
	 * 
	 * Throw dices to move 
	 * 
	 */
	private void throwDices() {
		final CDiceThrow wDiceThrow = new CDiceThrow();
		this.addThrow();
		final StringBuilder wStringBuilder = new StringBuilder(); 
		wStringBuilder.append(System.getProperty("line.separator"));
		wStringBuilder.append("Vous secouez les dés, lancez et faites un magnifique : ");
		wStringBuilder.append(wDiceThrow.getResult());
		wStringBuilder.append(" ( ");
		wStringBuilder.append(wDiceThrow.toString());
		wStringBuilder.append(" ) ");
		if(wDiceThrow.isDouble()) {
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("Quel boss, vous avez fait un double.");
		}
		System.out.println(wStringBuilder.toString());	
		// If the player has a third double in a row, send him directly to jail
		if(this.pThrowCount==3 && wDiceThrow.isDouble()) {
			this.pPlayer.goToJail();
		}
		// Else move player
		else {
			// Move the player
			final boolean wPlayAgain = this.pPlayer.move(wDiceThrow);
			//If he can play again (not going to jail) and does a double, throw again the dices
			if(wPlayAgain && wDiceThrow.isDouble()) {
				this.displayMenu();
			}
		}
	}

}
