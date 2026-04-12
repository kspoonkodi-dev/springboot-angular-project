import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TutorialMgmt } from './tutorial-mgmt';

describe('TutorialMgmt', () => {
  let component: TutorialMgmt;
  let fixture: ComponentFixture<TutorialMgmt>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TutorialMgmt]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TutorialMgmt);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
