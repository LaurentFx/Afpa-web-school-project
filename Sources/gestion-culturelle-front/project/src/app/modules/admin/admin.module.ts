import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminAddComponent } from './admin-add/admin-add.component';
import { AdminListComponent } from './admin-list/admin-list.component';
import { AdminUpdateComponent } from './admin-update/admin-update.component';
import { AdminShowComponent } from './admin-show/admin-show.component';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';


@NgModule({
  declarations: [
    AdminAddComponent,
     AdminListComponent,
      AdminUpdateComponent,
       AdminShowComponent],

  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
    

  ]
})
export class AdminModule { }
