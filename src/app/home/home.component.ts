import { Component, OnInit } from '@angular/core';
import { IdentityService } from "../service/identity.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  openPopup = false;

  constructor(public _identityService: IdentityService) {
  }

  open() {
    this.openPopup = true;
  }

  close() {
    this.openPopup = false;
  }

  save() {
    this._identityService.save();
    this.openPopup = false;
  }

  ngOnInit() {
  }

}
