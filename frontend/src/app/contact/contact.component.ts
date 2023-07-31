import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  constructor(private http: HttpClient) {}

  templateForm = {
    subject: '',
    message: '',
    email: ''
  };

  submitForm() {
    const url = 'http://localhost:8080/contacts';
    const body = {
      subject: this.templateForm.subject,
      message: this.templateForm.message,
      email: this.templateForm.email
    };

    this.http.post(url, body)
      .subscribe(
        () => {
          console.log('Form submitted successfully');
          // Reset the form after successful submission
          this.templateForm.subject = '';
          this.templateForm.message = '';
          this.templateForm.email = '';
        },
        error => {
          console.error('Form submission failed:', error);
        }
      );
  }
}
