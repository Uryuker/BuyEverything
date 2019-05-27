package fr.uryuker.games.buyeverything.entities;

import fr.uryuker.games.buyeverything.constants.IGameRules;

public class CPlayer implements IGameRules{

	private String pName;
	private String pAvatar;
	private int pMoney;
	
	public CPlayer(String aName) {
		this.setAvatar(DEFAULT_AVATAR);
		this.setName(aName);
		this.setMoney(BASE_MONEY);
	}
	
	public CPlayer(String aName, String aAvatar) {
		this.setAvatar(aAvatar);
		this.setName(aName);
		this.setMoney(BASE_MONEY);
	}
	
	public String getAvatar() {
		return this.pAvatar;
	}
	public int getMoney() {
		return this.pMoney;
	}
	public String getName() {
		return this.pName;
	}
	public void setAvatar(String pAvatar) {
		this.pAvatar = pAvatar;
	}
	public void setMoney(int pMoney) {
		this.pMoney = pMoney;
	}
	public void setName(String pName) {
		this.pName = pName;
	}
	
	
	
}
