import { Component, Output, EventEmitter, OnInit} from '@angular/core';
import { Student } from '../modeli/student';

@Component({
  selector: 'app-meni',
  templateUrl: './meni.component.html',
  styleUrls: ['./meni.component.css']
})
export class MeniComponent implements OnInit {
  ulogovan: Student = new Student();
  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
    }
  }
  @Output() menuItemSelected = new EventEmitter<string>();

  selectMenuItem(menuItem: string) {
    this.menuItemSelected.emit(menuItem);
  }
}
