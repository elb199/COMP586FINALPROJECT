import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ReturnComponent } from './return/return.component';

export const ROUTES: Routes = [
  { path: '', component: HomeComponent },
  { path: 'callback', component: ReturnComponent },
  { path: '**', redirectTo: '' }
];