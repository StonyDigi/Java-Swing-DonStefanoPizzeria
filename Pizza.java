package hfDonStefanoPizzeria;

public class Pizza {
	private String azonosito;
	private String nev;
	private Integer egysegAr;
	private Integer eladottDb;
	private Boolean akcios;
	
	public Pizza(String azonosito, String nev, Integer egysegAr, Integer eladottDb, Boolean akcios) {
		
		this.azonosito = azonosito;
		this.nev = nev;
		this.egysegAr = egysegAr;
		this.eladottDb = eladottDb;
		this.akcios = akcios;
	}

	public String getAzonosito() {
		return azonosito;
	}

	public String getNev() {
		return nev;
	}

	public Integer getEgysegAr() {
		return egysegAr;
	}

	public Integer getEladottDb() {
		return eladottDb;
	}

	public Boolean getAkcios() {
		return akcios;
	}
	public String toString() {
		return this.azonosito + ";"+this.nev + ";"+this.egysegAr + ";"+this.eladottDb + ";"+this.akcios;
		
	}
	public String getAkciosSzovegesen() {
		String valasz = "Nem";
		if(this.akcios) {
			valasz = "Igen";
		} else {
			valasz = "Nem";	
			}
		return valasz;
		}
	}