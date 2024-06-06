package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "predmeti")
public class Predmet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_predmeta")
	private int idPredmeta;

	@Column(name = "ime_predmeta")
	private String imePredmeta;

	@Column(name = "godina")
	private int godina;

	@Column(name = "broj_kredita")
	private int brojKredita;

	@Column(name = "opis_predmeta")
    private String opisPredmeta;
	
	public Predmet() {};
	public Predmet(int idPredmeta, String imePredmeta, int godina, int brojKredita, String opisPredmeta) {
		this.idPredmeta = idPredmeta;
		this.imePredmeta = imePredmeta;
		this.godina = godina;
		this.brojKredita = brojKredita;
		this.opisPredmeta = opisPredmeta;
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
	
}
