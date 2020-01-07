import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NavebarComponent} from './navebar/navebar.component';
import { TypeSalleListComponent } from './TypeSalle-list/TypeSalle-list.component';


const routes: Routes = [
  {path: 'navebar', component: NavebarComponent},
  {path: 'typesalle-list', component: TypeSalleListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
