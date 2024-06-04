package com.example.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;


@Entity
@Table(name = "uplate")
public class Uplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uplate")
    protected int id_uplate;

    @Column(name = "svrha_uplate")
    protected String svrha_uplate;

    @Column(name = "korisnicko_ime")
    protected String korisnicko_ime;

    @Column(name = "iznos")
    protected int iznos;

    @Column(name = "datum")
    protected String datum;

    @ManyToOne
    @JoinColumn(name = "korisnicko_ime", referencedColumnName = "korisnicko_ime", insertable = false, updatable = false)
    private Student student;

    public Uplate(){}

    public Uplate(int id_uplate, String svrha_uplate, String korisnicko_ime, int iznos, String datum){
        this.id_uplate=id_uplate;
        this.svrha_uplate = svrha_uplate;
        this.korisnicko_ime=korisnicko_ime;
        this.iznos=iznos;
        this.datum=datum;
    }

    public int getIdUplate() {
        return id_uplate;
    }

    public void setIdUplate(int id_uplate) {
        this.id_uplate = id_uplate;
    }
    public String getSvrha_uplate() {
        return svrha_uplate;
    }

    public void setSvrha_uplate(String svrha_uplate) {
        this.svrha_uplate = svrha_uplate;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }
    public int getIznos() {
        return iznos;
    }

    public void setIznos(int iznos) {
        this.iznos = iznos;
    }
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}

