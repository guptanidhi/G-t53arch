import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {

  constructor(private http: Http) { }

  private headers = new Headers({ 'Content-Type': 'application/json', 'Accept': 'application/json' });

  login(model) {
    const json = JSON.stringify({ userId: model.userName, password: model.password });
    return this.http.post('http://localhost:8080/userService/login/', json, { headers: this.headers }).map(res => res.json(),
      (err) => err.json());
  }

}