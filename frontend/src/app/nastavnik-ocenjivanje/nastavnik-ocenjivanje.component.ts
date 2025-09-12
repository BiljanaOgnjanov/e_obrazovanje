import { Component, OnInit } from '@angular/core';

// Interface for the ucenici
interface Ucenici {
  id: number;
  ime: string;
  prezime: string;
  username: string;
  ocena?: number | null;
  komentar?: string | null;
}

@Component({
  selector: 'app-nastavnik-ocenjivanje',
  templateUrl: './nastavnik-ocenjivanje.component.html',
  styleUrls: ['./nastavnik-ocenjivanje.component.css']
})
export class NastavnikOcenjivanjeComponent implements OnInit {
  ucenici: Ucenici[] = [
    { id: 1, ime: 'Ana', prezime: 'Jovanović', username: 'ana.j', ocena: null, komentar: null },
    { id: 2, ime: 'Marko', prezime: 'Petrović', username: 'marko.p', ocena: null, komentar: null },
    { id: 3, ime: 'Jovana', prezime: 'Nikolić', username: 'jovana.n', ocena: null, komentar: null }
  ];

  komentarMessage: string = '';
  komentarType: string = 'success';

  constructor() { }

  ngOnInit(): void { }

  posaljiOcenu(ucenici: Ucenici): void {
    if (ucenici.ocena === null || ucenici.ocena === undefined || ucenici.komentar === '') {
      this.komentarMessage = `Molimo vas unesite ocenu i komentar za ucenicia ${ucenici.ime} ${ucenici.prezime}.`;
      this.komentarType = 'danger';
      return;
    }

    this.komentarMessage = `Ocena za studenta ${ucenici.ime} ${ucenici.prezime} je uspešno postavljena.`;
    this.komentarType = 'success';

    setTimeout(() => {
      this.komentarMessage = '';
    }, 5000);
  }
}
