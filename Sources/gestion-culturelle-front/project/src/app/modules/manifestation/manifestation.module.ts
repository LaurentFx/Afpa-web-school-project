import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ManifestationRoutingModule } from './manifestation-routing.module';
import { ManifestationShowComponent } from './manifestation-show/manifestation-show.component';
import { ManifestationAddComponent } from './manifestation-add/manifestation-add.component';
import { ManifestationUpdateComponent } from './manifestation-update/manifestation-update.component';
import { ManifestationListComponent } from './manifestation-list/manifestation-list.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [
    ManifestationShowComponent,
    ManifestationAddComponent,
    ManifestationListComponent,
    ManifestationUpdateComponent],

  imports: [
    CommonModule,
    ManifestationRoutingModule,
    FormsModule,
    FontAwesomeModule
  ],
  exports: [
    ManifestationShowComponent,
    ManifestationAddComponent,
    ManifestationListComponent,
    ManifestationUpdateComponent
  ]
})
export class ManifestationModule { }
