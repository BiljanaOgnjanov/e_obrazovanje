import { Component, computed, inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent {
  private auth = inject(AuthService);
  user = this.auth.currentUser();

  menuItems = computed(() => {
    if (!this.user) return [];

    switch (this.user.userType) {
      case 'ADMIN':
        return [
          { label: 'Dodavanje i Azuriranje Koristnika', path: 'admin/upravljanje-korisnicima' },
          { label: 'Prikaz i brisanje predmeta', path: 'admin/upravljanje-predmetima' },
          { label: 'Azuriranje Ispita', path: 'admin/azuriranje-ispita' },
          { label: 'Istoraija Transakcija', path: 'admin/istorija-transakcija' },
        ];
      case 'STUDENT':
        return [
          { label: 'Licni Podaci', path: 'student/pocetna' },
          { label: 'Dokumenti', path: 'student/dokumenti' },
          { label: 'Ispiti', path: 'student/ispiti' },
          { label: 'Prijava i Odjava Ispita', path: 'student/prijava-ispita' },
          { label: 'Finansijska Kartica', path: 'student/finansijska-kartica' },
          { label: 'Predmeti', path: 'student/predmeti' },
        ];
      case 'PROFESSOR':
        return [
          { label: 'Licni Podaci', path: 'professor/pocetna' },
          { label: 'Predmeti', path: 'professor/predmeti' },
          { label: 'Zakazivanje Ispita', path: 'professor/zakazivanje-ispita' },
          { label: 'Ocena Ispita', path: 'professor/ocena' },
        ];
      default:
        return [];
    }
  });
}
