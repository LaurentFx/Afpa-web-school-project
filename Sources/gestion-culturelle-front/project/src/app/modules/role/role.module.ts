import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { RoleRoutingModule } from './role-routing.module';

import { RoleAddComponent } from './role-add/role-add.component';
import { RoleListComponent } from './role-list/role-list.component';

@NgModule({
  declarations: [
    RoleAddComponent,
    RoleListComponent
  ],
  imports: [
    CommonModule,
    RoleRoutingModule,
    FormsModule
  ],
  exports: [
    RoleAddComponent,
    RoleListComponent
  ]
})
export class RoleModule { }
