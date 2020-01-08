import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TypeSalleListComponent } from './typeSalle-list/typeSalle-list.component';
import { TypeSalleDetailComponent } from './typeSalle-detail/typeSalle-detail.component';
import { TypeSalleAddComponent } from './typeSalle-add/typeSalle-add.component';

import { RoleListComponent } from './role-list/role-list.component';
import { RoleDetailComponent } from './role-detail/role-detail.component';
import { RoleAddComponent } from './role-add/role-add.component';

import { NavebarComponent } from './navebar/navebar.component';
import { RouterModule } from '@angular/router';
import { AppHeaderComponent } from './app-header/app-header.component';
import { AppFooterComponent } from './app-footer/app-footer.component';


@NgModule({
  declarations: [
    AppComponent,
    TypeSalleListComponent,
    TypeSalleDetailComponent,
    TypeSalleAddComponent,

    RoleListComponent,
    RoleDetailComponent,
    RoleAddComponent,

    NavebarComponent,

    AppHeaderComponent,

    AppFooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
