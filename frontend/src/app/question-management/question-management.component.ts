import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-question-management',
  templateUrl: './question-management.component.html',
  styleUrls: ['./question-management.component.css']
})
export class QuestionManagementComponent implements OnInit{
  questions: any[] = [];
  //showModal = false;
  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.fetchQuestions();
  }

  fetchQuestions() {
    this.http.get<any>('http://localhost:8080/questions').subscribe(
      response => {
        this.questions = response.questions;
      },
      error => {
        console.error('Failed to fetch questions:', error);
      }
    );
  }

  suspendQuestion(questionId: number) {
    this.http.patch<any>(`http://localhost:8080/question/${questionId}/status?activate=false`, {}).subscribe(
      response => {
        console.log('Question suspended successfully');
        this.fetchQuestions();
      },
      error => {
        console.error('Failed to suspend question:', error);
      }
    );
  }

  activateQuestion(questionId: number) {
    this.http.patch<any>(`http://localhost:8080/question/${questionId}/status?activate=true`, {}).subscribe(
      response => {
        console.log('Question activate successfully');
        this.fetchQuestions();
      },
      error => {
        console.error('Failed to activate question:', error);
      }
    );
  }

  editQuestion(questionId: number) {
    this.router.navigateByUrl(`/edit-question/${questionId}`);
  }

  // openModal() {
  //   this.showModal = true;
  // }

  // closeModal() {
  //   this.showModal = false;
  // }
}
