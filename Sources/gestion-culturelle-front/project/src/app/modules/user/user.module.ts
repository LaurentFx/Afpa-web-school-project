import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserListComponent } from './user-list/user-list.component';
import { UserAddComponent } from './user-add/user-add.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    UserListComponent,
    UserAddComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule
  ],
  exports: [
    UserListComponent,
    UserAddComponent,
  ]
})
export class UserModule { }
