import {
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
  } from '@angular/common/http';
  import { Injectable } from '@angular/core';
  import { Observable } from 'rxjs';
  
  @Injectable()
  // Must implement the HttpInterceptor Interface!
  export class InterceptorService implements HttpInterceptor {
    intercept(
      req: HttpRequest<any>,
      next: HttpHandler
    ): Observable<HttpEvent<any>> {
      // Some logic to grab the token
      const TOKEN = localStorage.getItem('token');
  
      // HttpHandler to clone our Request Object and append a token to the header
      if (TOKEN) return next.handle(req.clone({ setHeaders: { Authorization: `Bearer ${TOKEN}`} }));
      return next.handle(req);
    }
    
    constructor() {}
  }
  