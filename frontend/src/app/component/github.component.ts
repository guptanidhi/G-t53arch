import { Component } from '@angular/core';

import { GithubService } from '../services/github.service';
import { AdvanceService } from '../services/advance.service';

@Component({
  selector: 'my-github',
  templateUrl: './github.component.html',
  styleUrls: ['./github.component.css'],
  providers: [ GithubService, AdvanceService ],
})
export class GithubComponent {
  user:any;
  repos:any;
  username: string;
  json: any;
  postdata:any;
  isUserLoggedIn: boolean = false;

  constructor(private _githubservice: GithubService, private _advanceservice: AdvanceService){
    console.log("loading....");
  }

  ngDoCheck(){
    this.isUserLoggedIn = localStorage.getItem('token') ? true: false;
  }

  search(){
    this._githubservice.updateusername(this.username);
    if(typeof this.username === 'undefined'){
      return;
    }
    this._githubservice.getUser().subscribe(user => {
      this.user = user; 
    });

    this._githubservice.getRepos().subscribe(repos => {
      this.repos = repos; 
    });
  }
  
  onSave(repos:any){
    this.json=JSON.stringify({
      id: repos.id,
      fullName: repos.full_name,
      language: repos.language,
      forkCount: repos.forks_count,
      homepage: repos.homepage,
      htmlUrl:repos.html_url,
      watchersCount: repos.watchers_count,
      description: repos.description,
      avatarUrl: repos.owner.avatar_url,
      userId: localStorage.getItem('userId')
    });     
    console.log(this.json);   
    return this._advanceservice.postLanguageRepo(this.json).subscribe(
      res => this.postdata=res,
      error => alert(error._body),
      ()=> alert("Repository saved successfully.")
    );
  }
}