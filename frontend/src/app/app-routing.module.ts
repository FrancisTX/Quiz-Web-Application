import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ContactComponent } from './contact/contact.component';
import { QuizComponent } from './quiz/quiz.component';
import { AuthGuardService } from './service/auth-guard.service';
import { NotFoundComponent } from './not-found/not-found.component';
import { ResultComponent } from './result/result.component';
import { ContactManagementComponent } from './contact-management/contact-management.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { QuizResultManagementComponent } from './quiz-result-management/quiz-result-management.component';
import { QuestionManagementComponent } from './question-management/question-management.component';
import { AddQuestionComponent } from './add-question/add-question.component';
import { EditQuestionComponent } from './edit-question/edit-question.component';
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect root path to login page
  { path: 'login', component: LoginComponent },
  { path: 'userhome', component: UserHomeComponent , canActivate: [AuthGuardService] },
  { path: 'adminhome', component: AdminHomeComponent , canActivate: [AuthGuardService] },
  { path: 'register', component: RegisterComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'quiz', component: QuizComponent , canActivate: [AuthGuardService]},
  { path: 'result/:quizId', component: ResultComponent , canActivate: [AuthGuardService]},
  { path: 'contact-us-management', component: ContactManagementComponent , canActivate: [AuthGuardService]},
  { path: 'user-management', component: UserManagementComponent , canActivate: [AuthGuardService]},
  { path: 'quiz-management', component: QuizResultManagementComponent, canActivate: [AuthGuardService] },
  { path: 'add-question', component: AddQuestionComponent,canActivate: [AuthGuardService]},
  { path: 'question-management', component: QuestionManagementComponent, canActivate: [AuthGuardService] },
  { path: 'edit-question/:questionId', component: EditQuestionComponent, canActivate: [AuthGuardService] },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
