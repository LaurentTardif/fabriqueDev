import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { IdentityService } from '../service/identity.service';
import { CheckToolsService } from '../service/check-tools.service';

@Component({
  selector: 'app-piste',
  templateUrl: './piste.component.html',
  styleUrls: ['./piste.component.css']
})
export class PisteComponent implements OnInit {

  @Output() onToolClick: EventEmitter<string> = new EventEmitter();

  constructor(public _identityService: IdentityService) { }

  ngOnInit() {
  }

  clickOn(event) {
    console.log(event);
    this.onToolClick.emit(event.target.id);
  }

  isToolRunning(tool:string) : boolean {
    const health = this._identityService.identity.checkTools[tool];
    return health != undefined && health;
  }

}
