package fr.uryuker.games.buyeverything.spaces;

import java.util.ArrayList;

public class CSpaceFamily {

	private String pName;
	private String pHexColor;
	private ArrayList<CPropertySpace> pSpaces= new ArrayList<>();
	
	public CSpaceFamily(String aName, String aHexColor){
		this.setHexColor(aHexColor);
		this.setName(aName);
	}
	
	public void addSpace(CPropertySpace aSpace) {
		this.pSpaces.add(aSpace);
	}
	public String getHexColor() {
		return this.pHexColor;
	}
	public String getName() {
		return this.pName;
	}
	public ArrayList<CPropertySpace> getSpaces() {
		return this.pSpaces;
	}
	public void setHexColor(String pHexColor) {
		this.pHexColor = pHexColor;
	}
	public void setName(String pName) {
		this.pName = pName;
	}
	public void setSpaces(ArrayList<CPropertySpace> pSpaces) {
		this.pSpaces = pSpaces;
	}
}
