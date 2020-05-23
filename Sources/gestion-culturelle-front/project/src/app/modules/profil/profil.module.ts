import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ProfilRoutingModule } from './profil-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ProfilShowComponent } from './profil-show-component/profil-show.component';
import { ProfilUpdateComponent } from './profil-update-component/profil-update.component';


@NgModule({
  declarations: [
    ProfilShowComponent,
    ProfilUpdateComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProfilRoutingModule,
    FontAwesomeModule
  ],
  exports: [
    ProfilShowComponent,
    ProfilUpdateComponent
  ]
})
export class ProfilModule { }
