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
import fr.uryuker.games.buyeverything.cards.ACard;
import fr.uryuker.games.buyeverything.cards.CBuildingTaxCard;
import fr.uryuker.games.buyeverything.cards.CExitJailCard;
import fr.uryuker.games.buyeverything.cards.CGetMoneyCard;
import fr.uryuker.games.buyeverything.cards.CGetMoneyFromEveryoneCard;
import fr.uryuker.games.buyeverything.cards.CGoToJailCard;
import fr.uryuker.games.buyeverything.cards.CLoseMoneyCard;
import fr.uryuker.games.buyeverything.cards.CLoseMoneyOrDrawCard;
import fr.uryuker.games.buyeverything.cards.CMoveCard;
import fr.uryuker.games.buyeverything.cards.CMoveToCard;
import fr.uryuker.games.buyeverything.constants.ECardTypes;
import fr.uryuker.games.buyeverything.constants.IGameRules;
import fr.uryuker.games.buyeverything.entities.CPlayer;
import fr.uryuker.games.buyeverything.spaces.ASpace;
import fr.uryuker.games.buyeverything.spaces.CChanceSpace;
import fr.uryuker.games.buyeverything.spaces.CCommunityChestSpace;
import fr.uryuker.games.buyeverything.spaces.CRailwayStationSpace;
import fr.uryuker.games.buyeverything.spaces.CRoadSpace;
import fr.uryuker.games.buyeverything.spaces.CSpaceFamily;
import fr.uryuker.games.buyeverything.spaces.CTaxSpace;
import fr.uryuker.games.buyeverything.spaces.CUtilitySpace;
import fr.uryuker.games.buyeverything.spaces.corner.CFreeParkingSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CGoToJailSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CJailSpace;
import fr.uryuker.games.buyeverything.spaces.corner.CStartSpace;

public class CBoardGenerator implements IGameRules {

	private static ArrayList<? extends ACard> generateChances(){
		final ArrayList<ACard> wChances  = new ArrayList<>();

		wChances.add(new CExitJailCard("Vous êtes libéré de prison .Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée ou vendue.", ECardTypes.CHANCE, new int[0]));
		
		wChances.add(new CGoToJailCard("Allez en prison. Ne franchissez pas la case \"Départ\". Ne touchez pas 200"+CURRENCY, ECardTypes.CHANCE, new int[-3]));
		
		wChances.add(new CMoveCard("Reculez de 3 cases", ECardTypes.CHANCE, new int[-3]));

		wChances.add(new CMoveToCard("Avancez jusqu'à la case \"Départ\"", ECardTypes.CHANCE, new int[1], "Start"));
		wChances.add(new CMoveToCard("Rendez-vous Rue de la Paix", ECardTypes.CHANCE, new int[1], "Rue de la Paix"));
		wChances.add(new CMoveToCard("Rendez-vous à l'Avenue Henri-Martin", ECardTypes.CHANCE, new int[1], "Avenue Henri-Martin"));
		wChances.add(new CMoveToCard("Avancez au Bd de la Villette", ECardTypes.CHANCE, new int[1], "Boulevard de la Villette"));
		wChances.add(new CMoveToCard("Allez à la gare de Lyon", ECardTypes.CHANCE, new int[1], "Gare de Lyon"));
		
		wChances.add(new CLoseMoneyCard("Amende pour excès de vitesse : "+CURRENCY, ECardTypes.CHANCE, new int[0]));
		wChances.add(new CLoseMoneyCard("Payez les frais de scolarité : "+CURRENCY, ECardTypes.CHANCE, new int[0]));
		wChances.add(new CLoseMoneyCard("Amende pour ivresse : "+CURRENCY, ECardTypes.CHANCE, new int[0]));

		wChances.add(new CGetMoneyCard("La banque vous verse un dividende de "+CURRENCY, ECardTypes.CHANCE, new int[0]));
		wChances.add(new CGetMoneyCard("Votre immeuble et votre prêt rapportent. Vous devez toucher "+CURRENCY, ECardTypes.CHANCE, new int[0]));
		wChances.add(new CGetMoneyCard("Vous avez gagné le prix de mots croisés. Recevez "+CURRENCY, ECardTypes.CHANCE, new int[0]));

		wChances.add(new CBuildingTaxCard("Vous êtes imposé pour les réparations de voirie a raison de : 4.000"+CURRENCY+" par maison et 11.500"+CURRENCY+" par hotel", ECardTypes.CHANCE, new int[0]));
		wChances.add(new CBuildingTaxCard("Faites des réparations dans toutes vos maisons : 4.000"+CURRENCY+" par maison et 11.500"+CURRENCY+" par hotel", ECardTypes.CHANCE, new int[0]));
		
		return wChances;
		
	}

