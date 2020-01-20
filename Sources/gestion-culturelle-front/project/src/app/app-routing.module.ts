import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './modules/login/login/login.component';
import { AuthGuard } from './guard/auth.guard';

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
import { AdminListComponent } from './modules/admin/admin-list/admin-list.component';
import { AdminAddComponent } from './modules/admin/admin-add/admin-add.component';
import { AdminUpdateComponent } from './modules/admin/admin-update/admin-update.component';
import { AdminShowComponent } from './modules/admin/admin-show/admin-show.component';
import { VipListComponent } from './modules/vip/vip-list/vip-list.component';
import { VipAddComponent } from './modules/vip/vip-add/vip-add.component';
import { VipUpdateComponent } from './modules/vip/vip-update/vip-update.component';
import { VipShowComponent } from './modules/vip/vip-show/vip-show.component';
import { UserListComponent } from './modules/user/user-list/user-list.component';
import { UserAddComponent } from './modules/user/user-add/user-add.component';
import { UserUpdateComponent } from './modules/user/user-update/user-update.component';
import { UserShowComponent } from './modules/user/user-show/user-show.component';



const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'login', component: LoginComponent },

  { path: 'typesalle-list', component: TypeSalleListComponent },
  { path: 'typesalle-ad', component: TypeSalleAddComponent, canActivate: [AuthGuard] },
  { path: 'typesalle-update/:id', component: TypeSalleUpdateComponent, canActivate: [AuthGuard] },
  { path: 'typesalle-show/:id', component: TypeSalleShowComponent, canActivate: [AuthGuard] },

  { path: 'salle-list', component: SalleListComponent },
  { path: 'salle-ad', component: SalleAddComponent, canActivate: [AuthGuard] },
  { path: 'salle-update/:id', component: SalleUpdateComponent, canActivate: [AuthGuard] },
  { path: 'salle-show/:id', component: SalleShowComponent, canActivate: [AuthGuard] },

  { path: 'manifestation-list', component: ManifestationListComponent },
  { path: 'manifestation-ad', component: ManifestationAddComponent, canActivate: [AuthGuard] },
  { path: 'manifestation-update/:id', component: ManifestationUpdateComponent, canActivate: [AuthGuard] },
  { path: 'manifestation-show/:id', component: ManifestationShowComponent, canActivate: [AuthGuard] },

  { path: 'animation-list', component: AnimationListComponent },
  { path: 'animation-ad', component: AnimationAddComponent, canActivate: [AuthGuard] },
  { path: 'animation-update/:id', component: AnimationUpdateComponent, canActivate: [AuthGuard] },
  { path: 'animation-show/:id', component: AnimationShowComponent, canActivate: [AuthGuard] },

  { path: 'panier-list', component: PanierListComponent , canActivate: [AuthGuard] },
  { path: 'panier-ad', component: PanierAddComponent, canActivate: [AuthGuard] },
  { path: 'panier-update/:id', component: PanierUpdateComponent, canActivate: [AuthGuard] },
  { path: 'panier-show/:id', component: PanierShowComponent, canActivate: [AuthGuard] },

  { path: 'role-list', component: RoleListComponent, canActivate: [AuthGuard] },
  { path: 'role-ad', component: RoleAddComponent, canActivate: [AuthGuard] },
  { path: 'role-update/:id', component: RoleUpdateComponent, canActivate: [AuthGuard] },
  { path: 'role-show/:id', component: RoleShowComponent, canActivate: [AuthGuard] },

  { path: 'admin-list', component: AdminListComponent, canActivate: [AuthGuard] },
  { path: 'admin-ad', component: AdminAddComponent, canActivate: [AuthGuard] },
  { path: 'admin-update/:id', component: AdminUpdateComponent, canActivate: [AuthGuard] },
  { path: 'admin-show/:id', component: AdminShowComponent, canActivate: [AuthGuard] },

  { path: 'user-list', component: UserListComponent, canActivate: [AuthGuard] },
  { path: 'user-ad', component: UserAddComponent, canActivate: [AuthGuard] },
  { path: 'user-update/:id', component: UserUpdateComponent, canActivate: [AuthGuard] },
  { path: 'user-show/:id', component: UserShowComponent, canActivate: [AuthGuard] },

  {path: 'panier-list', component: PanierListComponent},
  {path: 'panier-ad', component: PanierAddComponent},
  {path: 'panier-update/:id', component: PanierUpdateComponent},
  {path: 'panier-show/:id', component: PanierShowComponent},
 
  {path: 'role-list', component: RoleListComponent},
  {path: 'role-ad', component: RoleAddComponent},
  {path: 'role-update/:id', component: RoleUpdateComponent},
  {path: 'role-show/:id', component: RoleShowComponent},
  
  {path: 'admin-list', component: AdminListComponent},
  {path: 'admin-ad', component: AdminAddComponent},
  {path: 'admin-update/:id', component: AdminUpdateComponent},
  {path: 'admin-show/:id', component: AdminShowComponent},

  {path: 'vip-list', component: VipListComponent},
  {path: 'vip-add', component: VipAddComponent},
  {path: 'vip-show/:id', component: VipShowComponent},
  {path: 'vip-update/:id', component: VipUpdateComponent}
   


];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
