import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent {
  constructor(private router: Router) {}

  onMenuItemSelected(menuItem: string) {
    if (menuItem === 'profile') {
      this.router.navigateByUrl('/admin');
    } else {
      this.router.navigate(['/admin', menuItem]);
    }
  }
}
