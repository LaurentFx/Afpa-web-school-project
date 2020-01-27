import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CommandeRoutingModule } from './commande-routing.module';
import { CommandeAddComponent } from './commande-add/commande-add.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [CommandeAddComponent],
  imports: [
    CommonModule,
    CommandeRoutingModule,
    FormsModule
  ],
  exports: [
    CommandeAddComponent
  ]
})
export class CommandeModule { }
