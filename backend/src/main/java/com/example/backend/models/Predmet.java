package com.example.backend.models;


public class Predmet {
	
	private int idPredmeta;
	private String imePredmeta;
	private int godina;
	private int brojKredita;
    private String opisPredmeta;
	private String predispitneObaveze;
	
	public Predmet() {};
	public Predmet(int idPredmeta, String imePredmeta, int godina, int brojKredita, String opisPredmeta, String predispitneObaveze) {
		this.idPredmeta = idPredmeta;
		this.imePredmeta = imePredmeta;
		this.godina = godina;
		this.brojKredita = brojKredita;
		this.opisPredmeta = opisPredmeta;
		this.predispitneObaveze = predispitneObaveze;
	}
	public int getIdPredmeta() {
		return idPredmeta;
	}
	public void setIdPredmeta(int idPredmeta) {
		this.idPredmeta = idPredmeta;
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
		return predispitneObaveze;
	}
	public void setPredispitneObaveze(String predispitneObaveze) {
		this.predispitneObaveze = predispitneObaveze;
	}
	
}
