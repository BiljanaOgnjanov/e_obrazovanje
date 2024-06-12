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

  admin_login(){
    if (this.username == "") {
      this.greska = "Nije uneto korisnicko ime";
    }
    else if (this.password == "") {
      this.greska = "Nije uneta lozinka";
    }
    else {
      this.korisnikServis.prijava(this.username, this.password).subscribe(data => {
          if (data == null || data.tip!="administrator") {
            alert(data.tip);
            this.greska = 'Takav administrator u bazi ne postoji';
          } else {
            alert(data.tip);
            localStorage.setItem('ulogovan', JSON.stringify(data));
            this.router.navigate(['/admin'])
          }
        });

  }
  }
}
