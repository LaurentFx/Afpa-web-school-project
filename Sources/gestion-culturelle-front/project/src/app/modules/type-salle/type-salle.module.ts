import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import {TypeSalleRoutingModule} from './type-salle-routing.module';

import {TypeSalleListComponent} from './typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './typeSalle-add/typeSalle-add.component';
import { TypeSalleDetailComponent } from './typeSalle-detail/typeSalle-detail.component';


@NgModule({
  declarations: [
    TypeSalleListComponent,
    TypeSalleAddComponent,
    TypeSalleDetailComponent
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
  ]
})
export class TypeSalleModule { }
