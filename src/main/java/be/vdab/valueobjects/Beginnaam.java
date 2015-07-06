package be.vdab.valueobjects;

public class Beginnaam {
	private String beginnaam;

	public String getBeginnaam() {
		return beginnaam;
	}

	public void setBeginnaam(String beginnaam) {
		this.beginnaam = beginnaam;
	}
	
	public boolean startMet(String naam){
		return (naam.toLowerCase().startsWith(beginnaam.toLowerCase()));
	}
}
