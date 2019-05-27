package fr.uryuker.games.buyeverything.spaces;

public class CPropertySpace extends ASpace {
	private int pPrice;
	public CPropertySpace(String aName) {
		super(aName);
	}
	public CPropertySpace(String aName, int aPrice) {
		super(aName);
		this.setPrice(aPrice);
	}
	public int getPrice() {
		return this.pPrice;
	}
	public void setPrice(int aPrice) {
		this.pPrice = aPrice;
	}

	@Override
	public String toString() {
		final StringBuilder wStringBuilder = new StringBuilder(); 
		wStringBuilder.append(this.getClass().getSimpleName());
		wStringBuilder.append(" : \"");
		wStringBuilder.append(this.getName());
		wStringBuilder.append("\" which costs ");
		wStringBuilder.append(this.getPrice());
		wStringBuilder.append(CURRENCY);
		
		return wStringBuilder.toString();
	}
}
