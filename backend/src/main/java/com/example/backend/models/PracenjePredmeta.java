package com.example.backend.models;

public class PracenjePredmeta {
	
	private int idPracenjaPredmeta;
	private String korisnickoIme;
	private int idPolaganjaIspita;
	private int skolskaGodina;
	
	public PracenjePredmeta() {};
	public PracenjePredmeta(int idPracenjaPredmeta, String korisnickoIme, int idPolaganjaIspita, int skolskaGodina) {
		this.idPracenjaPredmeta = idPracenjaPredmeta;
		this.korisnickoIme = korisnickoIme;
		this.idPolaganjaIspita = idPolaganjaIspita;
		this.skolskaGodina = skolskaGodina;
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
	public int getIdPolaganjaIspita() {
		return idPolaganjaIspita;
	}
	public void setIdPolaganjaIspita(int idPolaganjaIspita) {
		this.idPolaganjaIspita = idPolaganjaIspita;
	}
	public int getSkolskaGodina() {
		return skolskaGodina;
	}
	public void setSkolskaGodina(int skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}
}
