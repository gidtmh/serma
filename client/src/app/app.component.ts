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
    this.route.navigate(['event/create']);
  }
  createSearch(){
    this.route.navigate(['']);
  }
  find(){
    this.route.navigate(['find']);
    
  }
}
