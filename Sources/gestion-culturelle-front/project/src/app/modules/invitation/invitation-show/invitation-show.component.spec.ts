import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvitationShowComponent } from './invitation-show.component';

describe('InvitationShowComponent', () => {
  let component: InvitationShowComponent;
  let fixture: ComponentFixture<InvitationShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvitationShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvitationShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
