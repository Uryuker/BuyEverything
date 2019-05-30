package fr.uryuker.games.buyeverything.constants;

public enum ECardActions {
	PAY("Payer (Payer)"),
	DRAW("Piocher une carte %s (Piocher)");
	
	public static ECardActions getEnum(String aValue) {
        for(final ECardActions wValue : values()) {
			if(wValue.getValue().equalsIgnoreCase(aValue)) {
				return wValue;
			}
		}
        throw new IllegalArgumentException();
    }

	private String pValue;
	
	ECardActions(String aValue) {
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
