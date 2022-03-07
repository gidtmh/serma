import {Component, OnInit} from '@angular/core';
import {EventService} from "../../services/event/event.service";

@Component({
  selector: 'overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements OnInit {
  selectedEvent: any = '';

  events: any = [];

  ngOnInit(): void {
    this.getAllEvents();
  }

  constructor(
    private eventService: EventService) {
  }

  getAllEvents() {
    this.eventService.getAll().subscribe(events => {
      this.events = events;
    })
  }
}