	private static ArrayList<? extends ACard> generateCommunities(){
		final ArrayList<ACard> wCommunities  = new ArrayList<>();
		wCommunities.add(new CExitJailCard("Vous êtes libéré de prison .Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée ou vendue.", ECardTypes.COMMUNITY_CHEST, new int[0]));
		
		wCommunities.add(new CGoToJailCard("Allez en prison. Ne franchissez pas la case \"Départ\". Ne touchez pas 200"+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[-3]));
		
		wCommunities.add(new CMoveToCard("Placez-vous sur la case \" Départ\".", ECardTypes.COMMUNITY_CHEST, new int[0], "Start"));
		wCommunities.add(new CMoveToCard("Retournez a Belleville", ECardTypes.COMMUNITY_CHEST, new int[-1], "Boulevard de Belleville"));
		
		wCommunities.add(new CGetMoneyFromEveryoneCard("C'est votre jour anniversaire chaque joueur doit vous donner 50"+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));

		wCommunities.add(new CGetMoneyCard("Vous avez gagné le 2nd prix de beauté. Recevez "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CGetMoneyCard("Erreur de la banque en votre faveur recevez "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CGetMoneyCard("Recevez votre intérêt sur l'emprunt à 7%, "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CGetMoneyCard("Recevez votre revenu annuel "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CGetMoneyCard("Les contributions vous remboursent la somme de "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CGetMoneyCard("Vous héritez de "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CGetMoneyCard("La vente de votre stock vous rapporte "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));

		wCommunities.add(new CLoseMoneyOrDrawCard("Payez un amende de 1.000"+CURRENCY+" ou bien tirez un carte \"CHANCE\"", ECardTypes.COMMUNITY_CHEST, new int[0], ECardTypes.CHANCE));
		
		wCommunities.add(new CLoseMoneyCard("Payez l'hôpital : "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CLoseMoneyCard("Payez la note du médecin : "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		wCommunities.add(new CLoseMoneyCard("Payez votre police d'assurance s'élevant à "+CURRENCY, ECardTypes.COMMUNITY_CHEST, new int[0]));
		
		return wCommunities;
	}
	/**
	 * Used to create the first map
	 * 
	 * Now you should use getEmptyBoard() which reads from the gson
	 * 
	 * @return ArrayList of spaces representing the board
	 */
	@Deprecated
	public static CBoard generateEmptyBoard(){
		final CBoard wBoard = new CBoard();
		final ArrayList<CSpaceFamily> wFamilies = new ArrayList<>();
		final CSpaceFamily wFamiliyBrown = new CSpaceFamily("Brown","#945333");
		wFamilies.add(wFamiliyBrown);
		
		final CSpaceFamily wFamiliyLightBlue = new CSpaceFamily("LightBlue","#a8e0f7");
		wFamilies.add(wFamiliyLightBlue);
		
		final CSpaceFamily wFamiliyPink = new CSpaceFamily("Pink","#da3895");
		wFamilies.add(wFamiliyPink);
		
		final CSpaceFamily wFamiliyOrange = new CSpaceFamily("Orange","#f8931d");
		wFamilies.add(wFamiliyOrange);
		
		final CSpaceFamily wFamiliyRed = new CSpaceFamily("Red","#ef1c26");
		wFamilies.add(wFamiliyRed);
		
		final CSpaceFamily wFamiliyYellow = new CSpaceFamily("Yellow","#fcf002");
		wFamilies.add(wFamiliyYellow);
		
		final CSpaceFamily wFamiliyGreen = new CSpaceFamily("Green","#1eb159");
		wFamilies.add(wFamiliyGreen);
		
		final CSpaceFamily wFamiliyDarkBlue = new CSpaceFamily("DarkBlue","#0173bc");
		wFamilies.add(wFamiliyDarkBlue);
		
		
		
		final ArrayList<ASpace> wSpaces = new ArrayList<ASpace> ();
		
		/*  First row  */
		wSpaces.add(new CStartSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard de Belleville", 60, 50, 50, 30, new int[]{2, 10, 30, 90, 160, 250}, wFamiliyBrown)); 
		wSpaces.add(new CCommunityChestSpace()); 
		wSpaces.add(new CRoadSpace("Rue Lecourbe", 60, 50, 50, 30, new int[]{4, 20, 60, 180, 320, 450}, wFamiliyBrown));  
		wSpaces.add(new CTaxSpace("Impôts sur le revenu", 200)); 
		wSpaces.add(new CRailwayStationSpace("Gare Montparnasse")); 
		wSpaces.add(new CRoadSpace("Rue de Vaugirard", 100, 50, 50, 50, new int[]{6, 30, 90, 270, 400, 550}, wFamiliyLightBlue)); 
		wSpaces.add(new CChanceSpace()); 
		wSpaces.add(new CRoadSpace("Rue de Courcelles", 100, 50, 50, 50, new int[]{6, 30, 90, 270, 400, 550}, wFamiliyLightBlue)); 
		wSpaces.add(new CRoadSpace("Avenue de la République", 100, 50, 50, 60, new int[]{8, 40, 100, 300, 450, 60}, wFamiliyLightBlue)); 
		
		/*  Second row  */
		wSpaces.add(new CJailSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard de la Villette", 140, 100, 100, 70, new int[]{10, 50, 150, 450, 625, 750}, wFamiliyPink)); 
		wSpaces.add(new CUtilitySpace("EDF")); 
		wSpaces.add(new CRoadSpace("Avenue de Neuilly", 140, 100, 100, 70, new int[]{10, 50, 150, 450, 625, 750}, wFamiliyPink)); 
		wSpaces.add(new CRoadSpace("Rue de Paradis", 160, 100, 100, 80, new int[]{12, 60, 180, 500, 700, 900}, wFamiliyPink)); 
		wSpaces.add(new CRailwayStationSpace("Gare de Lyon")); 
		wSpaces.add(new CRoadSpace("Avenue Mozart", 180, 100, 100, 90, new int[]{14, 70, 200, 550, 750, 950}, wFamiliyOrange)); 
		wSpaces.add(new CCommunityChestSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard Saint-Michel", 180, 100, 100, 90, new int[]{14, 70, 200, 550, 750, 950}, wFamiliyOrange)); 
		wSpaces.add(new CRoadSpace("Place Pigalle", 200, 100, 100, 100, new int[]{16, 80, 220, 600, 800, 1000}, wFamiliyOrange)); 
		

		/*  Third row  */
		wSpaces.add(new CFreeParkingSpace()); 
		wSpaces.add(new CRoadSpace("Avenue Matignon", 220, 150, 150, 110, new int[]{18, 90, 230, 700, 875, 1050}, wFamiliyRed)); 
		wSpaces.add(new CChanceSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard Malesherbes", 220, 150, 150, 110, new int[]{18, 90, 230, 700, 875, 1050}, wFamiliyRed)); 
		wSpaces.add(new CRoadSpace("Avenue Henri-Martin", 240, 150, 150, 120, new int[]{20, 10, 300, 750, 925, 1100}, wFamiliyRed)); 
		wSpaces.add(new CRailwayStationSpace("Gare du Nord")); 
		wSpaces.add(new CRoadSpace("Faubourg Saint-Honoré", 260, 150, 150, 130, new int[]{22, 110, 330, 800, 975, 1150}, wFamiliyYellow)); 
		wSpaces.add(new CRoadSpace("Place de la Bourse", 260, 150, 150, 130, new int[]{22, 110, 330, 800, 975, 1150}, wFamiliyYellow)); 
		wSpaces.add(new CUtilitySpace("Compagnie des eaux")); 
		wSpaces.add(new CRoadSpace("Rue La Fayette", 280, 150, 150, 140, new int[]{24, 120, 360, 850, 1025, 1200}, wFamiliyYellow));  
		

		/*  Fourth row  */
		wSpaces.add(new CGoToJailSpace()); 
		wSpaces.add(new CRoadSpace("Avenue de Breteuil", 300, 200, 200, 150, new int[]{26, 130, 390, 900, 1100, 1275}, wFamiliyGreen)); 
		wSpaces.add(new CRoadSpace("Avenue Foch", 300, 200, 200, 150, new int[]{26, 130, 390, 900, 1100, 1275}, wFamiliyGreen)); 
		wSpaces.add(new CCommunityChestSpace()); 
		wSpaces.add(new CRoadSpace("Boulevard des Capucines", 320, 200, 200, 160, new int[]{28, 150, 450, 1000, 1200, 1400}, wFamiliyGreen)); 
		wSpaces.add(new CRailwayStationSpace("Gare Saint-Lazare")); 
		wSpaces.add(new CChanceSpace()); 
		wSpaces.add(new CRoadSpace("Avenue des Champs-Élysées", 350, 200, 200, 175, new int[]{35, 175, 500, 1100, 1300, 1500}, wFamiliyDarkBlue)); 
		wSpaces.add(new CTaxSpace("Impôts sur Taxe de luxe", 100)); 
		wSpaces.add(new CRoadSpace("Rue de la Paix", 400, 200, 200, 200, new int[]{50, 200, 600, 1400, 1700, 2000}, wFamiliyDarkBlue)); 
		
		wBoard.setSpaces(wSpaces);
		wBoard.setFamilies(wFamilies);
		wBoard.setCommunities(generateCommunities());
		wBoard.setChances(generateChances());
		return wBoard;
		
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
