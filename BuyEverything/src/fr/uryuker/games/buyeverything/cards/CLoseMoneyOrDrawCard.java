package fr.uryuker.games.buyeverything.cards;

import java.util.Scanner;

import fr.uryuker.games.buyeverything.constants.ECardActions;
import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public class CLoseMoneyOrDrawCard  extends ACard{

	private ECardTypes pTypeToDraw;

	public CLoseMoneyOrDrawCard(String aTitle, ECardTypes aType, int[] aValues, ECardTypes aTypeCardToDraw) {
		super(aTitle, aType, aValues);
		this.setTypeToDraw(aTypeCardToDraw);
	}

	private ECardActions displayMenu() {
		StringBuilder wStringBuilder = new StringBuilder(); 
		wStringBuilder.append("Vous avez le choix : ");
		wStringBuilder = new StringBuilder();
		for(final ECardActions wValue : ECardActions.values()) {
			wStringBuilder.append(System.getProperty("line.separator"));
			wStringBuilder.append("    - ");
			wStringBuilder.append(String.format(wValue.getValue(), this.getTypeToDraw().getValue()));
		}
		System.out.println(wStringBuilder.toString());
		final Scanner sc = new Scanner(System.in);
		final String wAction = sc.nextLine();
		sc.close();
		return ECardActions.valueOf(wAction);
	}
	@Override
	public void doAction(CPlayer aPlayer) {
		super.doAction(aPlayer);
		final ECardActions wAction= this.displayMenu();
		this.doMenuAction(wAction, aPlayer);
	}

	private void doMenuAction(ECardActions wAction, CPlayer aPlayer) {
		if(wAction == ECardActions.PAY) {
			aPlayer.removeMoney(this.getValues()[0]);
		}
		else if(wAction == ECardActions.DRAW) {
			aPlayer.drawCard(this.getTypeToDraw());
		}
		
	}
	public ECardTypes getTypeToDraw() {
		return this.pTypeToDraw;
	}

	public void setTypeToDraw(ECardTypes pTypeToDraw) {
		this.pTypeToDraw = pTypeToDraw;
	}

}
