import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UPLWebsite } from './uplwebsite';

describe('UPLWebsite', () => {
  let component: UPLWebsite;
  let fixture: ComponentFixture<UPLWebsite>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UPLWebsite]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UPLWebsite);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
