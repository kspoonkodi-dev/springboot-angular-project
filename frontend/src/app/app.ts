import { NgModule } from '@angular/core';
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { UserCreate } from './user-create/user-create';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, ReactiveFormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('crud_angular');
}
