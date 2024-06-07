package com.example.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "rezultati_ispitnih_obaveza")
public class RezultatiIspitnihObaveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rez_io")
    private int idRezIo;

    @Column(name ="id_pracenja_predmeta", insertable = false, updatable = false)
    private int idPracenjaPredmeta;

    @Column(name ="id_io", insertable = false, updatable = false)
    private int idIo;

    @Column(name = "osvojeno_poena", nullable = false)
    private int osvojenoPoena;

    @Column(name = "rok")
    private String rok;

    @Column(name = "polozen_ispit_u_roku", nullable = false)
    private int polozenIspitURoku;

    @ManyToOne
    @JoinColumn(name = "id_pracenja_predmeta", referencedColumnName = "id_pracenja_predmeta", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private PracenjePredmeta pracenjePredmeta;

    @ManyToOne
    @JoinColumn(name = "id_io", referencedColumnName = "id_io", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private IspitneObaveze ispitneObaveze;

    public RezultatiIspitnihObaveza() {}

    public RezultatiIspitnihObaveza(int idRezIo, int osvojenoPoena, String rok, int polozenIspitURoku, int idPracenjaPredmeta, int idIo) {
        this.idRezIo = idRezIo;
        this.idPracenjaPredmeta = idPracenjaPredmeta;
        this.idIo = idIo;
        this.osvojenoPoena = osvojenoPoena;
        this.rok = rok;
        this.polozenIspitURoku = polozenIspitURoku;
    }

    public int getIdRezIo() {
        return idRezIo;
    }

    public void setIdRezIo(int idRezIo) {
        this.idRezIo = idRezIo;
    }

    public PracenjePredmeta getPracenjePredmeta() {
        return pracenjePredmeta;
    }

    public void setPracenjePredmeta(PracenjePredmeta pracenjePredmeta) {
        this.pracenjePredmeta = pracenjePredmeta;
    }

    public int getIdPracenjaPredmeta() {
        return idPracenjaPredmeta;
    }

    public void setIdPracenjaPredmeta(int idPracenjaPredmeta) {
        this.idPracenjaPredmeta = idPracenjaPredmeta;
    }

    public IspitneObaveze getIspitneObaveze() {
        return ispitneObaveze;
    }

    public void setIspitneObaveze(IspitneObaveze ispitneObaveze) {
        this.ispitneObaveze = ispitneObaveze;
    }

    public int getOsvojenoPoena() {
        return osvojenoPoena;
    }

    public void setOsvojenoPoena(int osvojenoPoena) {
        this.osvojenoPoena = osvojenoPoena;
    }

    public int getIdIo() {
        return idIo;
    }

    public void setIdIo(int idIo) {
        this.idIo = idIo;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public int getPolozenIspitURoku() {
        return polozenIspitURoku;
    }

    public void setPolozenIspitURoku(int polozenIspitURoku) {
        this.polozenIspitURoku = polozenIspitURoku;
    }
}
