import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/map';

@Injectable()
export class RegisterService {

  constructor(private http: Http) { }

  private headers = new Headers({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  registerUser(model) {
    const json = JSON.stringify({ firstName: model.firstName, lastName: model.lastName, userId: model.registerUserName, password: model.registerPassword });
    return this.http.post('http://localhost:8080/userService/register/', json, { headers: this.headers }).map(res => res.json(),
      (err) => err.json());
  }

}