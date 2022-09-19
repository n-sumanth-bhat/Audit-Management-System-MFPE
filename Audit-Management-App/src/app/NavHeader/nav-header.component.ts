import { Router } from '@angular/router';
import { User } from '../Models/User';
import { ProjectDetails } from './../Models/ProjectDetails';
import { SecurityService } from './../Services/security.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-nav-header',
  templateUrl: './nav-header.component.html',
  styleUrls: ['./nav-header.component.css'],
})
export class NavHeaderComponent implements OnInit {
  // @Input()  public username : string = "";  // shows the username in header (keep max-string-length = 10)
  // @Input()  public logStatus : boolean = false;     // it will decide if we want to show the username part or not
                                          // after logging in, the log status will be "true"

  constructor(
    private securityService : SecurityService,
    private router : Router,
    public user : User,

  ) { }
  logout(){
    this.securityService.resetAll();
    this.router.navigate(['login']);
  }


  ngOnInit(): void {
    if(this.securityService.getLoginStatus()){
      let nameArray : Array<string> = this.securityService.getProjectDetails().Name.split(" ");
      this.user.username = nameArray[0] + " " + nameArray[1][0] + ".";
      this.user.logStatus = this.securityService.getLoginStatus();
    }
  }

}
