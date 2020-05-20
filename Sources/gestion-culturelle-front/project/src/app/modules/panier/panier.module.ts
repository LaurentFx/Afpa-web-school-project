import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PanierRoutingModule } from './panier-routing.module';
import { PanierAddComponent } from './panier-add-component/panier-add.component';
// import { PanierUpdateComponent } from './panier-update-component/panier-update.component';
import { PanierShowComponent } from './panier-show-component/panier-show.component';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [
    PanierAddComponent,
  //  PanierUpdateComponent,
    PanierShowComponent
  ],
  imports: [
    CommonModule,
    PanierRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],
  exports: [
    PanierAddComponent,
  //  PanierUpdateComponent,
    PanierShowComponent
  ]
})
export class PanierModule { }
