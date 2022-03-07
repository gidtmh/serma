import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from "rxjs";
import {AuthService} from "../auth/auth.service";

const baseUrl = 'http://localhost:8080/api/events';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(
    private http: HttpClient,
    private authService: AuthService) {
  }

  getAll() {
    let headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getAccessToken()}`
    });
    let options = {headers: headers, withCredentials: true};

    return this.http.get<any>(`${baseUrl}`, options)
      .pipe(map(events => {
        return events;
      }));
  }

  getAllUpcoming() {
    let headers = new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getAccessToken()}`
    });
    let options = {headers: headers, withCredentials: true};

    return this.http.get<any>(`${baseUrl}/upcoming`, options)
      .pipe(map(events => {
        return events;
      }));
  }
}
