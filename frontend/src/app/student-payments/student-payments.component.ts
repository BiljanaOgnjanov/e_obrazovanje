import { Component, OnInit } from '@angular/core';
import { Uplata } from '../modeli/uplata';
import { HttpClient } from '@angular/common/http';
import { DokumentaplacanjaService } from '../servisi/dokumentaplacanja.service';
import { Student } from '../modeli/student';

@Component({
  selector: 'app-student-payments',
  templateUrl: './student-payments.component.html',
  styleUrls: ['./student-payments.component.css']
})
export class StudentPaymentsComponent implements OnInit {

  ulogovan: Student =new Student();
  uplate:Uplata[] = [];

  constructor(private http: HttpClient, private dokumentiplacanjaservis: DokumentaplacanjaService){}

  ngOnInit(): void{
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);

      this.dokumentiplacanjaservis.uplateKorisnika(this.ulogovan.korisnicko_ime).subscribe(data =>{
        this.uplate = data;
      })
    }

  }

}
