import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent} from './home/home.component';
import { TypeSalleListComponent } from './modules/typesalle/typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './modules/typesalle/typeSalle-add/typeSalle-add.component';
import { TypeSalleUpdateComponent } from './modules/typesalle/typesalle-update/typesalle-update.component';
import { TypeSalleShowComponent } from './modules/typesalle/typesalle-show/typesalle-show.component';
import { SalleListComponent } from './modules/salle/salle-list/salle-list.component';
import { SalleAddComponent } from './modules/salle/salle-add/salle-add.component';
import { SalleUpdateComponent } from './modules/salle/salle-update/salle-update.component';
import { SalleShowComponent } from './modules/salle/salle-show/salle-show.component';

const routes: Routes = [
  {path:'', pathMatch:'full', component: HomeComponent}, 
   {path: 'typesalle-list', component: TypeSalleListComponent},
  {path: 'typesalle-ad', component: TypeSalleAddComponent},
 {path: 'typesalle-update/:id', component: TypeSalleUpdateComponent},
 {path: 'typesalle-show/:id', component: TypeSalleShowComponent},
 {path: 'salle-list', component: SalleListComponent},
 {path: 'salle-ad', component: SalleAddComponent},
 {path: 'salle-update/:id', component: SalleUpdateComponent},
 {path: 'salle-show/:id', component: SalleShowComponent},
   
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
