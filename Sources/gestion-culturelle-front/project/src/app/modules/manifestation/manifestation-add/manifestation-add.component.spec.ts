import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManifestationAddComponent } from './manifestation-add.component';

describe('ManifestationAddComponent', () => {
  let component: ManifestationAddComponent;
  let fixture: ComponentFixture<ManifestationAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManifestationAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManifestationAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
