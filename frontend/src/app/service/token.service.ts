import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';

@Injectable({
    providedIn: 'root'
  })
  export class TokenService {
    private decodedToken : any;
  
    constructor() { }

    decodeToken(token: string): void {
        //this.decodedToken = jwt_decode(token);
        this.decodedToken = jwt_decode(token) as {
            sub: string;
            permissions: { authority: string }[];
            custom: { name: string };
            userId: number;
          }; 
    }

    getToken(): any {
        return this.decodedToken;
    }
      
    getUsername(): string {
        return this.decodedToken.sub;
    }
      
    getPermissions(): { authority: string }[] {
        return this.decodedToken.permissions;
    }
      
    getDisplayName(): string {
        return this.decodedToken.custom.name;
    }
      
    getUserId(): number {
        return this.decodedToken.userId;
    }
}
  