import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TypeSalleDetailComponent } from './modules/typeSalle/typeSalle-detail/typeSalle-detail.component';
import { TypeSalleListComponent } from './modules/typeSalle/typeSalle-list/typeSalle-list.component';
import { TypeSalleAddComponent } from './modules/typeSalle/typeSalle-add/typeSalle-add.component';

import { SalleDetailComponent } from './modules/Salle/Salle-detail/Salle-detail.component';
import { SalleAddComponent } from './modules/Salle/Salle-add/Salle-add.component';
import { SalleListComponent } from './modules/Salle/Salle-list/Salle-list.component';

import { RoleListComponent } from './modules/role/role-list/role-list.component';
import { RoleDetailComponent } from './modules/role/role-detail/role-detail.component';
import { RoleAddComponent } from './modules/role/role-add/role-add.component';

import { NavebarComponent } from './navebar/navebar.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    TypeSalleListComponent,
    TypeSalleDetailComponent,
    TypeSalleAddComponent,

    SalleDetailComponent,
    SalleAddComponent,
    SalleListComponent,

    RoleListComponent,
    RoleDetailComponent,
    RoleAddComponent,

    NavebarComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent
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
