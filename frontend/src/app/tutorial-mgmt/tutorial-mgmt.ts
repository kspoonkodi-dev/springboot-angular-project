import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Tutorial } from '../Models/tutorial.model';
import { HttpClient } from '@angular/common/http';
import { tutorialService } from '../services/tutorial.service';
import { CourseService } from '../services/course.service';
import { Router } from '@angular/router';

import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CourseTitle } from '../Models/coursetitle.model';

@Component({
  selector: 'app-tutorial-mgmt',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  standalone: true,
  templateUrl: './tutorial-mgmt.html',
  styleUrl: './tutorial-mgmt.css',
})
export class TutorialMgmt {
  tutorialForm!: FormGroup;
  course: CourseTitle[] = [];
  selectedTutorialId?: number;

  viewMode: 'create' | 'update' | 'delete' = 'create';

  tutorial: Tutorial[] = [];
  loading: boolean = false;
  showDropdown: boolean = false;
  error: string = '';
  errorMessage: String = '';
  successMessage: String = '';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private courseService: CourseService,
    private tutorialService: tutorialService,
    private router: Router,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit() {
    this.tutorialForm = this.fb.group({
      courseId: ['', Validators.required],
      tutorial: this.fb.group({
        title: ['', Validators.required],
        content: ['', Validators.required],
        youTubeLink: ['', Validators.required],
      }),
    });
    this.tutorialForm.get('courseId')?.valueChanges.subscribe((value) => {
      console.log('Course changed:', value);
      if (value) {
        this.loadTutorialsByCourse(value);
      }
    });
    this.loadCourses();
  }

  loadCourses() {
    this.courseService.getAllCourseForTutorial().subscribe({
      next: (data) => {
        this.course = data;
        if (this.course.length === 1) {
          this.showDropdown = false;
          // Set value automatically
          this.tutorialForm.get('courseId')?.setValue(this.course[0].courseId);
        } else {
          this.showDropdown = true;
        }
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
      },
    });
  }

  onCourseChange() {
    console.log('OnCourseChange');
    alert('Inside courseChange');
    const courseId = this.tutorialForm.get('courseId')?.value;

    if (courseId) {
      this.loadTutorialsByCourse(+courseId);
    }
  }

  loadTutorialsByCourse(courseId: number) {
    console.log('loadTutorialsByCourse');
    this.tutorialService.getTutorialByCourseId(courseId).subscribe({
      next: (res) => {
        this.tutorial = res;
        alert('getTutorialByCourseId called');
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
      },
    });
  }

  selectTutorial(t: Tutorial) {
    console.log('Selected:', t);

    this.selectedTutorialId = t.tutorialId;

    this.tutorialForm.get('tutorial')?.patchValue({
      title: t.title,
      content: t.content,
      youTubeLink: t.youTubeLink,
    });
  }
  handleAction() {
    if (this.viewMode === 'create') {
      this.createTutorial();
    } else if (this.viewMode === 'update') {
      this.updateTutorial();
    } else {
      this.deleteTutorial();
    }
  }

  createTutorial() {
    console.log(this.tutorialForm.value);

    this.tutorialService.createTutorial(this.tutorialForm.value).subscribe({
      next: (res) => {
        alert('Tutorial Created Successfully');
        this.tutorialForm.reset();
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
      },
    });
  }

  updateTutorial() {
    const tutorialData = this.tutorialForm.get('tutorial')?.value;
    console.log('Sending:', tutorialData);
    this.tutorialService.updateTutorial(this.selectedTutorialId!, tutorialData).subscribe({
      next: (res) => {
        alert('Updated Successfully');
        this.onCourseChange();
        this.resetForm();
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
      },
    });
  }

  deleteTutorial() {
    const id = this.selectedTutorialId!;
    if (confirm('Delete this Tutorial?')) {
      this.tutorialService.deleteTutorial(id).subscribe({
        next: (res) => {
          this.successMessage = res;
          this.cd.detectChanges();
        },
        error: (errMsg) => {
          this.errorMessage = errMsg;
          this.cd.detectChanges();
        },
      });
      this.onCourseChange();
    }
  }
  get isActionDisabled(): boolean {
    if (this.viewMode === 'create') {
      return this.tutorialForm.invalid;
    }

    return !this.tutorial?.length || !this.selectedTutorialId;
  }
  resetForm() {
    this.tutorialForm.reset();
    this.selectedTutorialId = undefined;
    this.errorMessage = '';
    this.successMessage = '';
    // this.onCourseChange();
  }
}
