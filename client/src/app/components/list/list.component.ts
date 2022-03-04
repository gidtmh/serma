import { Component, OnInit } from '@angular/core';
import { Participants } from 'src/app/models/participants.model';
import { ParticipantsService } from 'src/app/services/participants.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  participants?: Participants[];
  currentParticipants: Participants = {};
  currentIndex = -1;
  name = '';
  constructor(private participantsService : ParticipantsService ) { }
  ngOnInit(): void {
    this.retrieveParticipants();
  }
  retrieveParticipants(): void {
    this.participantsService.getAll()
      .subscribe({
        next: (data) => {
          this.participants = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  refreshList(): void {
    this.retrieveParticipants();
    this.currentParticipants = {};
    this.currentIndex = -1;
  }
  setActiveParticipants(participants: Participants, index: number): void {
    this.currentParticipants = participants;
    this.currentIndex = index;
  }
  removeAllParticipants(): void {
    this.participantsService.deleteAll()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
  searchName(): void {
    this.currentParticipants = {};
    this.currentIndex = -1;
    this.participantsService.findByName(this.name)
      .subscribe({
        next: (data) => {
          this.participants = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
