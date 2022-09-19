import { ServerErrorComponent } from './server-error/server-error.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChecklistComponent } from './CheckList/checklist.component';
import { SeverityComponent } from './Severity/severity.component';
import { BackToLoginComponent } from './back-to-login/back-to-login.component';
import { LoginComponent } from './Login/login.component';

const routes: Routes = [
  {path : "", component : LoginComponent},  // login in beginning
  {path:"severity",component:SeverityComponent},
  {path : "login", component : LoginComponent},  // login
  {path : "checklist", component : ChecklistComponent},  // checklists
  {path : "backToLogin", component : BackToLoginComponent}, // if unauthorized access happens
  {path : "error", component : ServerErrorComponent}, // if nothing matches
  {path : "**", component : LoginComponent} // if nothing matches

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
