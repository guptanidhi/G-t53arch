
import { Component } from '@angular/core';

import { NavbarComponent } from './component/navbar.component';

import { GithubComponent } from './component/github.component';

import { AdvanceComponent } from './component/advance.component';

import { SavedRepoComponent } from './component/savedrepo.component';

import { appRouterModule } from './app.route';
import{ HttpModule } from '@angular/http'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[HttpModule],
})
export class AppComponent {
  title = 'Github Search';
}