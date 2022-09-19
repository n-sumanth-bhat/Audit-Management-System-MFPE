import { SecurityService } from './../Services/security.service';
import { Component, OnInit } from '@angular/core';
import { ChecklistService } from '../Services/checklist.service';
import { Question } from './question';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checklist',
  templateUrl: './checklist.component.html',
  styleUrls: ['./checklist.component.css']
})
export class ChecklistComponent implements OnInit {
  
  questions: Question[] = [];
  connectionStatus: any = "Not connected";
  type: string = "";
  message : string = ""; 
  loadFlag : boolean = false;
  constructor(
    private checklistService:ChecklistService,private router:Router,
    public securityService : SecurityService
    ) {}

  getQuestions() : void {
    this.loadFlag = true;
    let fetch : Question[] = []; 
    this.checklistService.getQuestionsFromMS(this.type)
      // .subscribe(data => this.questions = data);
      .subscribe(
        (data)=>{
          fetch = data;
          if(data.length == 0){
            this.router.navigate(["backToLogin"]);
          }
        }, 
        (err)=>{this.router.navigate(["error"])}, 
        ()=>{
          this.message = "";
          this.questions = fetch;
        }
      );
  }

  connectionCheck() : void {
    this.checklistService.healthCheck().subscribe(data=>this.connectionStatus=data);
  }

  responseYes(i:number):void {
    this.questions[i].response = "YES";
  }

  responseNo(i:number):void {
    this.questions[i].response = "NO";
  }

  getResponse() : void {
    if(this.checklistService.validated(this.questions)){
      this.checklistService.getResponse(this.questions);
      this.securityService.turnOffSpecialFlag();
      this.message = "";
      this.router.navigate(['severity']);
    }else{
      this.message = "Please answer all the questions to submit!!";
    }    
  }

  ngOnInit(): void {
    //login comes
    this.message = "";
    this.loadFlag = false;
    this.securityService.healthCheck().subscribe(
      (data)=>{
      },
      (err)=>{
        this.router.navigate(['error']);
      },
      ()=>{
        this.checklistService.healthCheck().subscribe(
          (data)=>{
          },
          (err)=>{
            this.router.navigate(['error']);
          },
          ()=>{
            this.securityService.checkAuthFromLocal('checklist', 'backToLogin');
          }
        );
      }
    );
  }
}
