import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizResultManagementComponent } from './quiz-result-management.component';

describe('QuizResultManagementComponent', () => {
  let component: QuizResultManagementComponent;
  let fixture: ComponentFixture<QuizResultManagementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizResultManagementComponent]
    });
    fixture = TestBed.createComponent(QuizResultManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
