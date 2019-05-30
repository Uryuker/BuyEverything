package fr.uryuker.games.buyeverything.constants;

public enum ECardTypes {
	CHANCE("Chance"),
	COMMUNITY_CHEST("Caisse de Communauté");
	
	public static ECardTypes getEnum(String aValue) {
        for(final ECardTypes wValue : values()) {
			if(wValue.getValue().equalsIgnoreCase(aValue)) {
				return wValue;
			}
		}
        throw new IllegalArgumentException();
    }

	private String pValue;
	
	ECardTypes(String aValue) {
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
