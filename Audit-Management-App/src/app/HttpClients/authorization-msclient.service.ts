import { ProjectDetailsInterface } from './../Models/ProjectDetailsInterface';
import { Observable } from 'rxjs';
import { AuthenticationRequest } from './../Models/AuthenticationRequest';
import { Microservices } from '../Models/Microservices';
import { SecurityToken } from '../Models/SecurityToken';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationMSClientService {

  constructor(private authClient : HttpClient) { }

  authenticate(authRequest : AuthenticationRequest){
    // make rest call [POST] to /authenticate with authRequest as Request-body
    return this.authClient.post(Microservices["auth"]+"/authenticate", authRequest, 
                          { responseType : 'text',
                            //observe : 'response'
                        });
  }

  validate(securityToken : string){
    return this.authClient.post<ProjectDetailsInterface>(Microservices["auth"]+"/validate", 
      {
        headers: new HttpHeaders().set("Authorization", "Bearer "+securityToken)
      });
  }

  healthCheck(){
    return this.authClient.get(Microservices["auth"]+"/health-check", {
      responseType : 'text'
    });
  }
}
