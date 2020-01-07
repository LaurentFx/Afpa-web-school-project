import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NavebarComponent} from './navebar/navebar.component';


const routes: Routes = [
  {path: 'navebar', component: NavebarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
