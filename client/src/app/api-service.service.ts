import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './model/User';
import { AuthenticationRequest } from './model/AuthenticationRequest';
@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor(private  httpClient:  HttpClient) {
    
  }
  baseUrl="http://68.183.190.237:8080/api";

  signUp(userId,pass,type){
    let headers = new Headers();
       headers.append('Content-Type','application/json');
       const options = {headers: {'Content-Type': 'application/json'}};
       var user: User =new User();
       user.userId=userId;
       user.password=pass;
       user.role=type;
      return this.httpClient.post(this.baseUrl+'/signUp',JSON.stringify(user),options);
}
login(username,pass){
  let headers = new Headers();
  headers.append('Content-Type','application/json');
  const options = {headers: {'Content-Type': 'application/json'}};
  var auth: AuthenticationRequest =new AuthenticationRequest();
  auth.username=username;
  auth.password=pass;
 return this.httpClient.post(this.baseUrl+'/authenticate',JSON.stringify(auth),options);
}
createEvent(event){
  let headers = new Headers();
  headers.append('Content-Type','application/json');
  const options = {headers: {'Content-Type': 'application/json','Authorization':'Bearer '+localStorage.getItem('token')}};
  return this.httpClient.post(this.baseUrl+'/event',JSON.stringify(event),options)
}
viewEvent(){
  let headers = new Headers();
  headers.append('Content-Type','application/json');
  const options = {headers: {'Content-Type': 'application/json','Authorization':'Bearer '+localStorage.getItem('token')}};
  return this.httpClient.get(this.baseUrl+'/event?id=-1',options)
}
randomApi(){
  return this.httpClient.get(this.baseUrl+'/random');
}
}
