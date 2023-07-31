import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactManagementComponent } from './contact-management.component';

describe('ContactManagementComponent', () => {
  let component: ContactManagementComponent;
  let fixture: ComponentFixture<ContactManagementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactManagementComponent]
    });
    fixture = TestBed.createComponent(ContactManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
