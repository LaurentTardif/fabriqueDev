import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IdentityService} from '../service/identity.service';
import {CheckToolsService} from '../service/check-tools.service';

@Component({
  selector: 'app-tools',
  templateUrl: './tools.component.html',
  styleUrls: ['./tools.component.css']
})
export class ToolsComponent implements OnInit {

  @Input() toolToOpen:string;
  @Output() onClose: EventEmitter<void> = new EventEmitter();

  markdown : string;
  message : string;

  constructor(private _http : HttpClient,
              public _identityService: IdentityService,
              private _checkTool : CheckToolsService) {
  }

  ngOnInit() {
    this.message = '';
    this._http.get('assets/docs/' + this.toolToOpen + '.md',{ responseType: 'text'})
      .subscribe(res => this.markdown = res.replace(new RegExp("{{this.identityService.identity.ciDomain}}", 'g'), this._identityService.identity.ciDomain))
  }

  check() {
    this._checkTool.health(this.toolToOpen, this._identityService.identity).
      subscribe(
        isRunning => this.message = this.toolToOpen + (isRunning? ' works !': ' doesn\'t work !'),
        error => this.message = this.toolToOpen + ' doesn\'t work !');
  }

  close() {
    this.onClose.emit();
  }

}
