import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';

import {AuthService} from "../services/auth/auth.service";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const userRole = this.authService.getAuthenticatedUserRole();
    if (userRole) {
      // check if route is restricted by role
      if (route.data['roles'] && route.data['roles'].indexOf(userRole) === -1) {
        // role not authorised so redirect to home page
        this.router.navigate(['/participants']);
        return false;
      }

      // authorised so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login']);
    return false;
  }
}
