import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-question',
  templateUrl: './edit-question.component.html',
  styleUrls: ['./edit-question.component.css']
})
export class EditQuestionComponent implements OnInit {
  questionId: number = 0;
  questionDescription: string = '';
  choices: any[] = [];
  correctAnswer: number = 0;

  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) {}

  ngOnInit() {
    // Retrieve the questionId from the route parameters
    this.route.params.subscribe(params => {
      this.questionId = params['questionId'];
      console.log('Question ID:', this.questionId);
      
      // Perform the necessary logic to fetch the question details
      // this.questionDescription = 'Sample question description';
      // this.choices = ['Choice 1', 'Choice 2', 'Choice 3', 'Choice 4'];
      // this.correctAnswer = '2';
      this.fetchQuestionDetails(this.questionId);
    });
  }

  fetchQuestionDetails(questionId: number) {
    // Make an HTTP GET request to fetch the question details based on the questionId
    this.http.get<any>(`http://localhost:8080/editquestion/${questionId}`).subscribe(
      response => {
        // Handle the response and assign the values to component properties
        this.questionDescription = response.description;
        console.log('Question Description:', this.questionDescription);
        this.choices = response.choices;
        console.log('Choices:', this.choices);
        this.correctAnswer = response.correctAnswer;
        console.log('Correct Answer:', this.correctAnswer);
      },
      error => {
        console.error('Failed to fetch question details:', error);
        // Handle the error as needed
      }
    );
  }

  saveChanges() {
    // Perform the necessary logic to save the edited question
    // Replace the example data below with your actual implementation

    const editedQuestion = {
      description: this.questionDescription,
      choices: this.choices.map((choice, index) => ({
        choiceId: choice.choiceId,
        description: choice.description,
        correct: (index + 1) === this.correctAnswer
      })),
      correctAnswer: this.correctAnswer
    };
    console.log('Edited Question:', editedQuestion);

    this.http.post<any>(`http://localhost:8080/editquestion/${this.questionId}`, editedQuestion).subscribe(
      response => {
        // Handle the response and assign the values to component properties
        this.router.navigate(['/question-management']);
      },
      error => {
        console.error('Failed to fetch question details:', error);
        // Handle the error as needed
      }
    );
  }
}
