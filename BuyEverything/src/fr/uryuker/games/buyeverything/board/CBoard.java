package fr.uryuker.games.buyeverything.board;

import java.util.ArrayList;

import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.utils.CBoardGenerator;

public class CBoard {
 
	private ArrayList<ASpace> pSpaces = new ArrayList<>();
	
	public CBoard() {
		this.setSpaces(CBoardGenerator.generateEmptyBoard());
	}

	public ArrayList<ASpace> getSpaces() {
		return this.pSpaces;
	}

	public void setSpaces(ArrayList<ASpace> spaces) {
		this.pSpaces = spaces;
		CBoardGenerator.writeBoard(this);
	}
	
	@Override
	public String toString() {
		final StringBuilder wStringBuilder = new StringBuilder(); 
		wStringBuilder.append(this.getClass().getSimpleName());
		wStringBuilder.append(" : ");
		for(final ASpace wSpace : this.getSpaces()) {
			wStringBuilder.append(wSpace.toString());
			wStringBuilder.append(System.getProperty("line.separator"));
		}
		return wStringBuilder.toString();
	}
}
