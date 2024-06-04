import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DokumentaplacanjaService } from '../servisi/dokumentaplacanja.service';
import { Student } from '../modeli/student';
import { Dkmnt } from '../modeli/dkmnt';

@Component({
  selector: 'app-student-documents',
  templateUrl: './student-documents.component.html',
  styleUrls: ['./student-documents.component.css']
})
export class StudentDocumentsComponent implements OnInit {

  ulogovan: Student =new Student();
  dkmnti:Dkmnt[] = [];
  selectedFile: File| null = null;

  constructor(private http: HttpClient, private dokumentiplacanjaservis: DokumentaplacanjaService){}

  ngOnInit(): void{
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);

      this.dokumentiplacanjaservis.dokumentiKorisnika(this.ulogovan.korisnicko_ime).subscribe(data =>{
        this.dkmnti = data;
      })
    }
  }
  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onUpload(): void {
    if (this.selectedFile && this.ulogovan.korisnicko_ime) {
      const uploadData = new FormData();
      const documentName = prompt('Enter document name');
      if (documentName) {
        const fileName = `${this.ulogovan.korisnicko_ime}_${documentName}`;
        uploadData.append('file', this.selectedFile, fileName);

        this.dokumentiplacanjaservis.uploadDocument(uploadData).subscribe(response => {
          console.log('Upload response:', response);
        });
      }
    } else {
      alert('No file selected or user not logged in');
    }
  }

}
