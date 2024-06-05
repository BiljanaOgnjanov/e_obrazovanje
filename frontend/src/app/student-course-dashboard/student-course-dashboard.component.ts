import { Component, OnInit } from '@angular/core';
import { Student } from '../modeli/student';
import { PredmetiService } from '../servisi/predmeti.service';
import { mojiPredmetiDTO } from '../modeli/mojiPredmetiDTO';

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
    if (predmet.ocena === 4) {
        this.trenutnaPracenja.push(predmet);
      } else if (predmet.ocena === 5) {
        this.nepolozeniIspiti.push(predmet);
      } else if (predmet.ocena > 5){
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

  toggleDetails(index: number) {
    if (this.expandedDetailsIndex === index) {
      this.expandedDetailsIndex = -1;
    } else {
      this.expandedDetailsIndex = index;
    }
  }

  formatDetails(predispitneObaveze: string, rezultatiPredispitnihObaveza: string): string {
    const predispitnePairs = predispitneObaveze.split(':');
    const rezultatiPairs = rezultatiPredispitnihObaveza.split(':');

    let formattedDetails = '';

    for (let i = 0; i < predispitnePairs.length; i += 2) {
      const predispitneLabel = predispitnePairs[i];
      const predispitneValue = predispitnePairs[i + 1];
      const rezultatiValue = rezultatiPairs[i / 2];

      if (formattedDetails !== '') {
        formattedDetails += ', ';
      }

      formattedDetails += `${predispitneLabel}: ${rezultatiValue}/${predispitneValue}`;
    }

    return formattedDetails;
  }

}
