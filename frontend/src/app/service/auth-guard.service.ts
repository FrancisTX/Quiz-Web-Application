import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { TokenService } from './token.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private router: Router, private tokenService: TokenService) {}

  // Information about the route and state of the router
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const token = localStorage.getItem('token');
    if (!token) {
        const result = confirm('Please login first!'); // Show a confirmation dialog

        if (result) {
          this.router.navigate(['/login']);
        } else {
          return false;
        }
    }

    if (state.url.startsWith('/adminhome')) {
        this.tokenService.decodeToken(localStorage.getItem('token')!);
        const isAdmin = this.tokenService.getPermissions().some((permission: { authority: string }) => permission.authority === 'ROLE_ADMIN');
  
        if (!isAdmin) {
            alert('Access denied');
            this.router.navigate(['/userhome']);
            return false;
        }
    }

    if(state.url.startsWith('/userhome')) {
        this.tokenService.decodeToken(localStorage.getItem('token')!);
        const isAdmin = this.tokenService.getPermissions().some((permission: { authority: string }) => permission.authority === 'ROLE_ADMIN');

        if(isAdmin) {
            alert('Access denied');
            this.router.navigate(['/adminhome']);
            return false;
        }
    }

    return true;
  }
}