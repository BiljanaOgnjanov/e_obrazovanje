import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Uplata } from '../modeli/uplata';
import { HttpClient } from '@angular/common/http';
import { DokumentaplacanjaService } from '../servisi/dokumentaplacanja.service';
import { Placanje } from '../modeli/placanje';
import { Student } from '../modeli/student';

@Component({
  selector: 'app-student-payments',
  templateUrl: './student-payments.component.html',
  styleUrls: ['./student-payments.component.css']
})
export class StudentPaymentsComponent implements OnInit {

  ulogovan: Student =new Student();
  uplate:Uplata[] = [];
  placanja: Placanje[] = [];

  constructor(private http: HttpClient, private dokumentiplacanjaservis: DokumentaplacanjaService, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
      this.loadData();
  }
  loadData(): void{
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
      console.log('Loaded ulogovan from localstorage:', this.ulogovan);

      this.dokumentiplacanjaservis.uplateKorisnika(this.ulogovan.username).subscribe(data => {
        this.uplate = data;
      });

      this.dokumentiplacanjaservis.placanjaKorisnika(this.ulogovan.username).subscribe(data => {
        this.placanja = data;
      });
    }

  }

  refreshData(): void {
    // Force Angular to refresh the UI
    this.loadData();
  }

}
