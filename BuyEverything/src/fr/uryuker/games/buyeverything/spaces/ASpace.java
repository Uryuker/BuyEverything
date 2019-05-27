package fr.uryuker.games.buyeverything.spaces;

import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.entities.CPlayer;

public abstract class ASpace implements IGameRules {

	private String pName;

	public ASpace(String aName) {
		this.setName(aName);
	}
	
	public void doAction(CPlayer aPlayer) {
		//TODO
		System.out.println(this.toString());
	}

	public String getName() {
		return this.pName;
	}
	
	public void setName(String pName) {
		this.pName = pName;
	}
	
	@Override
	public String toString() {
		final StringBuilder wStringBuilder = new StringBuilder(); 
		wStringBuilder.append(this.getClass().getSimpleName());
		wStringBuilder.append(" : ");
		wStringBuilder.append(this.getName());
		return wStringBuilder.toString();
	}
}
