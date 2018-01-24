import { Injectable } from '@angular/core';

import { Identity } from "./identity";

@Injectable()
export class IdentityService {

  public identity: Identity;

  constructor() {
    this.load();
  }

  save() {
    localStorage.setItem('fabriquetonusine', JSON.stringify(this.identity));
  }

  clean() {
    this.identity.ci = false;
    this.identity.communication = false;
    this.identity.ops = false;
    this.identity.server = false;
    this.identity.scm = false;
    this.identity.repo = false;
    this.identity.organization = false;
    this.identity.quality = false;
    this.identity.checkTools = [];
    this.save();
  }

  load() {
    this.identity = JSON.parse(localStorage.getItem('fabriquetonusine'));
    if (!this.identity) {
      this.identity = new Identity();
      this.save();
    }
  }

  isFilledUp(): boolean {
    return this.identity.ci && this.identity.communication
      && this.identity.ops && this.identity.server && this.identity.scm
      && this.identity.repo && this.identity.organization
      && this.identity.quality;
  }

}
