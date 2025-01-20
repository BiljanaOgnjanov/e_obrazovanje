package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ispitni_rokovi")
public class IspitniRokovi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roka")
    private int idRoka;

    @Column(name = "naziv_roka", nullable = false)
    private String nazivRoka;

    @Column(name = "pocetak_roka", nullable = false)
    private String pocetakRoka;

    @Column(name = "kraj_roka", nullable = false)
    private String krajRoka;

    // Default constructor
    public IspitniRokovi() {}

    // Parameterized constructor
    public IspitniRokovi(int idRoka, String nazivRoka, String pocetakRoka, String krajRoka) {
        this.idRoka = idRoka;
        this.nazivRoka = nazivRoka;
        this.pocetakRoka = pocetakRoka;
        this.krajRoka = krajRoka;
    }

    // Getters and Setters
    public int getIdRoka() {
        return idRoka;
    }

    public void setIdRoka(int idRoka) {
        this.idRoka = idRoka;
    }

    public String getNazivRoka() {
        return nazivRoka;
    }

    public void setNazivRoka(String nazivRoka) {
        this.nazivRoka = nazivRoka;
    }

    public String getPocetakRoka() {
        return pocetakRoka;
    }

    public void setPocetakRoka(String pocetakRoka) {
        this.pocetakRoka = pocetakRoka;
    }

    public String getKrajRoka() {
        return krajRoka;
    }

    public void setKrajRoka(String krajRoka) {
        this.krajRoka = krajRoka;
    }
}