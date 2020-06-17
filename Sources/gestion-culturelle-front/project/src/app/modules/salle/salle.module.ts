import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { SalleRoutingModule } from './salle-routing.module';
import { SalleAddComponent } from './salle-add/salle-add.component';
import { SalleListComponent } from './salle-list/salle-list.component';
import { SalleShowComponent } from './salle-show/salle-show.component';
import { SalleUpdateComponent } from './salle-update/salle-update.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    SalleAddComponent,
    SalleListComponent,
    SalleShowComponent,
    SalleUpdateComponent
  ],
  imports: [
    CommonModule,
    SalleRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],
  exports: [
    SalleAddComponent,
    SalleListComponent,
    SalleShowComponent,
    SalleUpdateComponent
  ]
})
export class SalleModule { }
