import {Component, OnInit} from '@angular/core';
import {EventService} from "../../services/event/event.service";

@Component({
  selector: 'employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent implements OnInit {
  selected: any = '';

  upcomingEvents:any = [];

  ngOnInit(): void {
    this.getAllEvents();
  }

  constructor(
    private eventService: EventService) {
  }

  getAllEvents() {
    this.eventService.getAllUpcoming().subscribe(upcomingEvents => {
      this.upcomingEvents = upcomingEvents;
    })
  }
}
