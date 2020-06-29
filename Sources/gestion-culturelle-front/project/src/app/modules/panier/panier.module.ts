import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PanierRoutingModule } from './panier-routing.module';
/* import { PanierAddComponent } from './panier-add-component/panier-add.component';
import { PanierShowComponent } from './panier-show-component/panier-show.component'; */
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
   /*  PanierAddComponent,
    PanierShowComponent */
  ],
  imports: [
    CommonModule,
    PanierRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],
  exports: [
   /*  PanierAddComponent,
    PanierShowComponent */
  ]
})
export class PanierModule { }
