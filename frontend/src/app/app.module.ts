import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { TokenService } from './service/token.service';
import { AuthGuardService } from './service/auth-guard.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { UserHomeComponent } from './user-home/user-home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ContactComponent } from './contact/contact.component';
import { QuizComponent } from './quiz/quiz.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './service/interceptor.service';
import { ResultComponent } from './result/result.component';
import { ContactManagementComponent } from './contact-management/contact-management.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { QuizResultManagementComponent } from './quiz-result-management/quiz-result-management.component';
import { QuestionManagementComponent } from './question-management/question-management.component';
import { ModalComponent } from './modal/modal.component';
import { AddQuestionComponent } from './add-question/add-question.component';
import { EditQuestionComponent } from './edit-question/edit-question.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserHomeComponent,
    AdminHomeComponent,
    NavBarComponent,
    ContactComponent,
    QuizComponent,
    ResultComponent,
    ContactManagementComponent,
    UserManagementComponent,
    QuizResultManagementComponent,
    QuestionManagementComponent,
    ModalComponent,
    AddQuestionComponent,
    EditQuestionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    },
    TokenService,
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
