import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  console.log('Interceptor running');
  const token = localStorage.getItem('token');
  console.log('Interceptor running after token');
  console.log('Token from localStorage:', token);
  debugger;
  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  return next(req);
};
