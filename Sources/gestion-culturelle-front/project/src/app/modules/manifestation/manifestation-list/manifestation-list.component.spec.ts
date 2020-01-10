import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManifestationListComponent } from './manifestation-list.component';

describe('ManifestationListComponent', () => {
  let component: ManifestationListComponent;
  let fixture: ComponentFixture<ManifestationListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManifestationListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManifestationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
