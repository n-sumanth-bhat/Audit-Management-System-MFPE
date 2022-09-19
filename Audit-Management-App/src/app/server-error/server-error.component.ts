import { Component, OnInit } from '@angular/core';
import { SecurityService } from './../Services/security.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-server-error',
  templateUrl: './server-error.component.html',
  styleUrls: ['./server-error.component.css']
})
export class ServerErrorComponent implements OnInit {

  constructor( 
    private router : Router,
    private securityService : SecurityService
  )
  {}
  goBack(){
    this.securityService.resetAll();
    this.router.navigate(["login"]);
  }

  ngOnInit(): void {
    this.securityService.resetAll();
  }

}
