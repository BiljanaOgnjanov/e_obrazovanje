import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from '../servisi/korisnik.service';
import { Student } from '../modeli/student';
//import { KorisniciService } from '../servisi/korisnici.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private korisnikServis: KorisnikService, private router: Router){}
  username:string='';
  password:string='';
  greska:string='';

  tip:string = 'student';

  ngOnInit(): void {
    localStorage.clear()
  }

  login(){
    if (this.username == "") {
      this.greska = "Nije uneto korisnicko ime";
    }
    else if (this.password == "") {
      this.greska = "Nije uneta lozinka";
    }
    else {
      this.korisnikServis.prijava(this.username, this.password).subscribe(data => {
          if (data == null) {
            this.greska = 'Takav korisnik u bazi ne postoji';
          } else {
            localStorage.setItem('ulogovan', JSON.stringify(data));

            const dozvoljeniTipovi = ['nastavnik', 'student', 'administrator']
            if (dozvoljeniTipovi.includes(data.tip)) {
              this.router.navigate([`/${data.tip}`]);
            } else {
              this.greska = 'Nepoznat tip korisnika';
            }
          }
        });
  }
  }
}
