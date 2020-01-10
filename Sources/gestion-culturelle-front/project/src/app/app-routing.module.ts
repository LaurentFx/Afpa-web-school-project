import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TypeSalleListComponent } from './modules/typesalle/typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './modules/typesalle/typeSalle-add/typeSalle-add.component';
import { HomeComponent} from './home/home.component';
import { TypeSalleUpdateComponent } from './modules/typesalle/typesalle-update/typesalle-update.component';
import { TypeSalleShowComponent } from './modules/typesalle/typesalle-show/typesalle-show.component';

const routes: Routes = [
  {path:'', pathMatch:'full', component: HomeComponent}, 
   {path: 'typesalle-list', component: TypeSalleListComponent},
  {path: 'typesalle-ad', component: TypeSalleAddComponent},
 {path: 'typesalle-update/:id', component: TypeSalleUpdateComponent},
 {path: 'typesalle-show/:id', component: TypeSalleShowComponent},
   
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
