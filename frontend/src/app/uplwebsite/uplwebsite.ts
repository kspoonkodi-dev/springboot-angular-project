import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Course } from '../Models/course.model';
import { CourseService } from '../services/course.service';
import { CommonModule } from '@angular/common';
import { CourseTitle } from '../Models/coursetitle.model';
import { Tutorial } from '../Models/tutorial.model';
import { tutorialService } from '../services/tutorial.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-uplwebsite',
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  standalone: true,
  templateUrl: './uplwebsite.html',
  styleUrl: './uplwebsite.css',
})
export class UPLWebsite {
  navItems: CourseTitle[] = [];
  menuList: Tutorial[] = [];
  errorMessage: String = '';
  courseId?: number;
  selectedTutorial: any;
  selectedCourseId?: number;
  isDropdownVisible: boolean = false;
  youTubeLink!: SafeResourceUrl;

  constructor(
    private courseService: CourseService,
    private tutorialService: tutorialService,
    private router: Router,
    private cd: ChangeDetectorRef,
    private sanitizer: DomSanitizer,
  ) {}

  ngOnInit() {
    this.courseService.getAllUserCourses().subscribe({
      next: (res) => {
        this.navItems = res;
        this.cd.detectChanges();
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
      },
    });
  }

  loadTutorials(courseId: number) {
    this.selectedCourseId = courseId;
    this.isDropdownVisible = true;
    this.tutorialService.getAllTutorials(courseId).subscribe({
      next: (res) => {
        this.menuList = res;
        this.cd.detectChanges();
      },
      error: (errMsg) => {
        this.errorMessage = errMsg;
        this.cd.detectChanges();
      },
    });
  }

  loadContent(tutorial: any) {
    this.selectedTutorial = tutorial;
    this.isDropdownVisible = false;
    this.selectedCourseId = undefined;
    this.setVideo(tutorial.youTubeLink);
    this.cd.detectChanges();
  }

  register() {
    this.router.navigateByUrl('instructors/register');
  }

  login() {
    this.router.navigateByUrl('login');
  }
  setVideo(url: string) {
    const videoId = url.split('v=')[1]?.split('&')[0];

    const embedUrl = 'https://www.youtube.com/embed/' + videoId;

    this.youTubeLink = this.sanitizer.bypassSecurityTrustResourceUrl(embedUrl);
  }
}
