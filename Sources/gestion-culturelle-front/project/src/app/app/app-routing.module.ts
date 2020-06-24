import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './../modules/login/login/login.component';
import { AuthGuard } from '../security/guard/auth.guard';
import { RespGuard } from '../security/guard/resp.guard';
import { AdminGuard } from '../security/guard/admin.guard';
import { RespadminGuard } from '../security/guard/respadmin.guard';
import { AnimGuard } from '../security/guard/anim.guard';
import { VipGuard } from '../security/guard/vip.guard';
import { ClientGuard } from '../security/guard/client.guard';

import { TypeSalleListComponent } from './../modules/typesalle/typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './../modules/typesalle/typeSalle-add/typeSalle-add.component';
import { TypeSalleUpdateComponent } from './../modules/typesalle/typesalle-update/typesalle-update.component';
import { TypeSalleShowComponent } from './../modules/typesalle/typesalle-show/typesalle-show.component';
import { SalleListComponent } from './../modules/salle/salle-list/salle-list.component';
import { SalleAddComponent } from './../modules/salle/salle-add/salle-add.component';
import { SalleUpdateComponent } from './../modules/salle/salle-update/salle-update.component';
import { SalleShowComponent } from './../modules/salle/salle-show/salle-show.component';
import { AnimationAddComponent } from './../modules/animation/animation-add-component/animation-add.component';
import { AnimationUpdateComponent } from './../modules/animation/animation-update-component/animation-update.component';
import { AnimationListComponent } from './../modules/animation/animation-list-component/animation-list.component';
import { AnimationShowComponent } from './../modules/animation/animation-show-component/animation-show.component';
import { ManifestationListComponent } from './../modules/manifestation/manifestation-list-component/manifestation-list.component';
import { ManifestationAddComponent } from './../modules/manifestation/manifestation-add-component/manifestation-add.component';
import { ManifestationShowComponent } from './../modules/manifestation/manifestation-show-component/manifestation-show.component';
import { ManifestationUpdateComponent } from './../modules/manifestation/manifestation-update-component/manifestation-update.component';
import { PanierAddComponent } from './../modules/panier/panier-add-component/panier-add.component';
import { PanierShowComponent } from './../modules/panier/panier-show-component/panier-show.component';

import { RoleAddComponent } from './../modules/role/role-add/role-add.component';
import { RoleUpdateComponent } from './../modules/role/role-update/role-update.component';
import { RoleShowComponent } from './../modules/role/role-show/role-show.component';
import { RoleListComponent } from './../modules/role/role-list/role-list.component';

import { UserListComponent } from './../modules/user/user-list/user-list.component';
import { UserAddComponent } from './../modules/user/user-add/user-add.component';
import { UserUpdateComponent } from './../modules/user/user-update/user-update.component';
import { UserShowComponent } from './../modules/user/user-show/user-show.component';

import { ProfilShowComponent } from './../modules/profil//profil-show-component/profil-show.component';
import { ProfilUpdateComponent } from './../modules/profil//profil-update-component/profil-update.component';

import { InscriptionComponent } from './../modules/inscription/inscription/inscription.component';

import { InvitationAddComponent } from './../modules/invitation/invitation-add/invitation-add.component';
import { InvitationListComponent } from './../modules/invitation/invitation-list/invitation-list.component';
import { InvitationShowComponent } from './../modules/invitation/invitation-show/invitation-show.component';
import { InvitationAnswerComponent } from './../modules/invitation/invitation-answer/invitation-answer.component';
import { ReservationAddComponent } from '../modules/reservation/reservation-add-component/reservation-add-component.component';
import { ReservationShowComponent } from '../modules/reservation/reservation-show-component/reservation-show-component.component';

const routes: Routes = [
  { path: 'public', pathMatch: 'full', component: HomeComponent },
  { path: 'public/login', component: LoginComponent },

  { path: 'profil-update', component: ProfilUpdateComponent, canActivate: [AuthGuard] },
  { path: 'profil-show', component: ProfilShowComponent, canActivate: [AuthGuard] },
  { path: 'public/inscription', component: InscriptionComponent },

  { path: 'typesalle-list', component: TypeSalleListComponent, canActivate: [AuthGuard] },
  { path: 'typesalle-ad', component: TypeSalleAddComponent, canActivate: [RespGuard] },
  { path: 'typesalle-update/:id', component: TypeSalleUpdateComponent, canActivate: [RespGuard] },
  { path: 'typesalle-show/:id', component: TypeSalleShowComponent, canActivate: [RespGuard] },

  { path: 'public/salle-list', component: SalleListComponent },
  { path: 'salle-ad', component: SalleAddComponent, canActivate: [RespGuard] },
  { path: 'salle-update/:id', component: SalleUpdateComponent, canActivate: [RespGuard] },
  { path: 'salle-show/:id', component: SalleShowComponent, canActivate: [AuthGuard] },

  { path: 'public/manifestation-list', component: ManifestationListComponent },
  { path: 'manifestation-ad', component: ManifestationAddComponent, canActivate: [RespGuard] },
  { path: 'manifestation-update/:id', component: ManifestationUpdateComponent, canActivate: [RespadminGuard] },
  { path: 'manifestation-show/:id', component: ManifestationShowComponent, canActivate: [AuthGuard] },

  { path: 'public/animation-list', component: AnimationListComponent },
  { path: 'animation-ad', component: AnimationAddComponent, canActivate: [AnimGuard] },
  { path: 'animation-update/:id', component: AnimationUpdateComponent, canActivate: [AnimGuard] },
  { path: 'animation-show/:id', component: AnimationShowComponent, canActivate: [AuthGuard] },

  { path: 'panier-show/:id', component: PanierShowComponent, canActivate: [ClientGuard] },
  { path: 'panier-ad/:id', component: PanierAddComponent, canActivate: [ClientGuard] },

  // Inutiles ? 
  { path: 'panier-ad', component: PanierAddComponent, canActivate: [ClientGuard] },
  { path: 'panier-show', component: PanierShowComponent, canActivate: [ClientGuard] },

  { path: 'reservation-ad/:id', component: ReservationAddComponent, canActivate: [ClientGuard] },
  { path: 'reservation-show/:id', component: ReservationShowComponent, canActivate: [ClientGuard] },

  { path: 'invitation-ad/:id', component: InvitationAddComponent, canActivate: [AdminGuard] },
  { path: 'invitation-list', component: InvitationListComponent, canActivate: [RespadminGuard] },
  { path: 'invitation-show/:id', component: InvitationShowComponent, canActivate: [VipGuard] },
  { path: 'invitation-answer/:id', component: InvitationAnswerComponent, canActivate: [VipGuard] },

  { path: 'role-list', component: RoleListComponent, canActivate: [RespGuard] },
  { path: 'role-ad', component: RoleAddComponent, canActivate: [RespGuard] },
  { path: 'role-update/:id', component: RoleUpdateComponent, canActivate: [RespGuard] },
  { path: 'role-show/:id', component: RoleShowComponent, canActivate: [RespGuard] },

  { path: 'user-list', component: UserListComponent, canActivate: [RespGuard] },
  { path: 'user-ad', component: UserAddComponent, canActivate: [RespGuard] },
  { path: 'user-update/:id', component: UserUpdateComponent, canActivate: [RespGuard] },
  { path: 'user-show/:id', component: UserShowComponent, canActivate: [RespGuard] },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
