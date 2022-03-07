import {Component} from '@angular/core';
import {AuthService} from "./services/auth/auth.service";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "./models/user.model";
import {Role} from "./models/role.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(private authService: AuthService) {
    this.userSubject = new BehaviorSubject<User>(new User());
    this.user = this.userSubject.asObservable();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  authenticatedUsername(): string | null {
    return this.authService.getAuthenticatedUsername;
  }

  logout() {
    this.authService.logout();
  }

  isAdminRole(): boolean {
    return this.authService.getAuthenticatedUserRole() === Role.Admin;
  }
}
