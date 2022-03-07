import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AddComponent} from './components/add/add.component';
import {LoginComponent} from './components/login/login.component';
import {DetailsComponent} from './components/details/details.component';
import {ListComponent} from './components/list/list.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatListModule} from '@angular/material/list';
import {MatSelectModule} from '@angular/material/select';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {OverviewComponent} from "./components/overview/overview.component";
import {MatOptionModule} from "@angular/material/core";
import {EmployeeHomeComponent} from "./components/employee-home/employee-home.component";

@NgModule({
  declarations: [
    AppComponent,
    AddComponent,
    LoginComponent,
    DetailsComponent,
    ListComponent,
    OverviewComponent,
    EmployeeHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    MatListModule,
    MatSelectModule,
    MatOptionModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
