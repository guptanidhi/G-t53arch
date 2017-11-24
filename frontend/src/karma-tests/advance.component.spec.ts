import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { AdvanceComponent } from '../app/component/advance.component';
import * as $ from 'jquery';

describe('AdvanceComponent', () => {
  let component: AdvanceComponent;
  let fixture: ComponentFixture < AdvanceComponent > ;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AdvanceComponent
      ],
      imports:[
        HttpModule,
        FormsModule
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvanceComponent);
    component = fixture.componentInstance;
  });

  it('should create the AdvanceComponent component', async(() => {
    expect(component).toBeTruthy();
  }));

  it(`should have 1 heading element in AdvanceComponent component`, async(() => {
    const headingElement = fixture.debugElement.queryAll(By.css('h1'));
    expect(headingElement.length).toBe(1);
  }));

  it(`should have 1 heading element with text "Most Starred Repositories in specific Language" in AdvanceComponent component`, async(() => {
    const headingElement = fixture.debugElement.query(By.css('h1')).nativeElement;
    expect(headingElement.innerHTML.trim()).toEqual('Most Starred Repositories in specific Language');
  }));

  it(`should have 1 input element to search gitRepositories in AdvanceComponent component`, async(() => {
    const inputElement = fixture.debugElement.queryAll(By.css('input'));
    expect(inputElement.length).toBe(1);
  }));

  it(`should have 1 button element in AdvanceComponent component`, async(() => {
    const buttonElement = fixture.debugElement.queryAll(By.css('button'));
    expect(buttonElement.length).toBe(1);
  }));

  it(`should have 1 button element with text "Search" in AdvanceComponent component`, async(() => {
    const buttonElement = fixture.debugElement.query(By.css('button')).nativeElement;
    expect(buttonElement.innerHTML.trim()).toBe("Search");
  }));
});
