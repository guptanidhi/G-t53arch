
import {RouterModule, Routes } from '@angular/router';

import { NgModule } from '@angular/core';

import { GithubComponent } from './component/github.component';
import { AdvanceComponent } from './component/advance.component';
import { SavedRepoComponent } from './component/savedrepo.component';

const routes: Routes =[
    {path:'',component:GithubComponent},
    {path:'advance',component:AdvanceComponent},
    {path: 'saved', component:SavedRepoComponent}

];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})


export class appRouterModule{}