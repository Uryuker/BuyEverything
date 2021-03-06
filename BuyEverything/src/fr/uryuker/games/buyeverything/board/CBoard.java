package fr.uryuker.games.buyeverything.board;

import java.util.ArrayList;

import fr.uryuker.games.buyeverything.cards.ACard;
import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.spaces.CSpaceFamily;
import fr.uryuker.games.buyeverything.spaces.corner.CJailSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CStartSpace;
import fr.uryuker.games.buyeverything.utils.CBoardGenerator;

public class CBoard {
 
	private ArrayList<ASpace> pSpaces = new ArrayList<>();
	private ArrayList<CSpaceFamily> pFamilies = new ArrayList<>();
	private ArrayList<? extends ACard> pCommunities = new ArrayList<>();
	private ArrayList<? extends ACard> pChances = new ArrayList<>();
	private int pStartSpaceIndex;
	private int pJailSpaceIndex;
	
	public void generate() {
		this.setSpaces(CBoardGenerator.generateEmptyBoard().getSpaces());
		this.setFamilies(CBoardGenerator.generateEmptyBoard().getFamilies());
	}
	
	public ArrayList<? extends ACard> getChances() {
		return this.pChances;
	}

	public ArrayList<? extends ACard> getCommunities() {
		return this.pCommunities;
	}

	public ArrayList<CSpaceFamily> getFamilies() {
		return this.pFamilies;
	}

	public int getJailSpaceIndex() {
		return this.pJailSpaceIndex;
	}

	public ASpace getSpaceAt(int aIndex) {
		return this.pSpaces.get(aIndex);
	}
	
	public ArrayList<ASpace> getSpaces() {
		return this.pSpaces;
	}

	public int getStartSpaceIndex() {
		return this.pStartSpaceIndex;
	}

	public void setChances(ArrayList<? extends ACard> pChances) {
		this.pChances = pChances;
	}

	public void setCommunities(ArrayList<? extends ACard> pCommunities) {
		this.pCommunities = pCommunities;
	}

	public void setFamilies(ArrayList<CSpaceFamily> pFamilies) {
		this.pFamilies = pFamilies;
	}

	public void setJailSpaceIndex(int pJailSpaceIndex) {
		this.pJailSpaceIndex = pJailSpaceIndex;
	}

	public void setSpaces(ArrayList<ASpace> spaces) {
		this.pSpaces = spaces;
		CBoardGenerator.writeBoard(this);
		
		//Store start and jail space index for faster access later 
		boolean wFoundStart = false;
		boolean wFoundJail = false;
		for(int wSpaceIndex=0; wSpaceIndex<this.pSpaces.size(); wSpaceIndex++) {
			final ASpace wSpace = this.pSpaces.get(wSpaceIndex);
			if(wSpace instanceof CStartSpace) {
				this.setStartSpaceIndex(wSpaceIndex);
				wFoundStart=true;
			}
			if(wSpace instanceof CJailSpace) {
				this.setJailSpaceIndex(wSpaceIndex);
				wFoundJail=true;
			}
			if(wFoundJail && wFoundStart) {
				break;
			}
		}
	}

	public void setStartSpaceIndex(int pStartSpaceIndex) {
		this.pStartSpaceIndex = pStartSpaceIndex;
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
