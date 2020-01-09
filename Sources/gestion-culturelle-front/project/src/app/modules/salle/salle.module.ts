import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { SalleRoutingModule } from './salle-routing.module';

import { SalleAddComponent } from './salle-add/salle-add.component';
import { SalleListComponent } from './salle-list/Salle-list.component';
import { SalleDetailComponent } from './salle-detail/Salle-detail.component';


@NgModule({
  declarations: [
    SalleAddComponent,
    SalleListComponent,
    SalleDetailComponent
  ],
  imports: [
    CommonModule,
    SalleRoutingModule,
    FormsModule
  ]
})
export class SalleModule { }
