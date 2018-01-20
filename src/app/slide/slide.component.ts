import {Component} from '@angular/core';

@Component({
  selector: 'app-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.css']
})
export class SlideComponent {

  openPopup = false;

  constructor() {
  }

  open() {
    this.openPopup = true;
  }

  close() {
    this.openPopup = false;
  }

}
