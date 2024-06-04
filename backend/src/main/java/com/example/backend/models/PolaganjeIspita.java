package com.example.backend.models;

public class PolaganjeIspita {
    private int idPolaganjaIspita;
	private String rezultatiPredispitnihObaveza;
	private int ukupnoOsvojenoPoena;
	private int polozenIspit;
	private int ocena;
	private int idPremeta;
	
	public PolaganjeIspita() {};
	public PolaganjeIspita(int idPolaganjaIspita, String rezultatiPredispitnihObaveza, int ukupnoOsvojenoPoena, int polozenIspit, int ocena, int idPremeta) {
		this.idPolaganjaIspita = idPolaganjaIspita;
		this.rezultatiPredispitnihObaveza = rezultatiPredispitnihObaveza;
		this.ukupnoOsvojenoPoena = ukupnoOsvojenoPoena;
		this.polozenIspit = polozenIspit;
		this.ocena = ocena;
		this.idPremeta = idPremeta;
	}
	public int getIdPolaganjaIspita() {
		return idPolaganjaIspita;
	}
	public void setIdPolaganjaIspita(int idPolaganjaIspita) {
		this.idPolaganjaIspita = idPolaganjaIspita;
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
	public int getIdPremeta() {
		return idPremeta;
	}
	public void setIdPremeta(int idPremeta) {
		this.idPremeta = idPremeta;
	}
}
