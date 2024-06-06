package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Entity
@Table(name = "pracenje_predmeta")
public class PracenjePredmeta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pracenja_predmeta")
	private int idPracenjaPredmeta;

	@Column(name = "korisnicko_ime")
	private String korisnickoIme;

	@Column(name = "skolska_godina")
	private int skolskaGodina;

	@Column(name = "polozen_ispit")
	private int polozenIspit;

	@Column(name = "ocena")
	private int ocena;

	@Column(name = "id_predmeta")
	private int idPredmeta;

	@ManyToOne
    @JoinColumn(name = "id_predmeta", referencedColumnName = "id_predmeta", insertable = false, updatable = false)
    @JsonIgnore
    private Predmet predmet;

	@ManyToOne
	@JoinColumn(name = "korisnicko_ime", referencedColumnName = "korisnicko_ime", insertable = false, updatable = false)
	private Student student;
	
	public PracenjePredmeta() {};
	public PracenjePredmeta(int idPracenjaPredmeta, String korisnickoIme, int skolskaGodina, int polozenIspit, int ocena, int idPredmeta) {
		this.idPracenjaPredmeta = idPracenjaPredmeta;
		this.korisnickoIme = korisnickoIme;
		this.skolskaGodina = skolskaGodina;
		this.polozenIspit = polozenIspit;
		this.ocena = ocena;
		this.idPredmeta = idPredmeta;
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
	public int getidPredmeta() {
		return idPredmeta;
	}
	public void setIdPredmeta(int idPredmeta) {
		this.idPredmeta = idPredmeta;
	}
	public Predmet getPredmet() {
		return predmet;
	}
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
