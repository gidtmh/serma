import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateEventComponent } from './create-event/create-event.component';
import { FindUsComponent } from './find-us/find-us.component';
import { LoginComponent } from './login/login.component';
import { PublicEventComponent } from './public-event/public-event.component';
import { ShowEventsComponent } from './show-events/show-events.component';


const routes: Routes = [

  {
    path: '',
    component: LoginComponent
    // loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'event/create',
    component: CreateEventComponent
    // loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'event/view',
    component: ShowEventsComponent
    // loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  
  {
    path: 'event/public',
    component: PublicEventComponent
    // loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'find',
    component: FindUsComponent
    // loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

 }
