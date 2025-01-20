package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "ispitne_obaveze")
public class IspitneObaveze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_io", insertable =false, updatable=false)
    private int idIo;

    @Column(name = "naziv_ispitne_obaveze")
    private String nazivIspitneObaveze;

    @Column(name = "broj_poena")
    private int brojPoena;

    @Column(name = "id_predmeta")
    private int idPredmeta;

    @Column(name = "datum", nullable = false)
    private String datum;

    @Column(name = "rok", insertable = false, updatable = false, nullable = false)
    private int rok;

    @ManyToOne
    @JoinColumn(name = "rok", referencedColumnName = "id_roka", insertable = false, updatable = false, nullable = false)
    private IspitniRokovi ispitniRokovi;


    @ManyToOne
    @JoinColumn(name = "id_predmeta", referencedColumnName = "id_predmeta", insertable = false, updatable = false)
    @JsonIgnore
    private Predmet predmet;

    public IspitneObaveze(){}

    public IspitneObaveze(int idIo, String naziv, int brojPoena, int idPredmeta, String datum, int rok){
        this.idIo = idIo;
        this.nazivIspitneObaveze = naziv;
        this.brojPoena = brojPoena;
        this.idPredmeta=idPredmeta;
        this.datum = datum;
        this.rok = rok;
    }
    public int getIdIo() {
        return idIo;
    }

    public void setIdIo(int idIo) {
        this.idIo = idIo;
    }

    public String getNazivIspitneObaveze() {
        return nazivIspitneObaveze;
    }

    public void setNazivIspitneObaveze(String nazivIspitneObaveze) {
        this.nazivIspitneObaveze = nazivIspitneObaveze;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getIdPredmeta() {
        return idPredmeta;
    }

    public void setidPredmeta(int idPredmeta) {
        this.idPredmeta = idPredmeta;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
    public IspitniRokovi getIspitniRok() {
        return ispitniRokovi;
    }

    public void setIspitniRok(IspitniRokovi ispitniRokovi) {
        this.ispitniRokovi = ispitniRokovi;
    }
}
