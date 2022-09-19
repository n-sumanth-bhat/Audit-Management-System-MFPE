import { Microservices } from './../Models/Microservices';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Question } from '../CheckList/question';


@Injectable({
  providedIn: 'root'
})
export class ChecklistService {

  readonly APIUrl=Microservices["checklist"];

  responses:Question[] = [];
  
  getQuestionsFromMS(type: string) : Observable<Question[]> {
    //let params1=new HttpParams().set('type','Internal');
    console.log(type);
    return this.http.get<Question[]>(this.APIUrl+'/auditchecklistquestions/'+type);
    
  }

  healthCheck() {
    return this.http.get(this.APIUrl+'/health-check',{responseType:'text'});
  }

  getResponse(responses: Question[]) : void {
    this.responses = responses;
  }
  
  sendResponse() : Question[] {
    return this.responses;
  }

  constructor(private http:HttpClient) { }

}
