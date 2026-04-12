import { Component, ChangeDetectorRef } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Role } from '../Models/role.enum';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-create',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './user-create.html',
  styleUrl: './user-create.css',
  providers: [UserService],
})
export class UserCreate {
  userForm: FormGroup;
  role = Role.ADMIN;
  errorMessage: String = '';

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private cd: ChangeDetectorRef,
  ) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      //role: ['', this.roleVerify],
    });
  }

  createUser(): void {
    debugger;
    if (this.userForm.invalid) return;
    const formData = new FormData();
    formData.append('username', this.userForm.get('username')!.value);
    formData.append('password', this.userForm.get('password')!.value);
    formData.append('email', this.userForm.get('email')!.value);
    // formData.append('role', this.userForm.get('role')!.value);
    this.userService.createUser(formData).subscribe({
      next: (res) => {
        alert('User Created Successfully');
        this.router.navigate(['login']);
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
        alert(errMsg);
      },
    });
    console.log('inside user create');
  }

  /*roleVerify(role: any): Promise<any> | Observable<any> {
    const respone = new Promise((resolve, reject) => {
      setTimeout(() => {
        if (role === 'ADMIN') {
          resolve({ roleVerify: true });
        } else if (role === 'INSTRUCTOR') {
          resolve({ roleVerify: true });
        } else {
          resolve(null);
        }
      }, 5000);
    });
    return respone;
  }*/
}
