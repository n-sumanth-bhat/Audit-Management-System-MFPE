import { User } from '../Models/User';
import { Router } from '@angular/router';
import { SpecialFLag } from './../Models/SpecialFlag';
import { ProjectDetailsInterface } from './../Models/ProjectDetailsInterface';
import { Observable } from 'rxjs';
import { AuthorizationMSClientService } from '../HttpClients/authorization-msclient.service';
import { LoginStatus } from './../Models/LoginStatus';
import { AuthenticationRequest } from './../Models/AuthenticationRequest';
import { ProjectDetails } from './../Models/ProjectDetails';
import { SecurityToken } from './../Models/SecurityToken';
import { Injectable } from '@angular/core';
import { prepareEventListenerParameters } from '@angular/compiler/src/render3/view/template';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  public message : string = "";
  constructor(
    // Models
    private token : SecurityToken,
    private loginStatus : LoginStatus,
    private projectDetails : ProjectDetails,
    private authenticationRequest : AuthenticationRequest,
    private specialFlag : SpecialFLag,
    public user : User,
    // services
    private authClient : AuthorizationMSClientService,
    private router : Router
    ) { 
    }

  turnOnSpecialFlag() {
    this.specialFlag.flag = true;
  }
  turnOffSpecialFlag() {
    this.specialFlag.flag = false;
  }

  getSpecialFlag() : boolean{
    return this.specialFlag.flag;
  }

  ifStillValid() : Observable<ProjectDetailsInterface> {  // checks if the token from localstorage is valid or not
    let ltoken : string = localStorage.getItem("auditToken") || this.token.Jwt;
    this.token.Jwt = ltoken;
    return this.validateToken(ltoken);
  }

  healthCheck(){
    return this.authClient.healthCheck();
  }
  
  createSecuritytokenObservable(username : string, password : string){
    // take result from AuthorizationMSClient service, using the username, passord :: subscribe
    // setting the authenticationRequest object for response body
    this.authenticationRequest.Username = username;
    this.authenticationRequest.Password = password;
    // getting the respone back from Auth-MS through AuthClientService
    return this.authClient.authenticate(this.authenticationRequest);
  }
  
  validateToken(token:string){
    return this.authClient.validate(token);
  }
  
  setLoginStatus(status : boolean){
    this.loginStatus.Status = status;
  }
  
  getLoginStatus(){
    return this.loginStatus.Status;
  }
  getSecurityToken(){
    // take result from AuthorizationMSClient service, using the security-token :: subscribe
    return this.token.Jwt;
    
  }
  setSecurityToken(token : string){
    // take result from AuthorizationMSClient service, using the security-token :: subscribe
    this.token.Jwt = token;
  }
  
  getProjectDetails() : ProjectDetails{
    let nameArray : Array<string> = this.projectDetails.Name.split(" ");
    console.log(nameArray);
    this.user.username = nameArray[0] + " " + nameArray[1][0] + ".";
    this.user.logStatus = this.getLoginStatus();
    return this.projectDetails;
  }
  
  syncAll(username : string, pname : string) {
    if(localStorage.getItem("auditToken")==null && this.token.Jwt!=""){
      localStorage.setItem("auditToken", this.token.Jwt);
    }
    else if(this.token.Jwt=="" && localStorage.getItem("auditToken")!=null){
      this.token.Jwt = localStorage.getItem("auditToken") || "";
    }
    else{
      localStorage.setItem("auditToken", this.token.Jwt);
    }
    this.loginStatus.Status = true;
    // create username pass
    this.projectDetails.Name = username;
    this.projectDetails.ProjectName = pname;
    this.projectDetails.Valid = true;
    this.specialFlag.flag = true;
    let nameArray : Array<string> = username.split(" ");
    this.user.username = nameArray[0] + " " + nameArray[1][0] + ".";
    this.user.logStatus = this.getLoginStatus();
  }

  resetAll(){
    this.token.Jwt = "";
    localStorage.removeItem("auditToken");
    this.loginStatus.Status = false;
    this.projectDetails.Name = "";
    this.projectDetails.ProjectName = "";
    this.user.logStatus = false;
    this.user.username = "";
    this.projectDetails.Valid = false;
    this.specialFlag.flag = false;
    this.message = "";
  }

  checkAuthFromLocal(spath : string, epath : string){
    if(localStorage.getItem("auditToken")==null){
      if(this.getLoginStatus()){
        // update localstorage
        localStorage.setItem("auditToken", this.token.Jwt);
        // do nothing
      }
      else{
        this.router.navigate([epath]);
      }
    }
    else{
      //when you get the jwtToken in LocalStorage
      let fetch : ProjectDetailsInterface;
      this.ifStillValid().subscribe( //validate once if it works or not
        (data)=>{
          fetch = data;
          if(data.valid){
            this.syncAll(data.name, data.projectName) // can access everypage except Severity
          }else{
            this.resetAll();
            this.router.navigate([epath]);
          }
        },
        (err)=>{},
        ()=>{
          if(this.getLoginStatus()){
            //console.log("syncing");
            this.turnOnSpecialFlag();
          }
          else{
            //console.log("resetting");
            this.resetAll();
            this.router.navigate([epath]);
          }
        }
      );
    }
  }
}
