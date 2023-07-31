import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';


export interface Response {
  status: number,
  message: string,
  token: string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  templateForm = {
    username: '',
    password: ''
  };

  constructor(private http: HttpClient, private router: Router, private tokenService: TokenService) {}

  login() {
    this.http.post<Response>('http://localhost:8080/login', this.templateForm)
      .subscribe(
        response => {
          // Handle the response from the backend API
          console.log('Login successful:', response);
          const token = response.token;
          console.log('Token:', token);
          localStorage.setItem('token', token);
          
          // const decodedToken = jwt_decode(token) as {
          //   sub: string;
          //   permissions: { authority: string }[];
          //   custom: { name: string };
          //   userId: number;
          // }; 
          this.tokenService.decodeToken(token);
          const decodedToken = this.tokenService.getToken();
          console.log('Decoded token:', decodedToken);
          console.log('Username:', this.tokenService.getUsername());
          console.log('Permissions:', this.tokenService.getPermissions());
          console.log('Display:', this.tokenService.getDisplayName());
          console.log('User ID:', this.tokenService.getUserId());
          

          if (this.tokenService.getPermissions().some((permission: { authority: string }) => permission.authority === 'ROLE_ADMIN')) {
            console.log('User is an admin');
            this.router.navigate(['/adminhome']);
          } else {
            console.log('User is not an admin');
            this.router.navigate(['/userhome']);
          }
        },
        error => {
          // Handle any errors that occurred during the request
          console.error('Login failed:', error);
        }
      );

    // Reset the form after login
    this.templateForm.username = '';
    this.templateForm.password = '';
  }
}
