import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiServiceService } from '../api-service.service';
import { Event } from '../model/Event';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {

  constructor(private apiService: ApiServiceService,private route: Router) { }

  ngOnInit() {
    var username= localStorage.getItem('username');
    if(!username){
      this.route.navigate(['']);
    }
  }

  createEvent(name,loc,date,time){
    var userName=localStorage.getItem("username");
    var event : Event =new Event();
    event.name=name;
    event.location=loc;
    event.date=date;
    event.time=time;
    event.organiser=userName;
    this.apiService.createEvent(event).subscribe((data: Array<object>) => {
      this.route.navigate(['event/view']);
    });

  }

}
