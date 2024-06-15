import { Component, OnInit } from '@angular/core';
import { Nastavnik } from '../modeli/nastavnik';
import { NastavniksaradniciService } from '../servisi/nastavniksaradnici.service';
import { Predmet } from '../modeli/predmet';

@Component({
  selector: 'app-nastavnik-associate-administration',
  templateUrl: './nastavnik-associate-administration.component.html',
  styleUrls: ['./nastavnik-associate-administration.component.css']
})
export class NastavnikAssociateAdministrationComponent implements OnInit {
  ulogovan: Nastavnik = new Nastavnik();
  predmeti: Predmet[] = [];
  selectedPredmet: Predmet | null = null;
  professorsTeachingCourse: Nastavnik[] = [];
  professorsNotTeachingCourse: Nastavnik[] = [];
  showProfessorsNotOnCourse: boolean = false;
  isDisabled: boolean = false;

  constructor(private nastavniksaradniciService: NastavniksaradniciService) {}

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
      this.loadCourses();

      this.updateDisabledState();
    }
  }

  updateDisabledState() {
    if (this.ulogovan.uloga === "Demonstrator") {
      this.isDisabled = true;
    } else {
      this.isDisabled = false;
    }
  }

  loadCourses(): void {
    this.nastavniksaradniciService.getCoursesByProfessor(this.ulogovan.username)
      .subscribe(predmeti => this.predmeti = predmeti);
  }

  onCourseSelect(event: Event): void {
    const selectedId = (event.target as HTMLSelectElement).value;
    if (selectedId) {
      this.selectedPredmet = this.predmeti.find(p => p.idPredmeta === parseInt(selectedId, 10)) ?? null;
      if (this.selectedPredmet) {
        this.loadProfessorsTeachingCourse(this.selectedPredmet.idPredmeta);
        this.loadProfessorsNotTeachingCourse(this.selectedPredmet.idPredmeta);
      }
    }
  }

  loadProfessorsTeachingCourse(idPredmeta: number): void {
    this.nastavniksaradniciService.getProfessorsTeachingCourse(idPredmeta)
      .subscribe(professors => this.professorsTeachingCourse = professors);
  }

  loadProfessorsNotTeachingCourse(idPredmeta: number): void {
    this.nastavniksaradniciService.getProfessorsNotTeachingCourse(idPredmeta)
      .subscribe(professors => this.professorsNotTeachingCourse = professors);
  }

  toggleProfessorsNotOnCourse(): void {
    this.showProfessorsNotOnCourse = !this.showProfessorsNotOnCourse;
  }

  addProfessorToCourse(korisnickoIme: string): void {
    if (this.selectedPredmet) {
      this.nastavniksaradniciService.addProfessorToCourse(korisnickoIme, this.selectedPredmet.idPredmeta)
        .subscribe(() => {
          this.loadCourses();
          this.loadProfessorsTeachingCourse(this.selectedPredmet!.idPredmeta);
          this.loadProfessorsNotTeachingCourse(this.selectedPredmet!.idPredmeta);
        });
    }
  }

}
