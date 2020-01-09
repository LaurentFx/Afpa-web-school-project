import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TypeSalleListComponent } from './modules/type-salle/typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './modules/type-salle/typeSalle-add/typeSalle-add.component';
import { HomeComponent} from './home/home.component';
import { RoleListComponent } from './modules/role/role-list/role-list.component';

const routes: Routes = [
  {path:'', pathMatch:'full', component: HomeComponent}, 
   {path: 'typesalle-list', component: TypeSalleListComponent},
  {path: 'typesalle-ad', component: TypeSalleAddComponent},
  {path: 'role', component: RoleListComponent}

  //{path: 'login', component: LoginComponent},

 // {path: 'typesalle-update/:id', component: TypeSalleUpdateComponent},
 // {path: 'typesalle-details/:id', component: TypeSalleDetailsComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
