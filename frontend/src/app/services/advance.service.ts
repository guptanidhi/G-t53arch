import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions , Response} from '@angular/http'; 

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';


@Injectable()


export class AdvanceService{
    private language: 'javascript';
    private client_id = '9dbdc8f265d8247fc11a';
    private client_secret = '2a17a875a8f577641e1d740276108eaddf5db800';
    private data: any;

    constructor(private _http:Http){
      console.log('advance service');
    }

    getLanguageRepo(){
      return this._http.get('https://api.github.com/search/repositories?q=+language:'+this.language+'&sort=stars&order=desc')
      .map(res => res.json()['items']);
    }

    postLanguageRepo(data:any){  
      let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem('token')  });
      let options = new RequestOptions({ headers: headers });
      return this._http.post('http://localhost:8080/gitSearchService/addRepo',data,options)
      .map((res:Response) => res.json());        
    }

    getSavedRepo(){
      let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem('token')  });
      let options = new RequestOptions({ headers: headers });
      
      // return this._http.get('http://localhost:8080/gitSearch/getMyUsers', options).map((res: Response)=>res.json());
      const result = [
		    {
	        "id": "peter1@gmail.com",
	        "fullName": "peter",
	        "language": "java",
	        "forkCount": "10000",
	        "homePage": "www.googly.com",
	        "htmlUrl": "www.asd.com",
	        "watchersCount": "4000",
	        "description": "very good",
	        "avatarUrl": "https://avatars1.githubusercontent.com/u/17373378?v=4",
	        "userId": null
		    },
		    {
	        "id": "peter@gmail.com",
	        "fullName": "peter",
	        "language": "javascript",
	        "forkCount": "10",
	        "homePage": "www.googly.com",
	        "htmlUrl": "www.asd.com",
	        "watchersCount": "100",
	        "description": "Good",
	        "avatarUrl": "https://avatars1.githubusercontent.com/u/17373378?v=4",
	        "userId": null
		    }
			]
			return this._http.get('http://localhost:8080/gitSearchService/getMyRepos', options).toPromise().then((res: Response) => res.json(),
      (err) => err.json());
    }

    deleterepo(id:any){
      let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer '+localStorage.getItem('token') });
      let options = new RequestOptions({ headers: headers });
      const url = `http://localhost:8080/gitSearchService/deleteRepo/${id}`;
      console.log(url);
      return this._http.delete(url, options).toPromise().then(()=>null);        
    }   

    updatelanguage(language:any){
      this.language = language;
    }

}