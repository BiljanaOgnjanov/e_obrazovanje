import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-layout',
  templateUrl: './student-layout.component.html',
  styleUrls: ['./student-layout.component.css']
})
export class StudentLayoutComponent {
  constructor(private router: Router) {}

  onMenuItemSelected(menuItem: string) {
    if (menuItem === 'profile') {
      this.router.navigateByUrl('/student');
    } else {
      this.router.navigate(['/student', menuItem]);
    }
  }
}
