import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { By } from '@angular/platform-browser';
import { SavedRepoComponent } from '../app/component/savedrepo.component';
import * as $ from 'jquery';

describe('SavedRepoComponent', () => {
  let component: SavedRepoComponent;
  let fixture: ComponentFixture < SavedRepoComponent > ;
  let headingElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SavedRepoComponent
      ],
      imports:[
        HttpModule
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SavedRepoComponent);
    component = fixture.componentInstance;
    headingElement = fixture.debugElement.queryAll(By.css('h1'));
  });

  it('should create the SavedRepoComponent component', async(() => {
    expect(component).toBeTruthy();
  }));

  it(`should have 1 heading element`, async(() => {
    expect(headingElement.length).toBe(1);
  }));

  it(`should have 1 heading element with text "Saved Repositories"`, async(() => {
    expect(headingElement[0].nativeElement.innerHTML.trim()).toEqual('Saved Repositories');
  }));

});
