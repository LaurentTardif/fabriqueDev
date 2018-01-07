import { Component, OnInit } from '@angular/core';
import { IdentityService } from '../service/identity.service';
import { Identity } from '../service/identity';

@Component({
  selector: 'app-cycle',
  templateUrl: './cycle.component.html',
  styleUrls: ['./cycle.component.css']
})
export class CycleComponent implements OnInit {

  public identity: Identity;
  constructor(public _identityService: IdentityService) {
    this.identity = _identityService.identity;
  }

  ngOnInit() {
  }

}
