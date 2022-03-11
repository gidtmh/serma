import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-find-us',
  templateUrl: './find-us.component.html',
  styleUrls: ['./find-us.component.css']
})
export class FindUsComponent implements OnInit {
  title = 'My first AGM project';
  lat = 1.3421;
  lng = 103.76;
  constructor() { }

  ngOnInit() {
  }
  
  
}
