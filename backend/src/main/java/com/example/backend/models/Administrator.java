package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "administratori")
public class Administrator extends Korisnik {
    public Administrator() {
        super();
    }

    public Administrator(String korisnicko_ime, String lozinka, String ime, String prezime, String pol, String telefon,String tip, String profilna_slika, String status){
        super(korisnicko_ime, lozinka, ime, prezime, pol, telefon, tip, profilna_slika, status);
    }
}