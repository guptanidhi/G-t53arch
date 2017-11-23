import { Component } from '@angular/core';
import { AdvanceService } from '../services/advance.service';

export class Save{
    id:any;
    full_name:any;
    language:any;
    forks: any;
    homepage:any;
    html_url:any;
    watchers_count:any;
    description:any;
}

@Component({
    selector: 'my-advance',
    templateUrl: './advance.component.html',
    styleUrls: ['./advance.component.css'],
    providers: [ AdvanceService ],
})

export class AdvanceComponent{
  stared: any = [];
  languagename: any;
  saved: any;
  json: any;
  postdata:any;
  isUserLoggedIn: boolean = false;

  constructor(private _advanceservice: AdvanceService){
    console.log("loading....");
  }

  ngDoCheck(){
		this.isUserLoggedIn = localStorage.getItem('token') ? true: false;
	}

  onClick(){    
    this._advanceservice.updatelanguage(this.languagename);	    
    this._advanceservice.getLanguageRepo().subscribe(stared => {
      console.log(stared);
      this.stared = stared;        
    })
  }

  onSave(star:any){
    this.json=JSON.stringify({
      id: star.id,
      fullName: star.full_name,
      language: star.language,
      forkCount: star.forks_count,
      homepage: star.homepage,
      htmlUrl:star.html_url,
      watchersCount: star.watchers_count,
      description: star.description,
      avatarUrl: star.owner.avatar_url,
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