package fr.uryuker.games.buyeverything.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.uryuker.games.buyeverything.board.CBoard;
import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.entities.CPlayer;
import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.spaces.CChanceSpace;
import fr.uryuker.games.buyeverything.spaces.CCommunityChestSpace;
import fr.uryuker.games.buyeverything.spaces.CRailwayStationSpace;
import fr.uryuker.games.buyeverything.spaces.CRoadSpace;
import fr.uryuker.games.buyeverything.spaces.CTaxSpace;
import fr.uryuker.games.buyeverything.spaces.CUtilitySpace;
import fr.uryuker.games.buyeverything.spaces.corner.CFreeParkingSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CGoToJailSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CJailSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CStartSpace;

public class CBoardGenerator implements IGameRules {

	/**
	 * Used to create the first map
	 * 
	 * Now you should use getEmptyBoard() which reads from the gson
	 * 
	 * @return ArrayList of spaces representing the board
	 */
	@Deprecated
	public static ArrayList<ASpace> generateEmptyBoard(){
		final ArrayList<ASpace> wSpaces = new ArrayList<ASpace> ();
		
		/*  First row  */
		wSpaces.add(new CStartSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard de Belleville", 60)); 
		wSpaces.add(new CCommunityChestSpace()); 
		wSpaces.add(new CRoadSpace("Rue Lecourbe", 60));  
		wSpaces.add(new CTaxSpace("Impôts sur le revenu")); 
		wSpaces.add(new CRailwayStationSpace("Gare Montparnasse")); 
		wSpaces.add(new CRoadSpace("Rue de Vaugirard", 100)); 
		wSpaces.add(new CChanceSpace()); 
		wSpaces.add(new CRoadSpace("Rue de Courcelles", 100)); 
		wSpaces.add(new CRoadSpace("Avenue de la République", 100)); 
		
		/*  Second row  */
		wSpaces.add(new CJailSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard de la Villette", 140)); 
		wSpaces.add(new CUtilitySpace("EDF")); 
		wSpaces.add(new CRoadSpace("Avenue de Neuilly", 140)); 
		wSpaces.add(new CRoadSpace("Rue de Paradis", 160)); 
		wSpaces.add(new CRailwayStationSpace("Gare de Lyon")); 
		wSpaces.add(new CRoadSpace("Avenue Mozart", 180)); 
		wSpaces.add(new CCommunityChestSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard Saint-Michel", 180)); 
		wSpaces.add(new CRoadSpace("Place Pigalle", 200)); 
		

		/*  Third row  */
		wSpaces.add(new CFreeParkingSpace()); 
		wSpaces.add(new CRoadSpace("Avenue Matignon", 220)); 
		wSpaces.add(new CChanceSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard Malesherbes", 220)); 
		wSpaces.add(new CRoadSpace("Avenue Henri-Martin", 240)); 
		wSpaces.add(new CRailwayStationSpace("Gare du Nord")); 
		wSpaces.add(new CRoadSpace("Faubourg Saint-Honoré", 260)); 
		wSpaces.add(new CRoadSpace("Place de la Bourse", 260)); 
		wSpaces.add(new CUtilitySpace("Compagnie des eaux")); 
		wSpaces.add(new CRoadSpace("Rue La Fayette", 280));  
		

		/*  Fourth row  */
		wSpaces.add(new CGoToJailSpace()); 
		wSpaces.add(new CRoadSpace("Avenue de Breteuil", 300)); 
		wSpaces.add(new CRoadSpace("Avenue Foch", 300)); 
		wSpaces.add(new CCommunityChestSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard des Capucines", 320)); 
		wSpaces.add(new CRailwayStationSpace("Gare Saint-Lazare")); 
		wSpaces.add(new CChanceSpace()); 
		wSpaces.add(new CRoadSpace("Avenue des Champs-Élysées", 350)); 
		wSpaces.add(new CTaxSpace("Impôts sur Taxe de luxe")); 
		wSpaces.add(new CRoadSpace("Rue de la Paix", 400)); 
		
		return wSpaces;
		
	}
	
	public static CBoard getEmptyBoard(){
		final GsonBuilder wBuilder = new GsonBuilder();
		wBuilder.registerTypeAdapter(ASpace.class, new CGsonAdapter());
		wBuilder.setPrettyPrinting();
		final Gson wGson = wBuilder.create();
		String wContent ="";
		try {
			wContent=new String ( Files.readAllBytes( Paths.get("./test/map.json") ) );
		} catch (final IOException e) {
			return new CBoard();
		}
		final CBoard wBoard = wGson.fromJson(wContent, CBoard.class);
		return wBoard;
	}
	

	
	/**
	 * Used for tests, pick your own players
	 * 
	 * @return ArrayList of users
	 */
	@Deprecated
	public static ArrayList<CPlayer> initDefaultplayers(){
		final ArrayList<CPlayer> wPlayers = new ArrayList<CPlayer> ();
		wPlayers.add(new CPlayer("Quentin"));
		wPlayers.add(new CPlayer("Gaya"));
		wPlayers.add(new CPlayer("Thomas", "sbire.png"));
		return wPlayers;
	}
	
	/**
	 * @param aBoard
	 * 
	 * Write the board in json by the gson factory
	 */
	public static void writeBoard(CBoard aBoard) {
		final GsonBuilder wBuilder = new GsonBuilder();
		wBuilder.registerTypeAdapter(ASpace.class, new CGsonAdapter());
		wBuilder.setPrettyPrinting();
		final Gson wGson = wBuilder.create();
		final String wJson = wGson.toJson(aBoard);
		PrintWriter wOutput = null;
		try {
			wOutput = new PrintWriter("./test/map.json");
			wOutput.println(wJson);
		} catch (final FileNotFoundException e) {
			System.out.println("<writeBoard> Save map failed : Unable to open file");
			e.printStackTrace();
		}
		finally {
			wOutput.close();
		}
		
	}
	
	
}
