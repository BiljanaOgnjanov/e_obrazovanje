package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "nastavnici")
//@PrimaryKeyJoinColumn(name = "korisnicko_ime")
public class Nastavnik extends Korisnik{

    @Column(name = "katedra")
    private String katedra;

    @Column(name = "uloga")
    private String uloga;
    
    public Nastavnik(){
        super();
    }

    public Nastavnik(String korisnicko_ime, String lozinka, String ime, String prezime, String pol, String telefon,String tip, String profilna_slika, String status, String katedra, String uloga){
        super(korisnicko_ime, lozinka, ime, prezime, pol, telefon, tip, profilna_slika, status);
        this.katedra = katedra;
        this.uloga = uloga;
    }

    public void setSmer(String katedra) {
        this.katedra = katedra;
    }

    public String getKatedra() {
        return katedra;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public String getUloga() {
        return uloga;
    }
}
