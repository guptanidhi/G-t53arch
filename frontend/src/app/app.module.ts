import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule} from '@angular/http';
import { FormsModule } from '@angular/forms';
import * as $ from 'jquery';

import { appRouterModule} from './app.route';


import { AppComponent } from './app.component';


import { GithubComponent } from './component/github.component';

import { NavbarComponent } from './component/navbar.component';

import { AdvanceComponent } from './component/advance.component';

import { GithubService } from './services/github.service';

import { SavedRepoComponent } from './component/savedrepo.component'



@NgModule({
  declarations: [
    AppComponent,
    GithubComponent,
    AdvanceComponent,
    NavbarComponent,
    SavedRepoComponent,
  ],
  imports: [
    BrowserModule,
    appRouterModule,
    HttpModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
