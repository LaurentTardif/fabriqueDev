import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-piste',
  templateUrl: './piste.component.html',
  styleUrls: ['./piste.component.css']
})
export class PisteComponent implements OnInit {

  @Output() onToolClick: EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  clickOn(event) {
    console.log(event);
    this.onToolClick.emit(event.target.id);
  }

}
