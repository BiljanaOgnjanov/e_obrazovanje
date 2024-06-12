package com.example.backend.models;

import jakarta.persistence.*;


@Entity
@Table(name = "studenti")
public class Student extends Korisnik {

    @Column(name = "godina_upisa")
    private int godina_upisa;

    @Column(name = "broj_indeksa")
    private int broj_indeksa;

    @Column(name = "studijska_godina")
    private int studijska_godina;

    @Column(name = "smer")
    private String smer;

    public Student(){
        super();
    }

    public Student(String korisnicko_ime, String lozinka, String ime, String prezime, String pol, String telefon,String tip, String profilna_slika, String status, int godina_upisa, int broj_indeksa, int studijska_godina, String smer){
        super(korisnicko_ime, lozinka, ime, prezime, pol, telefon, tip, profilna_slika, status);
        this.godina_upisa = godina_upisa;
        this.broj_indeksa = broj_indeksa;
        this.studijska_godina = studijska_godina;
        this.smer=smer;
    }

    public int getGodinaUpisa() {
        return godina_upisa;
    }

    public void setGodinaUpisa(int godina_upisa) {
        this.godina_upisa = godina_upisa;
    }

    public int getBrojIndeksa() {
        return broj_indeksa;
    }

    public void setBrojIndeksa(int broj_indeksa) {
        this.broj_indeksa = broj_indeksa;
    }

    public int getStudijskaGodina() {
        return studijska_godina;
    }

    public void setStudijskaGodina(int studijska_godina) {
        this.studijska_godina = studijska_godina;
    }
    public void setSmer(String smer) {
        this.smer = smer;
    }

    public String getSmer() {
        return smer;
    }
}
