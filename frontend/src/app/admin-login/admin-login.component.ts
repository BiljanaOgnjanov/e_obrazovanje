import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { KorisnikService } from '../servisi/korisnik.service';


@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
  constructor(private korisnikServis: KorisnikService, private router: Router){}
  username:string='';
  password:string='';
  greska:string='';

  tip:string = 'administrator';

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
            this.greska = 'Takav administrator u bazi ne postoji';
          } else {
            localStorage.setItem('ulogovan', JSON.stringify(data));
            if(data.tip != 'administrator'){
              this.greska = 'Takav administrator u bazi ne postoji';
            }
            this.router.navigate(['/admin'])
          }
        });

  }
  }
}
