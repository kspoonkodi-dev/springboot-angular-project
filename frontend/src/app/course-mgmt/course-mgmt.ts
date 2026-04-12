import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourseService } from '../services/course.service';
import { Course } from '../Models/course.model';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ErrorService } from '../services/error.service';

@Component({
  selector: 'app-course-mgmt',
  imports: [ReactiveFormsModule, CommonModule],
  standalone: true,
  templateUrl: './course-mgmt.html',
  styleUrl: './course-mgmt.css',
})
export class CourseMgmt implements OnInit {
  courses: Course[] = [];
  selectedCourseId?: number;
  courseForm!: FormGroup;
  errorMessage: String = '';
  successMessage: String = '';

  viewMode: 'create' | 'update' | 'delete' = 'create';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private courseService: CourseService,
    private errorService: ErrorService,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit() {
    this.courseForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      status: ['ACTIVE', Validators.required],
    });

    this.loadCourse();
  }

  loadCourse() {
    console.log('called getAllCourse');
    this.courseService.getAllCourses().subscribe((res) => {
      alert('get All Courses');
      this.courses = res;
      this.cd.detectChanges();
    });
  }

  selectCourse(c: Course) {
    console.log('Selected:', c);
    this.selectedCourseId = c.courseId;
    this.courseForm.patchValue({
      title: c.title,
      description: c.description,
      status: c.status,
    });
  }

  handleAction() {
    if (this.viewMode === 'create') {
      this.createCourse();
    } else if (this.viewMode === 'update') {
      this.updateCourse();
    } else {
      this.deleteCourse();
    }
  }

  createCourse() {
    this.courseService.createCourse(this.courseForm.value).subscribe({
      next: (res) => {
        alert('Course Created Successfully');

        this.courseForm.reset();
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
        alert(errMsg);
      },
    });
  }

  updateCourse() {
    this.courseService.updateCourse(this.selectedCourseId!, this.courseForm.value).subscribe({
      next: (res) => {
        alert('Course Updated Successfully');
        this.resetForm();
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
        alert(errMsg);
      },
    });
  }

  deleteCourse() {
    const id = this.selectedCourseId!;
    if (confirm('Delete this course?')) {
      this.courseService.deleteCourse(id).subscribe({
        next: (res) => {
          this.successMessage = res;
          this.cd.detectChanges();
        },
        error: (errMsg) => {
          this.errorMessage = errMsg;
          this.cd.detectChanges();
          alert(errMsg);
        },
      });
      this.loadCourse();
    }
  }
  get isActionDisabled(): boolean {
    if (this.viewMode === 'create') {
      return this.courseForm.invalid;
    }

    return !this.courses?.length || !this.selectedCourseId;
  }

  resetForm() {
    this.selectedCourseId = undefined;
    this.courseForm.reset({
      status: 'ACTIVE',
    });
    this.errorMessage = '';
    this.successMessage = '';
    this.loadCourse();
  }
}
