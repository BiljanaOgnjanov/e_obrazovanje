import { Component, Output, EventEmitter, OnInit} from '@angular/core';
import { Nastavnik } from '../modeli/nastavnik';

@Component({
  selector: 'app-nastavnik-meni',
  templateUrl: './nastavnik-meni.component.html',
  styleUrls: ['./nastavnik-meni.component.css']
})
export class NastavnikMeniComponent implements OnInit{
  ulogovan: Nastavnik = new Nastavnik();
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
