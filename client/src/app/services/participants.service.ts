import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Participants } from '../models/participants.model';

const baseUrl = 'http://localhost:8080/api/participants';

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {

  constructor(private http: HttpClient) { }
  getAll(): Observable<Participants[]> {
    return this.http.get<Participants[]>(baseUrl);
  }

  get(id: any): Observable<Participants> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
  findByName(title: any): Observable<Participants[]> {
    return this.http.get<Participants[]>(`${baseUrl}?name=${name}`);
  }



}
