import { Component, OnInit } from '@angular/core';
import { Student } from '../modeli/student';
import { PredmetiService } from '../servisi/predmeti.service';
import { mojiPredmetiDTO } from '../modeli/mojiPredmetiDTO';
import { ispitniRezultat } from '../modeli/ispitniRezultat';

@Component({
  selector: 'app-student-course-dashboard',
  templateUrl: './student-course-dashboard.component.html',
  styleUrls: ['./student-course-dashboard.component.css']
})
export class StudentCourseDashboardComponent implements OnInit {

  ulogovan: Student =new Student();
  svaPracenja:mojiPredmetiDTO[] =[];


  polozeniIspiti:mojiPredmetiDTO[] = [];
  nepolozeniIspiti:mojiPredmetiDTO[] = [];
  trenutnaPracenja:mojiPredmetiDTO[] = [];

  selectedTab: string = 'trenutna';
  mojiEspb:number = 0;
  brPolozenihIspita:number = 0;
  prosecnaOcena:number = 0;

  rezultati:ispitniRezultat[] =[];

  constructor(private predmetiservis: PredmetiService){}

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);

      this.predmetiservis.svaPracenjaPredmeta(this.ulogovan.username).subscribe(data =>{
        this.svaPracenja = data;
        this.razvrstavanjePracenja();
      })
    }
  }

  razvrstavanjePracenja():void{
  for (let i = 0; i < this.svaPracenja.length; i++) {
    const predmet = this.svaPracenja[i];
    if (predmet.ocena === null) {
        this.trenutnaPracenja.push(predmet);
      } else if (predmet.ocena === 5) {
        this.nepolozeniIspiti.push(predmet);
      } else if (predmet.polozenIspit === 1){
        this.polozeniIspiti.push(predmet);
        this.brPolozenihIspita++;
        this.prosecnaOcena+=predmet.ocena;
        this.mojiEspb+=predmet.brojKredita;
      }
    }
    this.prosecnaOcena/=this.polozeniIspiti.length;
  }

  selectTab(tab: string) {
    this.selectedTab = tab;
  }

  expandedDetailsIndex: number = -1;

  toggleDetails(id_pracenja_predmeta:number, index: number) {
    this.formatDetails(id_pracenja_predmeta);
    if (this.expandedDetailsIndex === index) {
      this.expandedDetailsIndex = -1;
    } else {
      this.expandedDetailsIndex = index;
    }
  }

  formatDetails(id_pracenja_predmeta:number) {
    this.predmetiservis.detaljiPracenjaPredmeta(id_pracenja_predmeta).subscribe((data: ispitniRezultat[])=>
    this.rezultati = data)
  }

}
