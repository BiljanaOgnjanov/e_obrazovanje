package com.example.backend.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "placanja")
public class Placanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_placanja")
    private int idPlacanja;

    @Column(name = "naziv_placanja", nullable = false)
    private String nazivPlacanja;

    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnickoIme;

    @Column(name = "iznos", nullable = false)
    private int iznos;

    @Column(name = "datum", nullable = false)
    private String datum;

    @Column(name = "komentar")
    private String komentar;

    @ManyToOne
    @JoinColumn(name = "korisnicko_ime", referencedColumnName = "korisnicko_ime", insertable = false, updatable = false)
    @JsonIgnore
    private Student student;

    // Default constructor
    public Placanja() {}

    // Parameterized constructor
    public Placanja(int idPlacanja, String nazivPlacanja, String korisnickoIme, int iznos, String datum, String komentar) {
        this.idPlacanja = idPlacanja;
        this.nazivPlacanja = nazivPlacanja;
        this.korisnickoIme = korisnickoIme;
        this.iznos = iznos;
        this.datum = datum;
        this.komentar = komentar;
    }

    // Getters and Setters
    public int getIdPlacanja() {
        return idPlacanja;
    }

    public void setIdPlacanja(int idPlacanja) {
        this.idPlacanja = idPlacanja;
    }

    public String getNazivPlacanja() {
        return nazivPlacanja;
    }

    public void setNazivPlacanja(String nazivPlacanja) {
        this.nazivPlacanja = nazivPlacanja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}