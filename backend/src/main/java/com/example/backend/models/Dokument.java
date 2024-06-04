package com.example.backend.models;

public class Dokument {
    protected int id_dokumenta;
    protected String korisnicko_ime;
    protected String naziv_dokumenta;
    protected String tip_dokumenta;
    protected String putanja;

    public Dokument(){}

    public Dokument(int id_dokumenta, String korisnicko_ime, String naziv_dokumenta, String tip_dokumenta, String putanja){
        this.id_dokumenta=id_dokumenta;
        this.korisnicko_ime=korisnicko_ime;
        this.naziv_dokumenta=naziv_dokumenta;
        this.tip_dokumenta=tip_dokumenta;
        this.putanja =putanja;
    }

    public int getIdDokumenta() {
        return id_dokumenta;
    }

    public void setIdDokumenta(int id_dokumenta) {
        this.id_dokumenta = id_dokumenta;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }
    public String getNazivDokumenta() {
        return naziv_dokumenta;
    }

    public void setNazivDokumenta(String naziv_dokumenta) {
        this.naziv_dokumenta = naziv_dokumenta;
    }
    public String getTipDokumenta() {
        return tip_dokumenta;
    }

    public void setTipDokumenta(String tip_dokumenta) {
        this.tip_dokumenta = tip_dokumenta;
    }
    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }



}


