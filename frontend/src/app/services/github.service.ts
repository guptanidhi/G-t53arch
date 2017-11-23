import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http'; 

import 'rxjs/add/operator/map';

@Injectable()

export class GithubService {
    private username = 'TAM009' ;
    private client_id = '9dbdc8f265d8247fc11a';
    private client_secret = '2a17a875a8f577641e1d740276108eaddf5db800';
    private language = 'javascript';

    constructor(private _http:Http){
        console.log('github service');
    }

        getUser(){
            return this._http.get('https://api.github.com/users/'+this.username)
            .map(res => res.json());
        }

        getRepos(){
            return this._http.get('https://api.github.com/users/'+this.username+'/repos')
            .map(res => res.json());
        }

        updateusername(username:string){
            this.username = username;
        }

        
}