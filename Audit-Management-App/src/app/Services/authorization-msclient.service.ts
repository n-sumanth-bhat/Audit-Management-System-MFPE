import { SecurityToken } from './../Models/SecurityToken';
import { AuthenticationRequest } from './../Models/AuthenticationRequest';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationMSClientService {

  constructor() { }

  getAuthToken(authRequest : AuthenticationRequest){
    // make rest call to /authenticate with authRequest as Request-body
  }

  getProjectDetails(securityToken : SecurityToken){
    let token : string = securityToken.jwt;
    // make rest call to /validate with token as Request-header
  }
}
