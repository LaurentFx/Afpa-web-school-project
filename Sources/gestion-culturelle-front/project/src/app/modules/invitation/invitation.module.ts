import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { InvitationRoutingModule } from './invitation-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InvitationAddComponent } from './invitation-add/invitation-add.component';
import { InvitationListComponent } from './invitation-list/invitation-list.component';
import { InvitationShowComponent } from './invitation-show/invitation-show.component';

@NgModule({
  declarations: [InvitationAddComponent, InvitationListComponent, InvitationShowComponent],
  imports: [
    CommonModule,
    InvitationRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],exports :[
    InvitationAddComponent, InvitationListComponent, InvitationShowComponent
  ]
})
export class InvitationModule { }
