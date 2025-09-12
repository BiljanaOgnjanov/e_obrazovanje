import { Component } from '@angular/core';
interface Exam {
  id: number;
  name: string;
  date: Date;
  details: string;
  price: number;
  teacher: string;
}

interface ApplicationStatus {
  [examId: number]: boolean;
}

@Component({
  selector: 'app-student-course-selection',
  templateUrl: './student-course-selection.component.html',
  styleUrls: ['./student-course-selection.component.css']
})
export class StudentCourseSelectionComponent {
  exams: Exam[] = [
    { id: 1, name: 'Matematika', date: new Date('2025-12-01'), details: 'Ispit iz matematike za I godinu.', price: 2000, teacher: 'Dr. Marko Marković' },
    { id: 2, name: 'Fizika', date: new Date('2025-12-15'), details: 'Ispit iz fizike za II godinu.', price: 1500, teacher: 'Prof. Jovana Jovanović' },
    { id: 3, name: 'Programiranje', date: new Date('2025-12-20'), details: 'Ispit iz programiranja za III godinu.', price: 2500, teacher: 'Dr. Aleksandar Aleksić' },
  ];

  examApplied: ApplicationStatus = {};

  studentBalance: number = 10000;  // sa beka

  feedbackMessage: string = '';
  feedbackType: string = 'success';
  totalCost: number = 0;

  constructor() { }

  ngOnInit(): void {
    //
  }

  applyForExam(exam: Exam): void {
    if (!exam || !exam.id) {
      console.error("Invalid exam");
      return;
    }

    if (this.studentBalance < exam.price) {
      this.feedbackMessage = `Nemate dovoljno sredstava na računu za prijavu na ispit: ${exam.name}.`;
      this.feedbackType = 'danger';
      return;
    }

    this.examApplied[exam.id] = true;

    this.totalCost = this.getTotalCost();

    this.studentBalance -= this.totalCost;

    this.feedbackMessage = `Uspešno ste se prijavili za ispit: ${exam.name}. Ukupni trošak: ${this.totalCost} RSD.`;
    this.feedbackType = 'success';

    setTimeout(() => {
      this.feedbackMessage = '';
    }, 5000);
  }

  getTotalCost(): number {
    let total = 0;
    for (const examId in this.examApplied) {
      if (this.examApplied[examId]) {
        const exam = this.exams.find(exam => exam.id === +examId);
        if (exam) {
          total += exam.price;
        }
      }
    }
    return total;
  }

  // Check if the exam date is at least 1 day away
  isLessThanOneDay(examDate: Date): boolean {
    const today = new Date();
    const timeDifference = examDate.getTime() - today.getTime();
    const dayDifference = timeDifference / (1000 * 3600 * 24); // Convert milliseconds to days

    return dayDifference < 1; // Return true if less than 1 day away
  }

  // Cancel the application for the selected exam
  cancelApplication(exam: Exam): void {
    if (!exam || !exam.id) {
      console.error("Invalid exam");
      return;
    }

    if (this.isLessThanOneDay(exam.date)) {
      this.feedbackMessage = `Ne možete da otkažete prijavu za ispit: ${exam.name}. Rok za otkazivanje je istekao.`;
      this.feedbackType = 'danger';
    } else {
      this.examApplied[exam.id] = false; // Remove the application for the exam
      this.totalCost = this.getTotalCost(); // Update the total cost
      this.studentBalance += exam.price; // Refund the amount for cancellation
      this.feedbackMessage = `Uspešno ste otkazali prijavu za ispit: ${exam.name}.`;
      this.feedbackType = 'success';
    }

    // Reset feedback message after 5 seconds
    setTimeout(() => {
      this.feedbackMessage = '';
    }, 5000);
  }
}
