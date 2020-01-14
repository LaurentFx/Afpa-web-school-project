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
import { AnimationAddComponent } from './modules/animation/animation-add-component/animation-add.component';
import { AnimationUpdateComponent } from './modules/animation/animation-update-component/animation-update.component';
import { AnimationListComponent } from './modules/animation/animation-list-component/animation-list.component';
import { AnimationShowComponent } from './modules/animation/animation-show-component/animation-show.component';
import { ManifestationListComponent } from './modules/manifestation/manifestation-list-component/manifestation-list.component';
import { ManifestationAddComponent } from './modules/manifestation/manifestation-add-component/manifestation-add.component';
import { ManifestationShowComponent } from './modules/manifestation/manifestation-show-component/manifestation-show.component';
import { ManifestationUpdateComponent } from './modules/manifestation/manifestation-update-component/manifestation-update.component';
import { PanierListComponent } from './modules/panier/panier-list-component/panier-list.component';
import { PanierAddComponent } from './modules/panier/panier-add-component/panier-add.component';
import { PanierUpdateComponent } from './modules/panier/panier-update-component/panier-update.component';
import { PanierShowComponent } from './modules/panier/panier-show-component/panier-show.component';
import { RoleAddComponent } from './modules/role/role-add/role-add.component';
import { RoleUpdateComponent } from './modules/role/role-update/role-update.component';
import { RoleShowComponent } from './modules/role/role-show/role-show.component';
import { RoleListComponent } from './modules/role/role-list/role-list.component';


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

 {path: 'manifestation-list', component: ManifestationListComponent},
 {path: 'manifestation-ad', component: ManifestationAddComponent},
 {path: 'manifestation-update/:id', component: ManifestationUpdateComponent},
 {path: 'manifestation-show/:id', component: ManifestationShowComponent},

 {path: 'animation-list', component: AnimationListComponent},
 {path: 'animation-ad', component: AnimationAddComponent},
 {path: 'animation-update/:id', component: AnimationUpdateComponent},
 {path: 'animation-show/:id', component: AnimationShowComponent},

 {path: 'panier-list', component: PanierListComponent},
 {path: 'panier-ad', component: PanierAddComponent},
 {path: 'panier-update/:id', component: PanierUpdateComponent},
 {path: 'panier-show/:id', component: PanierShowComponent},
 
 {path: 'role-list', component: RoleListComponent},
 {path: 'role-ad', component: RoleAddComponent},
 {path: 'role-update/:id', component: RoleUpdateComponent},
 {path: 'role-show/:id', component: RoleShowComponent},
   
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing:true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
