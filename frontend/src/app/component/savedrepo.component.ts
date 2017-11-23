import {Component, OnInit} from '@angular/core';
import { AdvanceService } from '../services/advance.service';


@Component({
    selector: 'my-saved',
    templateUrl: './savedrepo.component.html',
    styleUrls:['./savedrepo.component.css'],
    providers:[AdvanceService]

})

export class SavedRepoComponent implements OnInit{
  data:any;
  saved:any[];

  getList(){
    this._savedrepo.getSavedRepo().then((saved) => {
      if(saved)
      	this.saved = saved;
    });
  }
  ngOnInit(){
    this.getList();
  }  

  constructor(private _savedrepo: AdvanceService){
  }

  delete(id:any): void{
    this._savedrepo.deleterepo(id).then(() => this.getList());
  }
}