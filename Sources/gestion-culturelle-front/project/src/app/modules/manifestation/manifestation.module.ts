import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManifestationRoutingModule } from './manifestation-routing.module';
import { ManifestationAddComponent } from './manifestation-add/manifestation-add.component';
import { ManifestationListComponent } from './manifestation-list/manifestation-list.component';
import { ManifestationShowComponent } from './manifestation-show/manifestation-show.component';
import { ManifestationUpdateComponent } from './manifestation-update/manifestation-update.component';

@NgModule({
  declarations: [ManifestationAddComponent,
    ManifestationListComponent,
    ManifestationShowComponent, ManifestationUpdateComponent
  ],
  imports: [
    CommonModule,
    ManifestationRoutingModule
  ]
})
export class ManifestationModule { }
