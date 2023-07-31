import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  @Input() showModal: boolean = false;
  @Output() showModalChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  newQuestion: any = {
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
  categories : any[] = [];

  saveQuestion() {
    // Perform the necessary logic to save the question
    console.log('New question:', this.newQuestion);
    // Reset the form after saving the question
    this.resetForm();
    this.showModalChange.emit(false);
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
}
