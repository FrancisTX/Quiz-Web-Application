import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {
  isLoggedIn: boolean | undefined;
  user: any; // Update the type based on the structure of the user object
  displayName: string = '';
  constructor(private router: Router, private tokenService: TokenService) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isLoggedIn = this.checkUserLoggedIn();
        console.log('Is user logged in?', this.isLoggedIn);
      }
    });
    //get the token from local storage
    const token = localStorage.getItem('token');
    if (token) {
      this.tokenService.decodeToken(token);
      this.user = this.tokenService.getUsername();
      this.displayName = this.tokenService.getDisplayName();
    }
  }

  checkUserLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    return token !== null;
  }
  
  isRouteActive(route: string): boolean {
    return this.router.url.startsWith(route);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
