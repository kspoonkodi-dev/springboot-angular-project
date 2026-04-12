import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Tutorial } from '../Models/tutorial.model';
import { Observable, catchError, throwError } from 'rxjs';
import { FormGroupName } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class tutorialService {
  private apiUrl = 'http://localhost:8080/tutorial';

  constructor(private http: HttpClient) {}

  getAllTutorial() {
    return this.http.get(`${this.apiUrl}/all`).pipe(catchError(this.handleError));
  }
  getAllTutorials(courseId: number): Observable<Tutorial[]> {
    return this.http
      .get<Tutorial[]>(`${this.apiUrl}/all/${courseId}`)
      .pipe(catchError(this.handleError));
  }

  getTutorialByCourseId(courseId: number): Observable<Tutorial[]> {
    return this.http
      .get<Tutorial[]>(`${this.apiUrl}/courseById/${courseId}`)
      .pipe(catchError(this.handleError));
  }

  createTutorial(tutorialForm: FormData) {
    return this.http.post(`${this.apiUrl}/create`, tutorialForm).pipe(catchError(this.handleError));
  }

  updateTutorial(id: number, tutorialForm: FormData) {
    return this.http
      .put(`${this.apiUrl}/update/${id}`, tutorialForm)
      .pipe(catchError(this.handleError));
  }

  deleteTutorial(id: number) {
    return this.http
      .delete(`${this.apiUrl}/delete/${id}`, { responseType: 'text' })
      .pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Something went wrong';
    if (error.error && typeof error.error === 'object' && error.error.message) {
      errorMessage = error.error.message;
    }
    // Backend sent plain text
    else if (error.error && typeof error.error === 'string') {
      errorMessage = error.error;
    }
    // Network error
    else if (error.status === 0) {
      errorMessage = 'Cannot connect to server';
    }
    // Other HTTP errors
    else {
      errorMessage = `Error ${error.status}: ${error.message}`;
    }

    console.error('HTTP Error:', errorMessage); // optional logging
    return throwError(() => errorMessage);
  }
}
