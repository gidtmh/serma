import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddComponent} from './components/add/add.component';
import {DetailsComponent} from './components/details/details.component';
import {ListComponent} from './components/list/list.component';
import {LoginComponent} from "./components/login/login.component";
import {OverviewComponent} from "./components/overview/overview.component";
import {EmployeeHomeComponent} from "./components/employee-home/employee-home.component";
import {AuthGuard} from './helpers/authguard';
import {Role} from "./models/role.model";

const routes: Routes = [
  /*{path: '', redirectTo: 'participants', pathMatch: 'full'},*/
  {path: 'participants', component: ListComponent},
  {path: 'participants/:id', component: DetailsComponent},
  {path: 'add', component: AddComponent},
  {path: 'login', component: LoginComponent},
  {path: 'overview', component: OverviewComponent, canActivate: [AuthGuard], data: {roles: [Role.Admin]}},
  {path: '', component: EmployeeHomeComponent, canActivate: [AuthGuard], data: {roles: [Role.Employee]}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
