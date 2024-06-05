package com.example.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;


@Entity
@Table(name = "uplate")
public class Uplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uplate")
    private int idUplate;

    @Column(name = "svrha_uplate")
    private String svrhaUplate;

    @Column(name = "korisnicko_ime")
    private String korisnickoIme;

    @Column(name = "iznos")
    private int iznos;

    @Column(name = "datum")
    private String datum;

    @ManyToOne
    @JoinColumn(name = "korisnicko_ime", referencedColumnName = "korisnicko_ime", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;

    public Uplate(){}

    public Uplate(int id_uplate, String svrha_uplate, String korisnicko_ime, int iznos, String datum){
        this.idUplate=id_uplate;
        this.svrhaUplate = svrha_uplate;
        this.korisnickoIme=korisnicko_ime;
        this.iznos=iznos;
        this.datum=datum;
    }

    public int getIdUplate() {
        return idUplate;
    }

    public void setIdUplate(int id_uplate) {
        this.idUplate = id_uplate;
    }
    public String getSvrhaUplate() {
        return svrhaUplate;
    }

    public void setSvrhaUplate(String svrha_uplate) {
        this.svrhaUplate = svrha_uplate;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnicko_ime) {
        this.korisnickoIme = korisnicko_ime;
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
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

