import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { User } from '../Models/user.model';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {}

  createUser(user: FormData): Observable<any> {
    return this.http
      .post(this.apiUrl + 'instructors/register', user)
      .pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Something went Wrong';
    if (error.error && typeof error.error === 'object' && error.error.message) {
      console.log('error as object', error.error.message);
      errorMessage = error.error.message;
    }
    // Backend sent plain text
    else if (error.error && typeof error.error === 'string') {
      console.log('error as string', error.error);

      errorMessage = error.error;
    }
    if (error.status === 0) {
      errorMessage = 'Cannot connect to server';
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
    }

    console.error('HTTP Error:', errorMessage); // optional logging
    return throwError(() => errorMessage);
  }
}
