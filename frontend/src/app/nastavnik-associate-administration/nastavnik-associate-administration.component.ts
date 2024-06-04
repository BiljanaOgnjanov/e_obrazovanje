import { Component, OnInit } from '@angular/core';
import { Nastavnik } from '../modeli/nastavnik';
import { PredmetiService } from '../servisi/predmeti.service';
import { KorisnikService } from '../servisi/korisnik.service';

interface Course {
  id: number;
  name: string;
}


@Component({
  selector: 'app-nastavnik-associate-administration',
  templateUrl: './nastavnik-associate-administration.component.html',
  styleUrls: ['./nastavnik-associate-administration.component.css']
})
export class NastavnikAssociateAdministrationComponent {

  kursevi: Course[] = [];
  saradnici: Nastavnik[] = [];
  selectedCourseId: number | null = null;

  constructor(
    private courseService: PredmetiService,
    private associateService: KorisnikService
  ) {}


}
