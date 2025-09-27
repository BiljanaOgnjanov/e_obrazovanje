import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [CommonModule, FormsModule],
})
export class LoginComponent {
  private auth = inject(AuthService);
  private router = inject(Router);
  email = '';
  password = '';
  error = false;

  async onLogin() {
    const user = await this.auth.login(this.email, this.password);
    console.log(user);
    if (user) {
      const role = user.userType.toLowerCase();

      this.router.navigate([`/${role}`]);
    } else {
      this.error = true;
    }
  }
}
