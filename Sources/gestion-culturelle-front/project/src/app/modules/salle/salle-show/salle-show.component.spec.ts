import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalleShowComponent } from './salle-show.component';

describe('SalleShowComponent', () => {
  let component: SalleShowComponent;
  let fixture: ComponentFixture<SalleShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalleShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalleShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
