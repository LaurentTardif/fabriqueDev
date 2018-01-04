import { OnChanges, SimpleChange } from "@angular/core";
export class Identity {
    ciIp = '';
    ciDomain = '';

    checkTools : boolean[] = [];
    
    scm = false;     
    ci = false;
    repo = false;
    ops = false;
    server = false;     
    quality = false;
    organization = false;
    communication = false;
}