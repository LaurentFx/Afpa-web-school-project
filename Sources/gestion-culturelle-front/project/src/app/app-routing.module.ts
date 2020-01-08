import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NavebarComponent} from './navebar/navebar.component';
import { TypeSalleListComponent } from './typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './typeSalle-add/typeSalle-add.component';


const routes: Routes = [
  {path:'', redirectTo: '', pathMatch:'full'}, 
  {path: 'navebar', component: NavebarComponent},
  {path: 'typesalle-list', component: TypeSalleListComponent},
  {path: 'typesalle-ad', component: TypeSalleAddComponent},
 // {path: 'typesalle-update/:id', component: TypeSalleUpdateComponent},
 // {path: 'typesalle-details/:id', component: TypeSalleDetailsComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
