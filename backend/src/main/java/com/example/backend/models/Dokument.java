package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "dokumenta")
public class Dokument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dokumenta")
    private int idDokumenta;

    @Column(name = "korisnicko_ime")
    private String korisnickoIme;

    @Column(name = "naziv_dokumenta")
    private String nazivDokumenta;

    @Column(name = "tip_dokumenta")
    private String tipDokumenta;

    @Column(name = "putanja")
    private String putanja;

    @ManyToOne
    @JoinColumn(name = "korisnicko_ime", referencedColumnName = "korisnicko_ime", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;

    public Dokument(){}

    public Dokument(int id_dokumenta, String korisnicko_ime, String naziv_dokumenta, String tip_dokumenta, String putanja){
        this.idDokumenta=id_dokumenta;
        this.korisnickoIme=korisnicko_ime;
        this.nazivDokumenta=naziv_dokumenta;
        this.tipDokumenta=tip_dokumenta;
        this.putanja =putanja;
    }

    public int getIdDokumenta() {
        return idDokumenta;
    }

    public void setIdDokumenta(int id_dokumenta) {
        this.idDokumenta = id_dokumenta;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnicko_ime) {
        this.korisnickoIme = korisnicko_ime;
    }
    public String getNazivDokumenta() {
        return nazivDokumenta;
    }

    public void setNazivDokumenta(String naziv_dokumenta) {
        this.nazivDokumenta = naziv_dokumenta;
    }
    public String getTipDokumenta() {
        return tipDokumenta;
    }

    public void setTipDokumenta(String tip_dokumenta) {
        this.tipDokumenta = tip_dokumenta;
    }
    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }
    //public Student getStudent() {
    //    return student;
    //}

    //public void setStudent(Student student) {
     //   this.student = student;
    //}


}


