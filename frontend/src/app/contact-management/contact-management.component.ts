import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contact-management',
  templateUrl: './contact-management.component.html',
  styleUrls: ['./contact-management.component.css']
})
export class ContactManagementComponent {
  contacts: any[] = [];
  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchContacts();
  }

  fetchContacts() {
    this.http.get<any>('http://localhost:8080/contacts').subscribe(
      response => {
        this.contacts = response.contacts;
      },
      error => {
        console.error('Failed to fetch contacts:', error);
      }
    );
  }
}
