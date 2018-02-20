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
    return this._http.get(`${this.getProtocol(tools)}://${tools}.${identity.ciDomain}${this.getExtraUrlPart(tools)}`.toString(),  {responseType: 'text'})
      .map(res => this.extractData(res, tools, identity))
      .catch(res => this.handleError(res, tools, identity));
  }

  private extractData(res: Response | any, tools: string,identity : Identity) {
    identity.checkTools[tools] = true;
    this.toolsIsEnabled(tools,identity );
    console.log('ok : '+JSON.stringify(identity));
    return true;
  }

  private handleError(error: Response | any, tools: string,identity : Identity) {
    identity.checkTools[tools] = false;
    console.log('fail : '+JSON.stringify(error));
    return Observable.throw(false);
  }

  private getProtocol(tools: string){
    if(tools == 'zulip'){
      return 'https';
    }
    return 'http';
  }

  private getExtraUrlPart(tools: string){
    if(tools == 'jenkins'){
      return '/login';
    }
    if(tools == 'zulip'){
      return ':7443/login/';
    }
    return '/';
  }

  private toolsIsEnabled(tools: string,identity : Identity) {
    switch (tools) {
      case 'gitlab':
        identity.scm = true;
        break;
      case 'svn':
        identity.scm = true;
        break;
      case 'mercurial':
        identity.scm = true;
        break;
      case 'bitbucket':
        identity.scm = true;
        break;
      case 'jenkins':
        identity.ci = true;
        break;
      case 'bamboo':
        identity.ci = true;
        break;
      case 'teamcity':
        identity.ci = true;
        break;
      case 'nexus':
        identity.repo = true;
        break;
      case 'artifactory':
        identity.repo = true;
        break;
      case 'verdaccio':
        identity.repo = true;
        break;
      case 'dockerhub':
        identity.repo = true;
        break;
      case 'rundeck':
        identity.ops = true;
        break;
      case 'gocd':
        identity.ops = true;
        break;
      case 'nginx':
        identity.server = true;
        break;
      case 'apache':
        identity.server = true;
        break;
      case 'tomcat':
        identity.server = true;
        break;
      case 'jetty':
        identity.server = true;
        break;
      case 'websphere':
        identity.server = true;
        break;
      case 'sonar':
        identity.quality = true;
        break;
      case 'teamscale':
        identity.quality = true;
        break;
      case 'mattermost':
        identity.communication = true;
        break;
      case 'zulip':
        identity.communication = true;
        break;
      case 'rocketchat':
        identity.communication = true;
        break;
      case 'wekan':
        identity.organization = true;
        break;
      case 'jira':
        identity.organization = true;
        break;
      case 'redmine':
        identity.organization = true;
        break;
      case 'mantis':
        identity.organization = true;
        break;
    }
  }
}
