import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nastavnik-layout',
  templateUrl: './nastavnik-layout.component.html',
  styleUrls: ['./nastavnik-layout.component.css']
})
export class NastavnikLayoutComponent {
  constructor(private router: Router) {}

  onMenuItemSelected(menuItem: string) {
    if (menuItem === 'profile') {
      this.router.navigateByUrl('/nastavnik');
    } else {
      this.router.navigate(['/nastavnik', menuItem]);
    }
  }
}
