import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Identity } from "./identity";
import { Tools } from "./tools"

import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class CheckToolsService {

  constructor(private _http : HttpClient) {
  }


  health(tools: string, identity : Identity): Observable<Boolean> {
    return this._http.get(`http://${tools}.${identity.ciDomain}/login`.toString(),  {responseType: 'text'})
      .map(res => this.extractData(res, tools, identity))
      .catch(res => this.handleError(res, tools, identity));
  }

  private extractData(res: Response | any, tools: string,identity : Identity) {
    identity.checkTools[tools] = true;
    console.log('ok');
    return true;
  }

  private handleError(error: Response | any, tools: string,identity : Identity) {
    identity.checkTools[tools] = false;
    console.log('fail : '+JSON.stringify(error));
    return Observable.throw(false);
  }
}
