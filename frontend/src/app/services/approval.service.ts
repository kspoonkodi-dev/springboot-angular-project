import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../Models/user.model';
import { Observable, catchError, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class approvalService {
  private apiUrl = 'http://localhost:8080/admin';

  constructor(private http: HttpClient) {}

  getPendingUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/all`).pipe(catchError(this.handleError));
  }

  processApprove(userId: number) {
    return this.http
      .put(`${this.apiUrl}/approve-instructor/${userId}`, {}, { responseType: 'text' })
      .pipe(catchError(this.handleError));
  }
  processReject(userId: number) {
    return this.http
      .put(`${this.apiUrl}/reject-instructor/${userId}`, {}, { responseType: 'text' })
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
