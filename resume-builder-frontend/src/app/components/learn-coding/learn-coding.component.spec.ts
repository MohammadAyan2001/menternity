import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LearnCodingComponent } from './learn-coding.component';

describe('LearnCodingComponent', () => {
  let component: LearnCodingComponent;
  let fixture: ComponentFixture<LearnCodingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LearnCodingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LearnCodingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
