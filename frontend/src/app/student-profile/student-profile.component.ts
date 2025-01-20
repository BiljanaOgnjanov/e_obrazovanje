import { Component, OnInit, ChangeDetectorRef  } from '@angular/core';
import { DokumentaplacanjaService } from '../servisi/dokumentaplacanja.service';
import { Student } from '../modeli/student';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit {

  ulogovan: Student = new Student();
  poruka:string = '';

  constructor(private dokumentiplacanjaservis: DokumentaplacanjaService, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
    }
  }
  refreshData(): void {
    // Force Angular to detect changes
    window.location.reload();
  }

  overiSemestar(): void {
    if (this.ulogovan.overenSemestar === 'overen') {
      this.poruka = 'Semestar je već overen.';
      return;
    }

    this.dokumentiplacanjaservis.overiSemestar(this.ulogovan.username).subscribe({
      next: (res) => {
        if (res === 'Uspešno ste overili semestar.') {
          this.poruka = res;

          // Update ulogovan object fields
          this.ulogovan.overenSemestar = 'overen';
          this.ulogovan.racun -= 2000;

          // Save the updated ulogovan object back to localStorage
          localStorage.setItem('ulogovan', JSON.stringify(this.ulogovan));
           // Trigger manual change detection
          this.refreshData();
          console.log('Updated ulogovan in localStorage:', JSON.parse(localStorage.getItem('ulogovan')!));
        } else {
          this.poruka = res; // Handle other success messages if any
        }
      },
      error: (err) => {
        this.poruka = err.error;
      }
    });
  }


}
