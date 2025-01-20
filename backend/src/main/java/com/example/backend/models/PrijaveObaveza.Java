package com.example.backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "prijave_obaveza")
public class PrijaveObaveza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prijave_obaveza")
    private int idPrijaveObaveza;

    @Column(name = "id_pracenja_predmeta", insertable = false, updatable = false)
    private int idPracenjaPredmeta;

    @Column(name = "id_io", insertable = false, updatable = false)
    private int idIo;

    @ManyToOne
    @JoinColumn(name = "id_pracenja_predmeta", referencedColumnName = "id_pracenja_predmeta", insertable = false, updatable = false)
    @JsonIgnore
    private PracenjePredmeta pracenjePredmeta;

    @ManyToOne
    @JoinColumn(name = "id_io", referencedColumnName = "id_io", insertable = false, updatable = false)
    @JsonIgnore
    private IspitneObaveze ispitneObaveze;

    public PrijaveObaveza() {}

    public PrijaveObaveza(int idPrijaveObaveza, int idPracenjaPredmeta, int idIo) {
        this.idPrijaveObaveza = idPrijaveObaveza;
        this.idPracenjaPredmeta = idPracenjaPredmeta;
        this.idIo = idIo;
    }

    public int getIdPrijaveObaveza() {
        return idPrijaveObaveza;
    }

    public void setIdPrijaveObaveza(int idPrijaveObaveza) {
        this.idPrijaveObaveza = idPrijaveObaveza;
    }

    public int getIdPracenjaPredmeta() {
        return idPracenjaPredmeta;
    }

    public void setIdPracenjaPredmeta(int idPracenjaPredmeta) {
        this.idPracenjaPredmeta = idPracenjaPredmeta;
    }

    public int getIdIo() {
        return idIo;
    }

    public void setIdIo(int idIo) {
        this.idIo = idIo;
    }

    public PracenjePredmeta getPracenjePredmeta() {
        return pracenjePredmeta;
    }

    public void setPracenjePredmeta(PracenjePredmeta pracenjePredmeta) {
        this.pracenjePredmeta = pracenjePredmeta;
    }

    public IspitneObaveze getIspitneObaveze() {
        return ispitneObaveze;
    }

    public void setIspitneObaveze(IspitneObaveze ispitneObaveze) {
        this.ispitneObaveze = ispitneObaveze;
    }
}