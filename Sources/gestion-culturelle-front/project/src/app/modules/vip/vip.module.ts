import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VipRoutingModule } from './vip-routing.module';
import { VipListComponent } from './vip-list/vip-list.component';
import { VipAddComponent } from './vip-add/vip-add.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    VipListComponent, 
    VipAddComponent],
  imports: [
    CommonModule,
    VipRoutingModule,
    FormsModule
  ]
})
export class VipModule { }
