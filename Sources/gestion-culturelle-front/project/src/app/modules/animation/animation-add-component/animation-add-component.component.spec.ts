import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimationAddComponentComponent } from './animation-add-component.component';

describe('AnimationAddComponentComponent', () => {
  let component: AnimationAddComponentComponent;
  let fixture: ComponentFixture<AnimationAddComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnimationAddComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimationAddComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
