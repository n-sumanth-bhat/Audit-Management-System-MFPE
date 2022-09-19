import { SecurityService } from './security.service';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(
    private securityService : SecurityService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler){
    let requestWithHeader = req.clone({
      setHeaders : {  // creating the authorization header
        Authorization : "Bearer " + this.securityService.getSecurityToken()
      }
    })
    return next.handle(requestWithHeader)
  }
}
