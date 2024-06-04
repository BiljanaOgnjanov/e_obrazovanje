package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnici")
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik {
    
    @Id
    @Column(name = "korisnicko_ime")
    public String username;

    @Column(name = "lozinka")
    protected String password;

    @Column(name = "ime")
    protected String ime;

    @Column(name = "prezime")
    protected String prezime;

    @Column(name = "pol")
    protected String pol;

    @Column(name = "telefon")
    protected String telefon;

    @Column(name = "tip")
    protected String tip;

    @Column(name = "profilna_slika")
    protected String profilna_slika;

    @Column(name = "status")
    protected String status;

    public Korisnik() {
    }

    public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String pol, String telefon, String tip, String profilna_slika, String status) {    
        this.username = korisnickoIme;
        this.password = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.telefon = telefon;
        this.tip = tip;
        this.profilna_slika = profilna_slika;
        this.status = status;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String korisnicko_ime) {
        this.username = korisnicko_ime;
    }

    public String getLozinka() {
        return password;
    }

    public void setPassword(String lozinka) {
        this.password = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getProfilnaSlika() {
        return profilna_slika;
    }

    public void setProfilnaSlika(String profilna_slika) {
        this.profilna_slika = profilna_slika;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
