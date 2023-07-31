import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TokenService } from '../service/token.service';
import { forkJoin } from 'rxjs';
import { formatDate } from '@angular/common';

interface QuizResult {
  quizResultId: number;
  categoryId: number;
  startTime: string;
  endTime: string;
  // Add any other properties if needed
}

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent {
  
  quizResults: any[] = []; 
  categoryList: any[] = []; 
  userId: number = 0;
  constructor(private router: Router, private http: HttpClient, private tokenService: TokenService) {}

  ngOnInit() {
    this.tokenService.decodeToken(localStorage.getItem('token')!);
    this.userId = this.tokenService.getUserId();
    this.fetchQuizResults();
  }


  fetchQuizResults() {
    // Make an HTTP GET request to retrieve the quiz results for the user
    this.http.get<any>(`http://localhost:8080/quiz/${this.userId}`).subscribe(
      response => {
        this.quizResults = response.quizResults.map((quizResult: QuizResult) => {
          return {
            ...quizResult,
            startTime: this.formatDate(quizResult.startTime),
            endTime: this.formatDate(quizResult.endTime)
          };
        });
        this.fetchCategories();
      },
      error => {
        console.error('Failed to fetch quiz results:', error);
      }
    );
  }
  
  fetchCategories() {
    // Create an array of observables for fetching category information
    const categoryObservables = this.quizResults.map(quizResult => {
      const categoryId = quizResult.categoryId;
      return this.http.get<any>(`http://localhost:8080/categories/${categoryId}`);
    });
  
    // Use forkJoin to wait for all category observables to complete
    forkJoin(categoryObservables).subscribe(
      responses => {
        console.log(responses);
        // Update the category name in the categoryList array
        this.categoryList = responses.map(response => response.message);
        console.log(this.categoryList);
      },
      error => {
        console.error('Failed to fetch categories:', error);
      }
    );
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return formatDate(date, 'yyyy-MM-dd HH:mm:ss', 'en-US');
  }
}
