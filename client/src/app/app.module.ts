import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { ShowEventsComponent } from './show-events/show-events.component';
import { HttpClientModule } from '@angular/common/http';
import { AlertModule } from 'ngx-alerts';
import { SearchPipe } from './SearchPipe';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { FindUsComponent } from './find-us/find-us.component';
import { PublicEventComponent } from './public-event/public-event.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateEventComponent,
    ShowEventsComponent,
    SearchPipe,
    FindUsComponent,
    PublicEventComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AlertModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCT93RYIhxmsuhORTnq3p7IS-cjwwxeZAA'
    }),
    AlertModule.forRoot({maxMessages: 5, timeout: 5000, positionX: 'right'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
