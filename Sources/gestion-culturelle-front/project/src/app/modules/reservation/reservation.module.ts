import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ReservationRoutingModule } from './reservation-routing.module';
import { ReservationAddComponent } from './reservation-add/reservation-add.component';
import { ReservationShowComponent } from './reservation-show/reservation-show.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [ReservationAddComponent, ReservationShowComponent, ReservationListComponent],
  imports: [
    CommonModule,
    FormsModule,
    FontAwesomeModule,
    ReservationRoutingModule
  ],exports :[
    ReservationAddComponent, ReservationShowComponent, ReservationListComponent
  ]
})
export class ReservationModule { }
