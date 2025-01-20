import { Component } from '@angular/core';
import { AdminostaloService } from '../servisi/adminostalo.service';

@Component({
  selector: 'app-admin-akcije',
  templateUrl: './admin-akcije.component.html',
  styleUrls: ['./admin-akcije.component.css']
})
  export class AdminAkcijeComponent {

    constructor(private adminOstaloService:AdminostaloService) {}

  resetOverenSemestar(): void {
    this.adminOstaloService.resetOverenSemestar().subscribe({
      next: (response) => {
        alert(response); // Show success message from backend
      },
      error: (error) => {
        alert('Error: Could not reset "overenSemestar" for all students.');
      }
    });
  }

}
