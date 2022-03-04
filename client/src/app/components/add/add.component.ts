import { Component, OnInit } from '@angular/core';
import { Participants } from 'src/app/models/participants.model';
import { ParticipantsService } from 'src/app/services/participants.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  participants: Participants = {
    name: '',
    contact: '',
    attending: false
  };
  submitted = false;
  constructor(private participantsService: ParticipantsService) { }
  ngOnInit(): void {
  }
  saveParticipant(): void {
    const data = {
      name: this.participants.name,
      contact: this.participants.contact
    };
    this.participantsService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }
  newParticipant(): void {
    this.submitted = false;
    this.participants = {
      name: '',
      contact: '',
      attending: false
    };
  }
}
