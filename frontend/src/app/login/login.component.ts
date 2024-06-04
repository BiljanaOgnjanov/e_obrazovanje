import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from '../servisi/korisnik.service';
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
            if(this.username == 'sanja'){
              this.tip='nastavnik';
            }
            if (this.tip == 'student') {
              this.router.navigate(['/student']);
            } else if (this.tip == 'nastavnik') {
              this.router.navigate(['/nastavnik']);
            } else {
              this.greska = 'Nepoznat tip korisnika';
            }
          }
        });

  }
  }
}
