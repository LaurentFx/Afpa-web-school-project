import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalleUpdateComponent } from './salle-update.component';

describe('SalleUpdateComponent', () => {
  let component: SalleUpdateComponent;
  let fixture: ComponentFixture<SalleUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalleUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalleUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
