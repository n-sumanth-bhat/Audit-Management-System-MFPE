import { SecurityService } from './../Services/security.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-back-to-login',
  templateUrl: './back-to-login.component.html',
  styleUrls: ['./back-to-login.component.css']
})
export class BackToLoginComponent implements OnInit {

  constructor(
    private router : Router,
    private securityService : SecurityService
  ) { }

  goBack(){
    this.securityService.resetAll();
    this.router.navigate(["login"]);
  }

  ngOnInit(): void {
    this.securityService.resetAll();
  }

}

