import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import {TypeSalleRoutingModule} from './type-salle-routing.module';

import {TypeSalleListComponent} from './typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './typeSalle-add/typeSalle-add.component';
import { TypeSalleDetailComponent } from './typeSalle-detail/typeSalle-detail.component';
import { TypeSalleUpdateComponent } from './type-salle-update/type-salle-update.component';
import { TypeSalleShowComponent } from './type-salle-show/type-salle-show.component';


@NgModule({
  declarations: [
    TypeSalleListComponent,
    TypeSalleAddComponent,
    TypeSalleDetailComponent,
    TypeSalleUpdateComponent,
    TypeSalleShowComponent
  ],
  imports: [
    CommonModule,
    TypeSalleRoutingModule,
    FormsModule
  ],
  exports: [
  TypeSalleListComponent,
  TypeSalleAddComponent,
  TypeSalleDetailComponent,
  TypeSalleUpdateComponent,
   TypeSalleShowComponent
  ]
})
export class TypeSalleModule { }
