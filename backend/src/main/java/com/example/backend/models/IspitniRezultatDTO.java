package com.example.backend.models;

public class IspitniRezultatDTO {
    private String nazivIspitneObaveze;
    private int brojPoena;
    private int osvojenoPoena;
    private String rok;
    private int polozenIspitURoku;

    public IspitniRezultatDTO(){}
    public IspitniRezultatDTO(String nazivIspitneObaveze, int brojPoena, int osvojenoPoena, String rok, int polozenIspitURoku) {
        this.nazivIspitneObaveze = nazivIspitneObaveze;
        this.brojPoena = brojPoena;
        this.osvojenoPoena = osvojenoPoena;
        this.rok = rok;
        this.polozenIspitURoku = polozenIspitURoku;
    }

    public String getNazivIspitneObaveze() {
		return nazivIspitneObaveze;
	}
	public void setNazivIspitneObaveze(String nazivIspitneObaveze) {
		this.nazivIspitneObaveze = nazivIspitneObaveze;
	}
	public int getBrojPoena() {
		return brojPoena;
	}
	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}
	public int getOsvojenoPoena() {
		return osvojenoPoena;
	}
	public void setOsvojenoPoena(int osvojenoPoena) {
		this.osvojenoPoena = osvojenoPoena;
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
