import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'eventApp';
  username;
  ngOnInit(): void {
    this.username= localStorage.getItem('username');
  }
  constructor(private route: Router){

  }
  logout(){
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    this.route.navigate(['']);
  }
  createEvent(){
    this.username= localStorage.getItem('username');
    this.route.navigate(['event/create']);
  }
  createSearch(){
    this.username= localStorage.getItem('username');
    this.route.navigate(['']);
  }
  publicEvent(){
    this.username= localStorage.getItem('username');
    this.route.navigate(['event/public']);
  }
  find(){
    this.username= localStorage.getItem('username');
    this.route.navigate(['find']);
    
  }
}
