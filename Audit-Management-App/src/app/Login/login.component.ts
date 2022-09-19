import { User } from '../Models/User';
import { ProjectDetails } from './../Models/ProjectDetails';
import { SecurityService } from './../Services/security.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private securityService : SecurityService,
    private router : Router,
    public projectDetails : ProjectDetails,
    public user : User
    ) { }

  public username : string = "";    // ngModel - takes username from form
  public password : string = "";    // ngModel - takes password from form
  public message : string = "";     // it will be shown when anyone gives wrong credential

  onLogIn(){
    // get the username and password, validate it, send it to SecurityService
    // delegate this info to SecurityService
    if(this.username == null || this.password == null){
      this.message = "Please provide valid credentials";
    }
    this.securityService.healthCheck().subscribe(
      (data)=>{
      },
      (err)=>{
        this.securityService.resetAll();
        this.router.navigate(['error']);
      },
      ()=>{
        this.securityService.createSecuritytokenObservable(this.username, this.password).subscribe(
          data => {
              //checks
              if(data.includes(".")){
                this.message = "";
                this.securityService.setLoginStatus(true);
                this.securityService.turnOnSpecialFlag();
                this.securityService.setSecurityToken(data);
              }
          },
          err => {
              this.message = "Give Proper Username and Password!!!";
          },
          () => {
            // routes based on authenticationStatus
              if(this.securityService.getLoginStatus()){
                this.securityService.validateToken(this.securityService.getSecurityToken()).subscribe(
                  (data) => {
                    //checks
                    this.projectDetails.Name = data.name;
                    this.projectDetails.ProjectName = data.projectName;
                    this.projectDetails.Valid = data.valid;
                  },
                  err =>{
    
                  },
                  ()=>{
                    // sets the localstorage
                    localStorage.setItem("auditToken", this.securityService.getSecurityToken());
                    // routes to checklist
                    this.router.navigate(['checklist']);            
                  }
                );
              }
          }
        );
      }
    );
    
  }


  ngOnInit(): void {
    // this.message = "";
    // //health-check
    // this.securityService.healthCheck().subscribe(
    //   (data)=>{
    //   },
    //   (err)=>{
    //     this.securityService.resetAll();
    //     this.router.navigate(['error']);
    //   },
    //   ()=>{
    //     //init jtwtoken in not localstorage
    //     this.securityService.checkAuthFromLocal('login', 'login');
    //   }
    // );
  }

}
