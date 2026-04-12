import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseMgmt } from './course-mgmt';

describe('CourseMgmt', () => {
  let component: CourseMgmt;
  let fixture: ComponentFixture<CourseMgmt>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseMgmt]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseMgmt);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
