package com.example.backend.models;

public class MojiPredmetiDTO {
    private int idPredmeta;
	private int idPolaganjaIspita;
	private int idPracenjaPredmeta;
	private String korisnickoIme;
	private int skolskaGodina;
	private String rezultatiPredispitnihObaveza;
	private int ukupnoOsvojenoPoena;
	private int polozenIspit;
	private int ocena;
	private String imePredmeta;
	private int godina;
	private int brojKredita;
	private String opisPredmeta;
	private String PredispitneObaveze;
	
	public MojiPredmetiDTO() {};
	public MojiPredmetiDTO(int idPredmeta, int idPolaganjaIspita, int idPracenjaPredmeta, String korisnickoIme,
			int skolskaGodina, String rezultatiPredispitnihObaveza, int ukupnoOsvojenoPoena, int polozenIspit,
			int ocena, String imePredmeta, int godina, int brojKredita, String opisPredmeta,
			String predispitneObaveze) {
		super();
		this.idPredmeta = idPredmeta;
		this.idPolaganjaIspita = idPolaganjaIspita;
		this.idPracenjaPredmeta = idPracenjaPredmeta;
		this.korisnickoIme = korisnickoIme;
		this.skolskaGodina = skolskaGodina;
		this.rezultatiPredispitnihObaveza = rezultatiPredispitnihObaveza;
		this.ukupnoOsvojenoPoena = ukupnoOsvojenoPoena;
		this.polozenIspit = polozenIspit;
		this.ocena = ocena;
		this.imePredmeta = imePredmeta;
		this.godina = godina;
		this.brojKredita = brojKredita;
		this.opisPredmeta = opisPredmeta;
		PredispitneObaveze = predispitneObaveze;
	}
	public int getIdPredmeta() {
		return idPredmeta;
	}
	public void setIdPredmeta(int idPredmeta) {
		this.idPredmeta = idPredmeta;
	}
	public int getIdPolaganjaIspita() {
		return idPolaganjaIspita;
	}
	public void setIdPolaganjaIspita(int idPolaganjaIspita) {
		this.idPolaganjaIspita = idPolaganjaIspita;
	}
	public int getIdPracenjaPredmeta() {
		return idPracenjaPredmeta;
	}
	public void setIdPracenjaPredmeta(int idPracenjaPredmeta) {
		this.idPracenjaPredmeta = idPracenjaPredmeta;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public int getSkolskaGodina() {
		return skolskaGodina;
	}
	public void setSkolskaGodina(int skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}
	public String getRezultatiPredispitnihObaveza() {
		return rezultatiPredispitnihObaveza;
	}
	public void setRezultatiPredispitnihObaveza(String rezultatiPredispitnihObaveza) {
		this.rezultatiPredispitnihObaveza = rezultatiPredispitnihObaveza;
	}
	public int getUkupnoOsvojenoPoena() {
		return ukupnoOsvojenoPoena;
	}
	public void setUkupnoOsvojenoPoena(int ukupnoOsvojenoPoena) {
		this.ukupnoOsvojenoPoena = ukupnoOsvojenoPoena;
	}
	public int getPolozenIspit() {
		return polozenIspit;
	}
	public void setPolozenIspit(int polozenIspit) {
		this.polozenIspit = polozenIspit;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public String getImePredmeta() {
		return imePredmeta;
	}
	public void setImePredmeta(String imePredmeta) {
		this.imePredmeta = imePredmeta;
	}
	public int getGodina() {
		return godina;
	}
	public void setGodina(int godina) {
		this.godina = godina;
	}
	public int getBrojKredita() {
		return brojKredita;
	}
	public void setBrojKredita(int brojKredita) {
		this.brojKredita = brojKredita;
	}
	public String getOpisPredmeta() {
		return opisPredmeta;
	}
	public void setOpisPredmeta(String opisPredmeta) {
		this.opisPredmeta = opisPredmeta;
	}
	public String getPredispitneObaveze() {
		return PredispitneObaveze;
	}
	public void setPredispitneObaveze(String predispitneObaveze) {
		PredispitneObaveze = predispitneObaveze;
	}
	
}
