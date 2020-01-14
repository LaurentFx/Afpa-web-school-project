import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PanierRoutingModule } from './panier-routing.module';
import { PanierComponent } from './panier/panier.component';
import { PanierListComponent } from './panier-list-component/panier-list.component';
import { PanierAddComponent } from './panier-add-component/panier-add.component';
import { PanierUpdateComponent } from './panier-update-component/panier-update.component';
import { PanierShowComponent } from './panier-show-component/panier-show.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    PanierComponent,
     PanierListComponent,
      PanierAddComponent,
       PanierUpdateComponent,
        PanierShowComponent],
  imports: [
    CommonModule,
    PanierRoutingModule,
    FormsModule
  ]
})
export class PanierModule { }
