import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { RoleRoutingModule } from './role-routing.module';

import { RoleAddComponent } from './role-add/role-add.component';
import { RoleDetailComponent } from './role-detail/role-detail.component';
import { RoleListComponent } from './role-list/role-list.component';

@NgModule({
  declarations: [
  RoleAddComponent,
  RoleDetailComponent,
  RoleListComponent
],
  imports: [
    CommonModule,
    RoleRoutingModule,
    FormsModule
  ],
  exports: [
    RoleAddComponent,
    RoleDetailComponent,
    RoleListComponent
  ]
})
export class RoleModule { }
