import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SalleRoutingModule } from './salle-routing.module';

import { SalleAddComponent } from './salle-add/salle-add.component';
import { SalleListComponent } from './salle-list/Salle-list.component';

@NgModule({
  declarations: [
    SalleAddComponent,
    SalleListComponent,
  ],
  imports: [
    CommonModule,
    SalleRoutingModule,
    FormsModule
  ]
})
export class SalleModule { }
