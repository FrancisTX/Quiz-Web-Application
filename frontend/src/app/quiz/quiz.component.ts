import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent {
  constructor(private http: HttpClient, private router: Router, private tokenService: TokenService) { }

  showCategorySelection: boolean = true;
  categories: any[] = []; // Update the type as per your data structure
  questions: any[] = []; // Update the type as per your data structure
  choices: any[] = []; // Update the type as per your data structure
  questionsWithChoices: any[] = []; // Update the type as per your data structure
  categoryId: number = 0;
  userId: number = 0;
  startTime: Date = new Date();


  ngOnInit() {
    this.tokenService.decodeToken(localStorage.getItem('token')!);
    this.userId = this.tokenService.getUserId();
    // Fetch the categories from the backend API and assign them to the 'categories' property
    this.fetchCategories();
  }

  fetchCategories() {
    this.http.get<any>('http://localhost:8080/categories')
      .subscribe(
        response => {
          if (response.status.success) {
            this.categories = response.categories;
            console.log('Categories fetched successfully');
            console.log(this.categories);
          } else {
            console.error('Failed to fetch categories:', response.status.message);
          }
        },
        error => {
          console.error('Failed to fetch categories:', error);
        }
      );
  } 

  getQuestionsByCategoryId(categoryId: number) {
    this.categoryId = categoryId;
    const url = `http://localhost:8080/quiz?categoryId=${categoryId}`;
    this.http.get<any>(url)
      .subscribe(
        response => {
          if (response.status.success) {
            this.questionsWithChoices = response.questionsWithChoices;
            console.log('Questions fetched successfully');
            console.log('Questions with Choices:', this.questionsWithChoices);
  
            // Iterate through each question with choices
            this.questionsWithChoices.forEach(questionWithChoices => {
              const question = questionWithChoices.question;
              const choices = questionWithChoices.choices;
              
              console.log('Question:', question);
              console.log('Choices:', choices);
  
              // You can perform further processing or rendering logic here
            });
          } else {
            console.error('Failed to fetch questions:', response.status.message);
          }
        },
        error => {
          console.error('Failed to fetch questions:', error);
        }
      );
      this.showCategorySelection = false;
  }

  submitQuiz() {
    // Handle the submission of the quiz form, including sending the user's answers to the backend API
    const submissionData = {
      userId: this.userId,
      categoryId: this.categoryId,
      startTime: this.startTime,
      endTime: new Date(),
      result: [] as { questionId: number, choiceId: string }[]
    };
    this.questionsWithChoices.forEach(questionWithChoices => {
      const questionId = questionWithChoices.question.questionId;
      const selectedChoiceId = (document.querySelector(`input[name="${questionId}"]:checked`) as HTMLInputElement)?.value;
      
      if (selectedChoiceId) {
        submissionData.result.push({
          questionId,
          choiceId: selectedChoiceId
        });
      }
    });
    console.log('Quiz submissino: ', submissionData)

    this.http.post('http://localhost:8080/quiz', submissionData)
    .subscribe(
      response => {
        // Handle the response from the server
        console.log('Quiz submitted successfully: ', response);
        // Reset any necessary variables or navigate to a different page
        this.showCategorySelection = true;
        this.router.navigateByUrl('/userhome');
      },
      error => {
        // Handle any errors that occurred during the request
        console.error('Quiz submission failed:', error);
      }
    );
  }
}
