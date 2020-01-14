import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoleAddComponent } from './role-add/role-add.component';
import { RoleListComponent } from './role-list/role-list.component';
import { RoleUpdateComponent } from './role-update/role-update.component';
import { RoleShowComponent } from './role-show/role-show.component';
import { FormsModule } from '@angular/forms';
import { RoleRoutingModule } from './role-routing.module';



@NgModule({
  declarations: [
    RoleAddComponent,
    RoleListComponent,
    RoleUpdateComponent,
    RoleShowComponent],
  imports: [
    FormsModule,
    RoleRoutingModule,
    CommonModule
  ]
})
export class RoleModule { }
