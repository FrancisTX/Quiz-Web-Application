import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {
  categories: any[] = [];
  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    console.log('AddQuestionComponent initialized');
    this.fetchAllCategories();
  }

  newQuestion: any = {
    categoryId: '',
    description: '',
    choices: [
      { description: '' },
      { description: '' },
      { description: '' },
      { description: '' }
    ],
    correctAnswer: ''
  };

  saveQuestion() {
    // Perform the necessary logic to save the question
    console.log('New question:', this.newQuestion);
    //post this body to 8080/questions
    this.http.post('http://localhost:8080/questions', this.newQuestion)
    .subscribe(
      response => {
        console.log('Question saved successfully:', response);
        // Reset the form after saving the question
        this.resetForm();
        this.router.navigate(['/question-management']);
      },
      error => {
        console.error('Error saving question:', error);
      }
    );
  }

  resetForm() {
    this.newQuestion = {
      categoryId: '',
      questionDescription: '',
      choices: [
        { description: '' },
        { description: '' },
        { description: '' },
        { description: '' }
      ],
      correctAnswer: ''
    };
  }

  fetchAllCategories() {
    this.http.get<any>(`http://localhost:8080/categories`).subscribe(
      response => {
        this.categories = response.categories;
        console.log("All categories: ", this.categories);
      },
      error => {
        console.error('Failed to fetch categories:', error);
      }
    )
  }
}
