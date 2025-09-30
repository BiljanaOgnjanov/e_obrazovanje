import { Component, inject, signal } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  auth = inject(AuthService);
  router = inject(Router);

  dropdownOpen = signal(false);

  toggleDropdown() {
    this.dropdownOpen.set(!this.dropdownOpen());
  }

  logout() {
    this.dropdownOpen.set(false);
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
