import { Component, OnInit } from '@angular/core';
import { Nastavnik } from '../modeli/nastavnik';
import { KorisnikService } from '../servisi/korisnik.service';

@Component({
  selector: 'app-nastavnik-profile',
  templateUrl: './nastavnik-profile.component.html',
  styleUrls: ['./nastavnik-profile.component.css']
})
export class NastavnikProfileComponent implements OnInit {
  ulogovan: Nastavnik = new Nastavnik();

  constructor(private korisnikService: KorisnikService){}

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
    }
  }

}
