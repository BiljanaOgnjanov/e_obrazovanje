package com.example.backend.models;

public class MojiPredmetiDTO {
	private int idPredmeta;
    private String imePredmeta;
    private int godina;
    private int brojKredita;
    private String opisPredmeta;
    private int idPracenjaPredmeta;
    private int skolskaGodina;
    private int polozenIspit;
    private Integer ocena;
    
    public MojiPredmetiDTO() {}
	public MojiPredmetiDTO(int idPredmeta, String imePredmeta, int godina, int brojKredita, String opisPredmeta, int idPracenjaPredmeta, int skolskaGodina, int polozenIspit, Integer ocena) {
		this.idPredmeta = idPredmeta;
		this.imePredmeta = imePredmeta;
		this.godina = godina;
		this.brojKredita = brojKredita;
		this.opisPredmeta = opisPredmeta;
		this.idPracenjaPredmeta = idPracenjaPredmeta;
		this.skolskaGodina = skolskaGodina;
		this.polozenIspit = polozenIspit;
		this.ocena = ocena;
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
	public int getIdPracenjaPredmeta() {
		return idPracenjaPredmeta;
	}
	public void setIdPracenjaPredmeta(int idPracenjaPredmeta) {
		this.idPracenjaPredmeta = idPracenjaPredmeta;
	}
	public int getSkolskaGodina() {
		return skolskaGodina;
	}
	public void setSkolskaGodina(int skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}
	public int getPolozenIspit() {
		return polozenIspit;
	}
	public void setPolozenIspit(int polozenIspit) {
		this.polozenIspit = polozenIspit;
	}
	public Integer getOcena() {
		return ocena;
	}
	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
	
}
