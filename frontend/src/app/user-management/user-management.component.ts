import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent {
  users: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchUsers();
  }

  fetchUsers() {
    this.http.get<any>('http://localhost:8080/users').subscribe(
      response => {
        this.users = response.users;
      },
      error => {
        console.error('Failed to fetch users:', error);
      }
    );
  }

  suspendUser(userId: number) {
    // Make an HTTP POST request to suspend the user with the specified userId
    this.http.patch<any>(`http://localhost:8080/user/${userId}/status?activate=false`, {}).subscribe(
      response => {
        console.log('User suspended successfully');
        // Refresh the user list
        this.fetchUsers();
      },
      error => {
        console.error('Failed to suspend user:', error);
      }
    );
  }

  activateUser(userId: number) {
    // Make an HTTP POST request to activate the user with the specified userId
    this.http.patch<any>(`http://localhost:8080/user/${userId}/status?activate=true`, {}).subscribe(
      response => {
        console.log('User activated successfully');
        // Refresh the user list
        this.fetchUsers();
      },
      error => {
        console.error('Failed to activate user:', error);
      }
    );
  }
}
