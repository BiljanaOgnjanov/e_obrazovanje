import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { Korisnik } from '../modeli/korisnik';

@Component({
  selector: 'app-admin-meni',
  templateUrl: './admin-meni.component.html',
  styleUrls: ['./admin-meni.component.css']
})
export class AdminMeniComponent implements OnInit{
  ulogovan: Korisnik = new Korisnik();
  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
    }
  }
  @Output() menuItemSelected = new EventEmitter<string>();

  selectMenuItem(menuItem: string) {
    this.menuItemSelected.emit(menuItem);
  }
}
