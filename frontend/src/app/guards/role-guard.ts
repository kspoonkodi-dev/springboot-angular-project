import { CanActivateFn, Router } from '@angular/router';
import { Role } from '../Models/role.enum';
import { inject } from '@angular/core';

export const roleGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const token = localStorage.getItem('token');
  const userRole = localStorage.getItem('role') as Role;
  const expectedRole = route.data?.['role'] as Role;

  console.log('token', token);
  console.log('userRole', userRole);
  console.log('expectedRole', expectedRole);

  if (!token) {
    router.navigateByUrl('/login');
    return false;
  }
  if (!expectedRole) {
    console.error('No role defined in route data');
    router.navigateByUrl('/login');
    return false;
  }

  if (userRole === expectedRole) {
    return true;
  }
  console.log('expected role', expectedRole);

  /*if (userRole === Role.ADMIN) {
    router.navigateByUrl('/admin-dashboard');
    return true;
  } else if (userRole === Role.INSTRUCTOR) {
    router.navigateByUrl('/instructor-dashboard');
    return true;
  }*/

  router.navigate(['login']);
  return false;
};
