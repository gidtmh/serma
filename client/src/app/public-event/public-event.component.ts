import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../api-service.service';

@Component({
  selector: 'app-public-event',
  templateUrl: './public-event.component.html',
  styleUrls: ['./public-event.component.css']
})
export class PublicEventComponent implements OnInit {
  eventData:any;
  name="";
  constructor(private apiService: ApiServiceService) { }

  ngOnInit() {
    this.randomApi();
  }

  randomApi(){
    this.apiService.randomApi().subscribe((data: Array<object>) => {
      this.eventData=data;
      console.log(data);
     });
  }

}
