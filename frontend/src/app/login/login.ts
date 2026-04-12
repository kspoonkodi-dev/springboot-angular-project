import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Role } from '../Models/role.enum';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  standalone: true,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  private apiUrl = 'http://localhost:8080/';

  loginForm: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  http = inject(HttpClient);
  router = inject(Router);

  onLogin() {
    debugger;
    const formValue = this.loginForm.value;
    this.http.post(this.apiUrl + 'login', formValue).subscribe({
      next: (response: any) => {
        console.log(response);
        debugger;
        if (response != null) {
          console.log(response);
          debugger;
          localStorage.setItem('token', response.jwt);
          localStorage.setItem('role', response.role);
          console.log(response.jwt);
          console.log(response.role);
          alert('login successful');
          console.log('Role received:', response.role);
          console.log('Enum Instructor:', Role.INSTRUCTOR);
          console.log('Enum Admin:', Role.ADMIN);
          switch (response.role) {
            case Role.ADMIN:
              this.router.navigateByUrl('/admin-dashboard');
              break;

            case Role.INSTRUCTOR:
              this.router.navigateByUrl('/instructor-dashboard');
              break;

            default:
              console.log('Unknown role');
          }
          /*if (response.jwt === Role.INSTRUCTOR) {
            console.log('login');
            this.router.navigate(['instructor-dashboard']);
          } else if (response.jwt === Role.ADMIN) {
            this.router.navigate(['admin-dashboard']);
          }*/
        } else {
          alert('User not found');
        }
      },
      error: (error) => {
        alert(error.statusText);
      },
    });
  }
}
