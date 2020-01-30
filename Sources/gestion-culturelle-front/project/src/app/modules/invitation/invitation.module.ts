import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { InvitationRoutingModule } from './invitation-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InvitationAddComponent } from './invitation-add/invitation-add.component';

@NgModule({
  declarations: [InvitationAddComponent],
  imports: [
    CommonModule,
    InvitationRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],exports :[
    InvitationAddComponent
  ]
})
export class InvitationModule { }
