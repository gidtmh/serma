import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-show-events',
  templateUrl: './show-events.component.html',
  styleUrls: ['./show-events.component.css']
})
export class ShowEventsComponent implements OnInit {
  eventData:any;
   name="";
  constructor(private apiService: ApiServiceService) { }

  ngOnInit() {
    this.viewEvent();
  }
  viewEvent(){
    this.apiService.viewEvent().subscribe((data: Array<object>) => {
     this.eventData=data;
    });
  }

}
