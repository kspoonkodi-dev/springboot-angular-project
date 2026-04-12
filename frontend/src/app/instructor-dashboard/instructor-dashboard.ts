import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-instructor-dashboard',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './instructor-dashboard.html',
  styleUrl: './instructor-dashboard.css',
})
export class InstructorDashboard {
  router = inject(Router);
  logout() {
    localStorage.removeItem('token'); // remove JWT token
    this.router.navigateByUrl('/login'); // redirect to login page
  }
}
