import { Component } from '@angular/core';

@Component({
  selector: 'app-nastavnik-ispit',
  templateUrl: './nastavnik-ispit.component.html',
})
export class NastavnikIspitComponent {
  nastavnik = {
    id: 1,
    ime: 'Dr. John Doe',
    korisnickoIme: 'johndoe',
    predmet: { id: 1, naziv: 'Matematika', kod: 'MATH101' }
  };

  ispit = {
    datum: '',
    vreme: '',
    cena: 0,
  };

  zakazaniIspiti = [
    { predmet: 'Matematika', datum: '2025-10-12', vreme: '09:00', cena: 1200 },
    { predmet: 'Matematika', datum: '2025-10-15', vreme: '11:00', cena: 1200 }
  ];

  dodajIspit() {
    if (this.ispit.datum && this.ispit.vreme) {
      this.zakazaniIspiti.push({
        predmet: this.nastavnik.predmet.naziv,
        datum: this.ispit.datum,
        vreme: this.ispit.vreme,
        cena: this.ispit.cena
      });
      this.resetujFormu();
    } else {
      alert('Molimo Vas, popunite sva polja!');
    }
  }

  obavestiStudente(indeksIspita: number): void {
   // nastavnik je ocenio sve studente i zeli da se studenti imaju informaciju o ocenama za taj predmet na stranici za obavestenja
  }


  resetujFormu() {
    this.ispit = {
      datum: '',
      vreme: '',
      cena: 0
    };
  }

  get ukupnaCenaIspita() {
    return this.zakazaniIspiti.reduce((acc, ispit) => acc + ispit.cena, 0);
  }
}
