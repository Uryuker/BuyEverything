package fr.uryuker.games.buyeverything.constants;

public enum EBuyActions {
	BUY("Acheter la propriété (Acheter)"),
	SKIP("Passer sans acheter (Passer)"),
	BUY_HOUSE("Acheter une maison (Maison)"),
	BUY_HOTEL("Acheter un hôtel (Hotel)");
	
	public static EBuyActions getEnum(String aValue) {
        for(final EBuyActions wValue : values()) {
			if(wValue.getValue().equalsIgnoreCase(aValue)) {
				return wValue;
			}
		}
        throw new IllegalArgumentException();
    }

	private String pValue;
	
	EBuyActions(String aValue) {
        this.pValue = aValue;
    }

    public String getValue() {
        return this.pValue;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
