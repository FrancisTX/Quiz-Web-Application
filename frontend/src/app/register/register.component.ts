import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private http: HttpClient) {}

  ngOnInit() {
  }

  templateForm = {
    email: '',
    password: '',
    firstname: '',
    lastname: ''
  };

  register() {
    // Perform register logic here, such as sending a request to the server
    const userData = {
      lastName: this.templateForm.lastname,
      firstName: this.templateForm.firstname,
      email: this.templateForm.email,
      password: this.templateForm.password
    };
    console.log(userData);
    this.http.post('http://localhost:8080/users', userData)
    .subscribe(
      response => {
        // Handle the response from the server
        console.log('Registration successful:', response);

        // Reset the form after registration
        this.templateForm.email = '';
        this.templateForm.password = '';
        this.templateForm.firstname = '';
        this.templateForm.lastname = '';
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('Registration failed:', error);
      }
    );

    // Reset the form after registration
    this.templateForm.email = '';
    this.templateForm.password = '';
    this.templateForm.firstname = '';
    this.templateForm.lastname = '';
  }
}

