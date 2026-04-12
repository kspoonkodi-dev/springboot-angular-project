import { Component, ChangeDetectorRef } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { User } from '../../Models/user.model';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { approvalService } from '../../services/approval.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-approvals',
  imports: [ReactiveFormsModule, CommonModule],
  standalone: true,
  templateUrl: './approvals.html',
  styleUrls: ['./approvals.css'],
})
export class Approvals {
  users$: Observable<User[]>;
  errorMessage: String = '';
  constructor(
    private http: HttpClient,
    private approvalService: approvalService,
    private cd: ChangeDetectorRef,
  ) {
    this.users$ = this.approvalService.getPendingUsers();
  }

  /*ngOnInit() {
    this.loadUser();
  }
  loadUser() {
    this.approvalService.getPendingUsers().subscribe((res) => {
      console.log(Array.isArray(res));
      console.log('Calling User service');
      console.log('Response', res);
      this.users = res;
      this.cd.detectChanges();
    });
  }*/
  approve(userId: number) {
    console.log('Approved:', userId);
    if (confirm('Are you sure you want to approve this instructor?')) {
      this.approvalService.processApprove(userId).subscribe({
        next: (res) => {
          this.users$ = this.approvalService.getPendingUsers();
        },
        error: (errMsg) => {
          this.errorMessage = errMsg;
          this.cd.detectChanges();
        },
      });
    }
  }

  reject(userId: number) {
    console.log('Rejected:', userId);
    if (confirm('Are you sure you want to reject this instructor?')) {
      this.approvalService.processReject(userId).subscribe({
        next: (res) => {
          this.users$ = this.approvalService.getPendingUsers();
        },
        error: (errMsg) => {
          this.errorMessage = errMsg;
          this.cd.detectChanges();
        },
      });
    }
  }
}
