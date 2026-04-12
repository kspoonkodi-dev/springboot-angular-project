import { provideRouter, Routes } from '@angular/router';
import { UserCreate } from './user-create/user-create';
import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app';
import { roleGuard } from './guards/role-guard';
import { Role } from './Models/role.enum';
import { Login } from './login/login';
import { InstructorDashboard } from './instructor-dashboard/instructor-dashboard';
import { CourseMgmt } from './course-mgmt/course-mgmt';
import { TutorialMgmt } from './tutorial-mgmt/tutorial-mgmt';
import { AdminDashboard } from './admin-dashboard/admin-dashboard';
import { Approvals } from './admin-dashboard/approvals/approvals';
import { ActivityMonitor } from './admin-dashboard/activity-monitor/activity-monitor';
import { UPLWebsite } from './uplwebsite/uplwebsite';

export const routes: Routes = [
  { path: '', redirectTo: 'uplwebsite', pathMatch: 'full' },
  {
    path: 'uplwebsite',
    component: UPLWebsite,
  },

  {
    path: 'instructors/register',
    component: UserCreate,
  },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'instructor-dashboard',
    component: InstructorDashboard,
    children: [
      { path: 'courses', component: CourseMgmt },
      { path: 'tutorial', component: TutorialMgmt },
      { path: '', redirectTo: 'courses', pathMatch: 'full' },
    ],
    canActivate: [roleGuard],
    data: { role: Role.INSTRUCTOR },
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboard,
    children: [
      { path: 'approvals', component: Approvals },
      { path: 'activity-Monitor', component: ActivityMonitor },
      { path: '', redirectTo: 'approvals', pathMatch: 'full' },
    ],
    canActivate: [roleGuard],
    data: { role: Role.ADMIN },
  },
];

export const AppRoutes = provideRouter(routes);

bootstrapApplication(App, {
  providers: [AppRoutes],
});
