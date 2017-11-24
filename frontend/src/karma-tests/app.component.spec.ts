import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AppComponent } from '../app/app.component';
import * as $ from 'jquery';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture < AppComponent > ;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
  });

  it('should create the app component', async(() => {
    expect(component).toBeTruthy();
  }));

  it(`should have as title 'Github Search'`, async(() => {
    expect(component.title).toEqual('Github Search');
  }));
});
