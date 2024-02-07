package sn.esmt.gymManagement.models.beans.enums;

public enum TypeAbonnement {
	MENSUEL (1),
	TRIMESTRIEL (3),
	ANNUEL (12);
	
	private int value;
	
	private TypeAbonnement(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
