import { Component, computed, inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [RouterModule],
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent {
  private auth = inject(AuthService);
  user = this.auth.currentUser;

  menuItems = computed(() => {
    const u = this.user();
    if (!u) return [];

    switch (u.role) {
      case 'admin':
        return [
          { label: 'Dodavanje i Azuriranje Studenata', path: '' },
          { label: 'Dodavanje i Azuriranje Profesora', path: '' },
          { label: 'Dodavanje i Azuriranje Predmeta', path: '' },
          { label: 'Pregled i Azuriranje Svih Funkcija', path: '' },
        ];
      case 'student':
        return [
          { label: 'Licni Podaci', path: '' },
          { label: 'Dokumenti', path: '' },
          { label: 'Ispiti', path: '' },
          { label: 'Prijava i Odjava Ispita', path: '' },
          { label: 'Finansijska Kartica', path: '' },
          { label: 'Predmeti', path: '' },
        ];
      case 'profesor':
        return [
          { label: 'Licni Podaci', path: '' },
          { label: 'Predmeti', path: '' },
          { label: 'Zkazivanje Ispita', path: '' },
          { label: 'Zkazivanje Ispita', path: '' },
          { label: 'Ocena Ispita', path: '' },
        ];
      default:
        return [];
    }
  });
}
