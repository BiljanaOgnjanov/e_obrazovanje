import { Component, Output, EventEmitter, OnInit, Input} from '@angular/core';
import { Student } from '../modeli/student';

@Component({
  selector: 'app-meni',
  templateUrl: './meni.component.html',
  styleUrls: ['./meni.component.css']
})
export class MeniComponent implements OnInit {
  menuItems: any[] = [];
  ulogovan: Student = new Student();
  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
    }
    this.setMenuItems();
  }

  setMenuItems() {
    if (this.ulogovan.tip === 'student') {
      this.menuItems = [
        { label: 'Pocetna', route: '/student' },
        { label: 'Predmeti', route: '/student/predmeti-dashboard' },
        { label: 'Biranje predmeta', route: '/student/prijave-predmeta' },
        { label: 'Biranje ispita', route: '/student/prijave-predmeta' },
        { label: 'Placanja', route: '/student/placanja' },
        { label: 'Obavestenja', route: '/student/obavestenja' },
        { label: 'Dokumenti', route: '/student/dokumenti' }
      ];
    } else if (this.ulogovan.tip === 'nastavnik') {
      this.menuItems = [
        { label: 'Pocetna', route: '/nastavnik' },
        { label: 'Administracija predmeta', route: '/nastavnik/administracija-predmeta' },
        { label: 'Administracija saradnika', route: '/nastavnik/administracija-saradnika' },
        { label: 'Kreiranje ispita', route: '/nastavnik/novi-ispit' },
        { label: 'Ocenjivanje ispita', route: '/nastavnik/ocenjivanje' },
        { label: 'Obavestenja', route: '/nastavnik/obavestenja' }
      ];
    } else if (this.ulogovan.tip === 'administrator') {
      this.menuItems = [
        { label: 'Pocetna', route: '/administrator' },
        { label: 'Akcije', route: '/administrator/akcije' },
        { label: 'Predmeti', route: '/administrator/predmeti' },
        { label: 'Studenti', route: '/administrator/studenti' }
      ];
    }}

  @Output() menuItemSelected = new EventEmitter<string>();
}
