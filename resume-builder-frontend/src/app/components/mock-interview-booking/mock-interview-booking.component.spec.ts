import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockInterviewBookingComponent } from './mock-interview-booking.component';

describe('MockInterviewBookingComponent', () => {
  let component: MockInterviewBookingComponent;
  let fixture: ComponentFixture<MockInterviewBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MockInterviewBookingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MockInterviewBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
