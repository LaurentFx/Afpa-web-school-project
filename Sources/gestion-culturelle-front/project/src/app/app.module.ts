import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TypeSalleListComponent } from './TypeSalle-list/TypeSalle-list.component';
import { TypeSalleDetailComponent } from './TypeSalle-detail/TypeSalle-detail.component';
import { TypeSalleAddComponent } from './TypeSalle-add/TypeSalle-add.component';

import { RoleListComponent } from './Role-list/Role-list.component';
import { RoleDetailComponent } from './Role-detail/role-detail.component';
import { RoleAddComponent } from './Role-add/role-add.component';

import { NavebarComponent } from './navebar/navebar.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    TypeSalleListComponent,
    TypeSalleDetailComponent,
    TypeSalleAddComponent,

    RoleListComponent,
    RoleDetailComponent,
    RoleAddComponent,

    NavebarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot ([
    
    {path: 'typesalle-list', component:TypeSalleListComponent },
    {path: 'typesalle-add', component:TypeSalleAddComponent },
    ]),
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
