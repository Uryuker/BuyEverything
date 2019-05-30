package fr.uryuker.games.buyeverything.constants;

public interface IGameRules {
	
	/* game stats */
	public final int BASE_MONEY=1500;
	public final String CURRENCY="€";
	public final int MIN_PLAYERS = 2;
	public final int MAX_PLAYERS = 8;
	public final int BOARD_LENGTH = 40;
	public final String MAX_DURATION = "none";
	public final int EXIT_JAIL_PRICE=50;
	
	/* default values */
	public final int DEFAULT_RAILWAY_STATION_SPACE_PRICE= 200;
	public final int DEFAULT_RAILWAY_STATION_MORTGAGE_PRICE= 100;
	public final int DEFAULT_UTILITY_SPACE_PRICE= 150;
	public final int DEFAULT_UTILITY_MORTGAGE_PRICE= 50;
	public final String DEFAULT_AVATAR = "Unknown.png";
}
