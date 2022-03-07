import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Employee} from '../../models/employee.model';
import {Router} from "@angular/router";
import {BehaviorSubject, map, Observable} from "rxjs";
import {User} from "../../models/user.model";

const baseUrl = 'http://localhost:8080/api/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;
  private usernameSubject: BehaviorSubject<string>;
  public username: Observable<string>;
  private accessToken: string | null = null;
  private authenticatedUsername: string | null = null;

  constructor(
    private http: HttpClient,
    private router: Router) {
    this.userSubject = new BehaviorSubject<User>(new User());
    this.user = this.userSubject.asObservable();

    this.usernameSubject = new BehaviorSubject<string>('');
    this.username = this.usernameSubject.asObservable();
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${baseUrl}/login`, {username, password}, {withCredentials: true})
      .pipe(map(user => {
        this.userSubject.next(user);
        this.startRefreshTokenTimer();
        this.router.navigate(['/participants']);
        return user;
      }));
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  public get getAuthenticatedUsername(): string | null {
    return this.usernameSubject.value !== '' ? this.authenticatedUsername : localStorage.getItem('authenticatedUsername');
  }

  private refreshTokenTimeout: any;

  private startRefreshTokenTimer() {
    // parse json object from base64 encoded jwt token
    const parsedAccessToken = JSON.parse(atob(this.userValue.accessToken!.split('.')[1]));

    const accessToken = this.userValue.accessToken;
    const refreshToken = this.userValue.refreshToken;

    this.accessToken = this.userValue.accessToken;
    this.authenticatedUsername = this.userValue.username;

    localStorage.setItem('accessToken', this.accessToken);
    localStorage.setItem('authenticatedUsername', this.authenticatedUsername);
    localStorage.setItem('userRole', this.userValue.role);

    this.usernameSubject.next(this.authenticatedUsername);

    // set a timeout to refresh the token a minute before it expires
    const expires = new Date(parsedAccessToken.exp * 1000);
    const timeout = expires.getTime() - Date.now() - (60 * 1000);
    this.refreshTokenTimeout = setTimeout(() => this.refreshToken(refreshToken, accessToken).subscribe(), timeout);
  }

  refreshToken(refreshToken: string, accessToken: string) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${accessToken}`
    });
    let options = {headers: headers, withCredentials: true};
    console.log(`accessToken: ${accessToken}, refreshToken: ${refreshToken}`);
    return this.http.post<any>(`${baseUrl}/refresh-tokens`, {"refreshToken": refreshToken}, options)
      .pipe(map((user) => {
        this.userSubject.next(user);
        this.startRefreshTokenTimer();
        return user;
      }));
  }

  logout() {
    this.stopRefreshTokenTimer();
    localStorage.clear();
    this.userSubject.next(new User());
    this.usernameSubject.next('');
    this.router.navigate(['/login']);
  }

  private stopRefreshTokenTimer() {
    clearTimeout(this.refreshTokenTimeout);
  }

  public isAuthenticated(): boolean {
    return !!this.accessToken || !!localStorage.getItem('accessToken');
  }

  public getAuthenticatedUserRole(): string | null {
    return localStorage.getItem('userRole');
  }

  public getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }
}
