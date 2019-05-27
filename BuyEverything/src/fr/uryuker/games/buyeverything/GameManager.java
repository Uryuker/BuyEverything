package fr.uryuker.games.buyeverything;

import fr.uryuker.games.buyeverything.engine.CGame;

public class GameManager {

	public static void main(String[] args) {
		final CGame wNewGame = CGame.getInstance();
		try {
			wNewGame.startGame();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
