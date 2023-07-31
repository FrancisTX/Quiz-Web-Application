import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from '../service/token.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {
  quizId: number = 0;
  countCorrect: number = 0;
  questions: any[] = []; // Update the type based on the structure of the questions
  choices: any[] = []; // Update the type based on the structure of the choices
  selected: any[] = []; // Update the type based on the structure of the selected choices
  isAdmin: boolean = false;

  constructor(private http: HttpClient, private route: ActivatedRoute, private tokenService: TokenService) {}

  ngOnInit() {
    // Retrieve the quizId from the route parameters
    this.tokenService.decodeToken(localStorage.getItem('token')!);
    console.log(this.tokenService.getUsername());
    this.isAdmin = this.tokenService.getPermissions().some((permission: { authority: string }) => permission.authority === 'ROLE_ADMIN');
    console.log(this.isAdmin);
    this.route.paramMap.subscribe(params => {
      this.quizId = +params.get('quizId')!; // Convert the parameter to a number
      this.fetchQuizResult();
    });
  }

  fetchQuizResult(): void {
    const url = `http://localhost:8080/questions/${this.quizId}`;

    this.http.get<any>(url).subscribe(
      response => {
        console.log('Quiz result and questions fetched successfully');
        console.log(response);

        //this.quizResult = response.quizResult;
        //question it self
        this.questions = response.quizQuestions.map((item: any) => item.questionsDto);
        //choices
        this.choices = response.quizQuestions.map((item: any) => item.choices);
        //selected choice
        this.selected = response.quizQuestions.map((item: any) => item.choiceDto);

        this.countCorrect = this.calculateCorrectAnswers();

        console.log('Questions:', this.questions);
        console.log('Choices:', this.choices);
        console.log('Selected Choices:', this.selected);
        console.log('Correct: ', this.countCorrect);
      },
      error => {
        console.error('Failed to fetch quiz result and questions:', error);
      }
    );
  }

  calculateCorrectAnswers(): number {
    let count = 0;
    for (let i = 0; i < this.selected.length; i++) {
      if (this.selected[i].correct) {
        count++;
      }
    }
    return count;
  }
}
