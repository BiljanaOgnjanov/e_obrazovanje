package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "predavanje_predmeta")
public class PredavanjePredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_predavanja_predmeta")
    private int idPredavanjaPredmeta;

    @Column(name = "korisnicko_ime")
    private String korisnickoIme;

    @ManyToOne
    @JoinColumn(name = "id_predmeta", referencedColumnName = "id_predmeta")
    private Predmet predmet;

    public PredavanjePredmeta() {
    }

    public PredavanjePredmeta(String korisnickoIme, Predmet predmet) {
        this.korisnickoIme = korisnickoIme;
        this.predmet = predmet;
    }

    public int getIdPredavanjaPredmeta() {
        return idPredavanjaPredmeta;
    }

    public void setIdPredavanjaPredmeta(int idPredavanjaPredmeta) {
        this.idPredavanjaPredmeta = idPredavanjaPredmeta;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
