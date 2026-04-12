import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ErrorService {
  constructor() {}

  // Call this in your component to set the message
  showError(errorMessage: string, component: any) {
    component.errorMessage = errorMessage;
  }
}
