import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManifestationUpdateComponent } from './manifestation-update.component';

describe('ManifestationUpdateComponent', () => {
  let component: ManifestationUpdateComponent;
  let fixture: ComponentFixture<ManifestationUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManifestationUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManifestationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
