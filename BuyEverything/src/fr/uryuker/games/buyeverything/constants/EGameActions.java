package fr.uryuker.games.buyeverything.constants;

public enum EGameActions {
	THROW_DICES("Lancer les dés"),
	PLAY_CARD("Jouer une carte sortie de prison");
	
	public static EGameActions getEnum(String aValue) {
        for(final EGameActions wValue : values()) {
			if(wValue.getValue().equalsIgnoreCase(aValue)) {
				return wValue;
			}
		}
        throw new IllegalArgumentException();
    }

	private String pValue;
	
	EGameActions(String aValue) {
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
