import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';

import { GithubService } from './services/github.service'

import { Http, Headers } from '@angular/http'; 

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        GithubService
      ],
    }).compileComponents();
  }));

  it('should do something', () => {
    let service = new GithubService();
  
    expect(service.foo).toEqual('bar');
  });
});
