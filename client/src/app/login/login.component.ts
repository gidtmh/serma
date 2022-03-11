import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'ngx-alerts';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
   showRegister = false;
   showLogin=true;
   type:'A';
  constructor(private apiService: ApiServiceService,private alertService: AlertService,private route: Router) { }

  ngOnInit() {
    var username=localStorage.getItem('username');
    if(username){
      this.route.navigate(['event/view']);
    }
  }
  showingRegister(){
    this.showRegister=true;
    this.showLogin=false;
  }
  showingLogin(){
    this.showRegister=false;
    this.showLogin=true;
  }
  signup(username,password){
    this.apiService.signUp(username,password,this.type).subscribe((data:  Array<object>) => {
      this.alertService.success("Registration Success, Please login!!");
      this.showingLogin();
      
    });
  }
  changeType(val){
    this.type=val;
  }
  login(username,password){
    this.apiService.login(username,password).subscribe((data: Array<object>) => {
      this.alertService.success("login success");
      localStorage.setItem("token", data['jwt']);
      localStorage.setItem("username", username);
      this.route.navigate(['event/view']);
    }, error => {
      this.alertService.danger("Login Failed !!");  
    })

  }
}
