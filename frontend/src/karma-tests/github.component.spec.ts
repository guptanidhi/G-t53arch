import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { GithubComponent } from '../app/component/github.component';
import * as $ from 'jquery';

describe('GithubComponent', () => {
  let component: GithubComponent;
  let fixture: ComponentFixture < GithubComponent > ;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        GithubComponent
      ],
      imports:[
        HttpModule,
        FormsModule
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GithubComponent);
    component = fixture.componentInstance;
  });

  it('should create the GithubComponent component', async(() => {
    expect(component).toBeTruthy();
  }));

  it(`should have 1 heading element in GithubComponent component`, async(() => {
    const headingElement = fixture.debugElement.queryAll(By.css('h1'));
    expect(headingElement.length).toBe(1);
  }));

  it(`should have 1 heading element with text "Search Git Repositories" in GithubComponent component`, async(() => {
    const headingElement = fixture.debugElement.query(By.css('h1')).nativeElement;
    expect(headingElement.innerHTML.trim()).toEqual('Search Git Repositories');
  }));

  it(`should have 1 input element to search gitRepositories in GithubComponent component`, async(() => {
    const inputElement = fixture.debugElement.queryAll(By.css('input'));
    expect(inputElement.length).toBe(1);
  }));

  it(`should have 1 button element in GithubComponent component`, async(() => {
    const buttonElement = fixture.debugElement.queryAll(By.css('button'));
    expect(buttonElement.length).toBe(1);
  }));

  it(`should have 1 button element with text "Search" in GithubComponent component`, async(() => {
    const buttonElement = fixture.debugElement.query(By.css('button')).nativeElement;
    expect(buttonElement.innerHTML.trim()).toBe("Search");
  }));
});
