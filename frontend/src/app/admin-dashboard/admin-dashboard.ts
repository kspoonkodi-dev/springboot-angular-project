import { Component, inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css',
})
export class AdminDashboard {
  router = inject(Router);
  logout() {
    localStorage.removeItem('token'); // remove JWT token
    this.router.navigateByUrl('/login'); // redirect to login page
  }
}
