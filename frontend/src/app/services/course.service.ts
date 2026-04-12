import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Course } from '../Models/course.model';
import { CourseTitle } from '../Models/coursetitle.model';
import { catchError, Observable, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private apiUrl = 'http://localhost:8080/courses';

  constructor(private http: HttpClient) {}

  getAllUserCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/user/all`).pipe(catchError(this.handleError));
  }

  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/all`).pipe(catchError(this.handleError));
  }

  getAllCourseForTutorial(): Observable<CourseTitle[]> {
    return this.http
      .get<CourseTitle[]>(`${this.apiUrl}/allCourse`)
      .pipe(catchError(this.handleError));
  }

  createCourse(courseForm: FormData) {
    return this.http.post(`${this.apiUrl}/create`, courseForm).pipe(catchError(this.handleError));
  }
  updateCourse(id: number, courseForm: FormData) {
    return this.http
      .put(`${this.apiUrl}/update/${id}`, courseForm)
      .pipe(catchError(this.handleError));
  }

  deleteCourse(id: number) {
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
