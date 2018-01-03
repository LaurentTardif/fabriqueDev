import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-tools',
  templateUrl: './tools.component.html',
  styleUrls: ['./tools.component.css']
})
export class ToolsComponent implements OnInit {

  @Input() toolToOpen:string;
  @Output() onClose: EventEmitter<void> = new EventEmitter();

  markdown : string;

  constructor(private _http : HttpClient) { }

  ngOnInit() {
    console.log(this.toolToOpen);
    this._http.get('assets/docs/' + this.toolToOpen + '.md',{ responseType: 'text'})
      .subscribe(res => this.markdown = res)
  }

  close() {
    this.onClose.emit();
  }

}
