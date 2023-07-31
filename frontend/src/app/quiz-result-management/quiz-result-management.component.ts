import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TokenService } from '../service/token.service';
import { forkJoin } from 'rxjs';
import { formatDate } from '@angular/common';
import { Router } from '@angular/router';

interface QuizResult {
  quizResultId: number;
  categoryId: number;
  startTime: string;
  endTime: string;
  // Add any other properties if needed
}

@Component({
  selector: 'app-quiz-result-management',
  templateUrl: './quiz-result-management.component.html',
  styleUrls: ['./quiz-result-management.component.css']
})
export class QuizResultManagementComponent {
  constructor(private router: Router, private http: HttpClient, private tokenService: TokenService) { }

  quizResults: any[] = [];
  categoryList: any[] = []; 
  allCategories: any[] = [];
  users: any[] = [];
  DisplayName: string = "";
  filterCategory: string | null = null;
  filterUser: number | string | null = null;
  templeQuizResults: any[] = [];
  
  ngOnInit() {
    this.tokenService.decodeToken(localStorage.getItem('token')!);
    this.DisplayName = this.tokenService.getDisplayName();
    this.fetchQuizResults();
    this.fetchUsers();
    this.fetchAllCategories();
  }

  fetchAllCategories() {
    this.http.get<any>(`http://localhost:8080/categories`).subscribe(
      response => {
        this.allCategories = response.categories;
        console.log("All categories: ", this.allCategories);
      },
      error => {
        console.error('Failed to fetch categories:', error);
      }
    )
  }

  fetchUsers() {
    this.http.get<any>(`http://localhost:8080/users`).subscribe(
      response => {
        this.users = response.users;
        console.log("All users: ", this.users);
      },
      error => {
        console.error('Failed to fetch users:', error);
      }
    )
  }

  fetchQuizResults() {
    // Make an HTTP GET request to retrieve the quiz results for the user
    this.http.get<any>(`http://localhost:8080/quizzes`).subscribe(
      response => {
        this.quizResults = response.quizResults.map((quizResult: QuizResult) => {
          return {
            ...quizResult,
            startTime: this.formatDate(quizResult.startTime),
            endTime: this.formatDate(quizResult.endTime)
          };
        });
        console.log("All quiz results: ", this.quizResults);
        this.templeQuizResults = this.quizResults;
        //this.fetchCategories();
      },
      error => {
        console.error('Failed to fetch quiz results:', error);
      }
    );
  }
  
  /**
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
        console.log("Quiz results corresponding category list: ", this.categoryList);
      },
      error => {
        console.error('Failed to fetch categories:', error);
      }
    );
  }
    **/

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return formatDate(date, 'yyyy-MM-dd HH:mm:ss', 'en-US');
  }

  applyFilters() {
    // Reset filtered list
    this.templeQuizResults = [...this.quizResults];

    // Apply category filter
    if (this.filterCategory !== null) {
      if (this.filterCategory !== "all") {
        console.log("filterCategory: ",  this.filterCategory);
        this.templeQuizResults = this.templeQuizResults.filter(result => result.categoryName == this.filterCategory); 
      }
    }

    // Apply user filter
    if (this.filterUser !== null ) {
      if (this.filterUser == "all") {
        return;
      }
      console.log("filterUser: ",  this.filterUser);
      this.templeQuizResults = this.templeQuizResults.filter(result => result.userId == this.filterUser);
    }
  }
}
