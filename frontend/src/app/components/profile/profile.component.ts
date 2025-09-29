import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { ProfessorService } from '../../services/professor.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.component.html',
})
export class ProfileComponent {
  private auth = inject(AuthService);
  private professorService = inject(ProfessorService);

  user = this.auth.currentUser();

  showModal = false;
  editing: any = { ...this.user };

  openModal() {
    this.editing = { ...this.user };
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  save() {
    if (this.user?.userType === 'PROFESSOR') {
      this.professorService.update(this.user.id, this.editing).subscribe({
        next: () => {
          alert('Profil uspešno ažuriran!');
          this.auth.updateUser(this.editing);
          this.closeModal();
          window.location.reload();
        },
        error: (err) => {
          console.error('Greška pri ažuriranju profila:', err);
          alert('Greška pri čuvanju podataka');
        },
      });
    }
  }
}
