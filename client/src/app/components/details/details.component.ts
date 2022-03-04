import { Component, Input, OnInit } from '@angular/core';
import { Participants } from 'src/app/models/participants.model';
import { ParticipantsService } from 'src/app/services/participants.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  @Input() viewMode = false;
  @Input() currentParticipants: Participants= {
    name: '',
    contact: '',
    attending: false
  };

  message = '';
  constructor(
    private participantsService: ParticipantsService,
    private route: ActivatedRoute,
    private router: Router) { }
  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getParticipants(this.route.snapshot.params["id"]);
    }
  }
  getParticipants(id: string): void {
    this.participantsService.get(id)
      .subscribe({
        next: (data) => {
          this.currentParticipants = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
  updateAttending(status: boolean): void {
    const data = {
      name: this.currentParticipants.name,
      contact: this.currentParticipants.contact,
      attending: status
    };
    this.message = '';
    this.participantsService.update(this.currentParticipants.id, data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.currentParticipants.attending= status;
          this.message = res.message ? res.message : 'The status was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  updateParticipants(): void {
    this.message = '';
    this.participantsService.update(this.currentParticipants.id, this.currentParticipants)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'Participant was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }
  deleteParticipants(): void {
    this.participantsService.delete(this.currentParticipants.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/participants']);
        },
        error: (e) => console.error(e)
      });
  }
}
