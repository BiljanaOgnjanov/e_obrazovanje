import { Component, OnInit } from '@angular/core';
import { Nastavnik } from '../modeli/nastavnik';

@Component({
  selector: 'app-nastavnik-profile',
  templateUrl: './nastavnik-profile.component.html',
  styleUrls: ['./nastavnik-profile.component.css']
})
export class NastavnikProfileComponent implements OnInit {
  ulogovan: Nastavnik = new Nastavnik();

  constructor(){}

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
    }
  }
}
