import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManifestationShowComponent } from './manifestation-show.component';

describe('ManifestationShowComponent', () => {
  let component: ManifestationShowComponent;
  let fixture: ComponentFixture<ManifestationShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManifestationShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManifestationShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
