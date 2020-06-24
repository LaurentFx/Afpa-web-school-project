import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReservationAddComponent } from './reservation-add-component/reservation-add-component.component';
import { ReservationShowComponent } from './reservation-show-component/reservation-show-component.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [ReservationAddComponent, ReservationShowComponent],
  imports: [
    CommonModule,
    FormsModule,
    FontAwesomeModule
  ],exports :[
    ReservationAddComponent, ReservationShowComponent
  ]
})
export class ReservationModule { }
