package fr.uryuker.games.buyeverything.board;

public class CBuilding {
	private EBuildingType pBuildingType;
	
	public CBuilding(EBuildingType aBuildingType) {
		this.setBuildingType(aBuildingType);
	}

	public EBuildingType getBuildingType() {
		return this.pBuildingType;
	}

	public void setBuildingType(EBuildingType pBuildingType) {
		this.pBuildingType = pBuildingType;
	}
}
