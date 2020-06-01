import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { InvitationRoutingModule } from './invitation-routing.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InvitationAddComponent } from './invitation-add/invitation-add.component';
import { InvitationListComponent } from './invitation-list/invitation-list.component';
import { InvitationShowComponent } from './invitation-show/invitation-show.component';
import { InvitationAnswerComponent } from './invitation-answer/invitation-answer.component';

@NgModule({
  declarations: [InvitationAddComponent, InvitationListComponent, InvitationShowComponent, InvitationAnswerComponent],
  imports: [
    CommonModule,
    InvitationRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],exports :[
    InvitationAddComponent, InvitationListComponent, InvitationShowComponent,  InvitationAnswerComponent
  ]
})
export class InvitationModule { }
