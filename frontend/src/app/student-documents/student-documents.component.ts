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
  documentName: string = '';
  documentType: string = 'ostalo';

  constructor(private http: HttpClient, private dokumentiplacanjaservis: DokumentaplacanjaService){}

  ngOnInit(): void{
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);

      this.dokumentiplacanjaservis.dokumentiKorisnika(this.ulogovan.username).subscribe(data =>{
        this.dkmnti = data;
      })
    }
  }
  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadDocument(): void {
    if (this.selectedFile && this.ulogovan.username && this.documentName) {
      const fileName = `${this.ulogovan.username}_${this.documentName}`;
      const uploadData = new FormData();
      uploadData.append('file', this.selectedFile, fileName);
      uploadData.append('korisnickoIme', this.ulogovan.username);
      uploadData.append('nazivDokumenta', this.documentName);
      uploadData.append('tipDokumenta', this.documentType);

      this.dokumentiplacanjaservis.postaviDokument(uploadData).subscribe(
        response => {
          console.log('Upload response:', response);
          // Handle success response
        },
        error => {
          console.error('Error uploading document:', error);
          // Handle error response
        }
      );
    } else {
      alert('No file selected, document name missing, or user not logged in');
    }
  }

  deleteDocument(idDokumenta: number): void {
    if (confirm('Da li ste sigurni da zelite da obisete ovaj dokument?')) {
      this.dokumentiplacanjaservis.obrisiDokument(idDokumenta).subscribe(
        response => {
          console.log('Odgovor servera:', response);
          this.dkmnti = this.dkmnti.filter(d => d.idDokumenta !== idDokumenta);
        },
        error => {
          console.error('Greska:', error);
        }
      );
    }

  }

}
