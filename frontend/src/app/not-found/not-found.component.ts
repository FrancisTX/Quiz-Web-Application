import { Component } from '@angular/core';

@Component({
  selector: 'app-not-found',
  template: `
    <h1>404 - Not Found</h1>
    <p>The page you're looking for doesn't exist.</p>
    <a routerLink="/login">Go to Login</a>
  `,
})
export class NotFoundComponent {}